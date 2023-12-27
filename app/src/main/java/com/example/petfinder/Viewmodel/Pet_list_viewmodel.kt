package com.example.petfinder.Viewmodel

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petfinder.Adapter.Pet_list_adapter
import com.example.petfinder.Adapter.Pet_type_adapter
import com.example.petfinder.Bean.Global
import com.example.petfinder.Bean.Pet
import com.example.petfinder.Bean.Pet_type
import com.example.petfinder.Coroutines.AsyncRq
import com.example.petfinder.Interface.Pet_list_interface
import com.example.petfinder.R
import com.example.petfinder.Retrofitapis.RF_Requests
import com.example.petfinder.async.Add_listot_room_async
import com.example.petfinder.async.GetData_api
import com.example.petfinder.databinding.FragmentPetsListBinding
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.lifecycle.HiltViewModel
import org.json.JSONObject
import javax.inject.Inject
@HiltViewModel
class Pet_list_viewmodel@Inject constructor(): ViewModel(),Pet_list_interface {
    var token: MutableLiveData<String> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var typelist: MutableLiveData<ArrayList<Pet_type>> = MutableLiveData()
    var petlist: MutableLiveData<ArrayList<Pet>> = MutableLiveData()
    var type_selected="All"
    lateinit var binding: FragmentPetsListBinding
    var get_Token:AsyncRq<String,Any>?=null
    var gettypes:AsyncRq<String,Any>?=null
    var get_Pets:AsyncRq<String,Any>?=null
     var page=1
    lateinit var activity: FragmentActivity
    override fun handle_ui(binding: FragmentPetsListBinding, activity: FragmentActivity) {
        this.activity=activity
        this.binding=binding
        val mLayoutManager: RecyclerView.LayoutManager =  LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        binding.petsList.layoutManager = mLayoutManager
        binding.petsList.itemAnimator = DefaultItemAnimator()
        val mLayoutManagerh: RecyclerView.LayoutManager =  LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        binding.typesList.layoutManager = mLayoutManagerh
        binding.typesList.itemAnimator = DefaultItemAnimator()
        handle_scroll()

    }

    override fun handle_loading(show: Boolean) {
     if (show){
         binding.load.visibility=View.VISIBLE
         binding.typesList.visibility=View.GONE
         binding.petsList.visibility=View.GONE
     }else{

             binding.load.visibility = View.GONE
             binding.typesList.visibility = View.VISIBLE
             binding.petsList.visibility = View.VISIBLE

     }
    }

    override fun set_adapter(type: Int) {
    if (type==0){
        binding.typesList.adapter=Pet_type_adapter(typelist.value!!,this@Pet_list_viewmodel)
    }else{
        binding.petsList.adapter=Pet_list_adapter(petlist.value!!,this@Pet_list_viewmodel)
    }
    }

    override fun callapi(type: Int) {
           fireapi(type)
    }

    override fun handle_scroll() {
        binding.petsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                //check for scroll down
                if (dy > 0) {

                    // get count of visible items in recyclerview
                    val visibleItemCount: Int = binding.petsList.layoutManager!!.childCount
                    // get count of total items that recyclerview owns in layout
                    val totalItemCount: Int = binding.petsList.layoutManager!!.itemCount
                    // get current position of first visible item in recycle view
                    val pastVisiblesItems: Int = (binding.petsList.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
                    /*
                    * Ex: pastVisiblesItems (15) + visibleItemCount (5) = totalItemCount (20)
                    * As long as the sum of pastVisiblesItems & visibleItemCount is greater than or equal to totalItemCount,
                    * it means there's more items to load from the server
                    *  */
                    val loadMore = pastVisiblesItems + visibleItemCount >= totalItemCount
                    if (loadMore) {
                        var run=true
                        if (page>1&&get_Pets!=null&&get_Pets!!.getStatus()==AsyncRq.Status.RUNNING){
                            run=false
                        }
                        // To make sure only calling asyn if it's not already running
                        if (run){
                            page += 1
                            // To make sure execute existing async and only create new instance if not existed
                           callapi(1)

                        }
                    }
                }
            }
        })
    }

    override fun fireapi( type: Int) {
        when (type) {
            0 -> {
                if (get_Token != null&&get_Token!!.getStatus() == AsyncRq.Status.RUNNING) {
                    cancelapi(get_Token)
                }
                get_Token=GetData_api(type,this@Pet_list_viewmodel).execute<String>()
            }
            1 -> {
                if (gettypes != null && gettypes!!.getStatus() == AsyncRq.Status.RUNNING) {
                    cancelapi(gettypes)
                }
                gettypes=GetData_api(type,this@Pet_list_viewmodel).execute<String>()
            }
            2 -> {
                if (get_Pets!=null &&  get_Pets!!.getStatus()==AsyncRq.Status.RUNNING) {
                    cancelapi(get_Pets)
                }
                get_Pets=GetData_api(type,this@Pet_list_viewmodel).execute<String>()
            }
        }
    }

    override fun cancelapi(async: AsyncRq<String, Any>?) {
        async.let {
            if (it!!.getStatus()==AsyncRq.Status.RUNNING){
                it.cancel()
            }
        }
    }

    override fun gettoken_api(): String {
        val param=HashMap<String,Any>()
        param["grant_type"] = "client_credentials"
        param["client_id"] = Global.client_id
        param["client_secret"] =Global.client_secret
        return RF_Requests().Post(Global.gettoken,param,activity)
    }

    override fun gettypes(): String {
        return RF_Requests().Get_request(Global.gettypes)
    }

    override fun getanimals(): String {
        var url=Global.getallanimals
        url+="?page=${page}"
        url+="&limit=30"
        if (type_selected.isNotEmpty()&&type_selected!="All"){
            url+="&type=${type_selected}"
        }
        return RF_Requests().Get_request(url)
    }

    override fun parse_token(result: String) {
//        {
//            "token_type": "Bearer",
//            "expires_in": 3600,
//            "access_token": "..."
//        }
        if (result.isNotEmpty()){
            val json=JSONObject(result)
            if (json.has("token_type")){
                Global.token= json.getString("token_type")
            }
            if (json.has("access_token")){
                Global.token+=" ${json.getString("access_token")}"
            }
            Global.setToken(activity,Global.token)
            callapi(1)
            callapi(2)

        }
    }

    override fun parse_Types(result: String):ArrayList<Pet_type> {
        var list=ArrayList<Pet_type>()
       if (!Checkerror(result)){
           val json=JSONObject(result)

           if (json.has("types")){
               val jsonArray=json.getJSONArray("types")
               val type=Pet_type()
               type.type="All"
               type.id="0"
               list.add(type)
               for (i in 0 until jsonArray.length()) {
                   val  j=jsonArray.getJSONObject(i)

                   if (j.has("name")){
                       val pettype=Pet_type()
                       pettype.id= (i+1).toString()
                       pettype.type= j.getString("name")
                     list.add(pettype)
                   }

               }

           }
       }else{
           callapi(0)
       }
        return list
    }

    override fun parse_pets(result: String):ArrayList<Pet> {
        var list=ArrayList<Pet>()
        if (!Checkerror(result)){
//             id
//           Name
//          Gender
//          image
//            Color
//       address
//            type
//            Size
//       url
            val json=JSONObject(result)

            if (json.has("animals")){
                val jsonArray=json.getJSONArray("animals")
                for (i in 0 until jsonArray.length()) {
                    val pet=Pet()
                    val jsonObject=jsonArray.getJSONObject(i)
                    if (jsonObject.has("id")){
                        pet.id=jsonObject.getInt("id")
                    }
                    if (jsonObject.has("name")){
                        pet.Name=jsonObject.getString("name")
                    }
                    if (jsonObject.has("gender")){
                        pet.Gender=jsonObject.getString("gender")
                    }
                    if (jsonObject.has("photos")&&jsonObject.getJSONArray("photos").length()>0){
                        val photojson=jsonObject.getJSONArray("photos").getJSONObject(0)
                        if (photojson.has("small")){
                            pet.smallimage=photojson.getString("small")
                        }
                        if (photojson.has("large")){
                            pet.image=photojson.getString("large")
                        }
                    }
                    if (jsonObject.has("Color")){
                        val colorjson=jsonObject.getJSONObject("Color")
                        if (colorjson.has("primary")){
                            pet.Color=colorjson.getString("primary")
                        }

                    }
                    if (jsonObject.has("contact")&&jsonObject.getJSONObject("contact").has("address")){
                        val addjson=jsonObject.getJSONObject("contact").getJSONObject("address")
                        pet.address= if (addjson.has("city")&&addjson.get("city")!=null){
                             addjson.getString("city")
                        }else{
                            "N/A"
                        }
                        pet.address+= if (addjson.has("state")&&addjson.get("state")!=null){
                             addjson.getString("state")
                        }else{
                            "N/A"
                        }
                        pet.address+= if (addjson.has("country")&&addjson.get("country")!=null){
                             addjson.getString("country")
                        }else{
                            "N/A"
                        }
                    }
                    if (jsonObject.has("type")){
                        pet.type=jsonObject.getString("type")
                    }
                    if (jsonObject.has("Size")){
                        pet.Size=jsonObject.getString("Size")
                    }
                    if (jsonObject.has("url")){
                        pet.url=jsonObject.getString("url")
                    }
                    list.add(pet)

                }

                Add_listot_room_async(this@Pet_list_viewmodel).execute<String>()
            }

        }else{
            callapi(0)
        }
        return list
    }

    override fun Checkerror(result: String): Boolean {
//        Response{protocol=http/1.1, code=401, message=Unauthorized, url=https://api.petfinder.com/v2/types}
        val json=JSONObject(result)
        return json.has("Response")&&json.getJSONObject("Response").has("code")&&json.getInt("code")==401
    }

    fun changeToolbarTitle( title: String?, v: View) {
        val toolbar = v.findViewById<View>(R.id.toolbar) as MaterialToolbar
        toolbar.title = title

    }

    fun select_type(type: Pet_type){
        type_selected=type.type
binding.typesList.adapter!!.notifyDataSetChanged()
        callapi(1)
    }
}