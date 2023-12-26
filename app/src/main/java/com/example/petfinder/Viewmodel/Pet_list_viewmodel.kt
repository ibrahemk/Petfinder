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
import com.example.petfinder.Bean.Pet
import com.example.petfinder.Bean.Pet_type
import com.example.petfinder.Coroutines.AsyncRq
import com.example.petfinder.Interface.Pet_list_interface
import com.example.petfinder.databinding.FragmentPetsListBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class Pet_list_viewmodel@Inject constructor(): ViewModel(),Pet_list_interface {
    var token: MutableLiveData<String> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var typelist: MutableLiveData<ArrayList<Pet_type>> = MutableLiveData()
    var petlist: MutableLiveData<ArrayList<Pet>> = MutableLiveData()
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
         binding.load.visibility=View.GONE
         binding.typesList.visibility=View.VISIBLE
         binding.petsList.visibility=View.VISIBLE
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
        when(type){
        0->{
           fireapi(gettypes!!)
        }
        1->{
            fireapi(get_Pets!!)
        }else->{
            fireapi(get_Token!!)
        }
        }
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

    override fun fireapi(async: AsyncRq<String, Any>) {
     if (get_Token!=null && async.javaClass==get_Token!!.javaClass && get_Token!!.getStatus()==AsyncRq.Status.RUNNING){
         cancelapi(get_Token)
         async.execute<String>()
     }else if (gettypes!=null && async.javaClass==gettypes!!.javaClass && gettypes!!.getStatus()==AsyncRq.Status.RUNNING){
         cancelapi(gettypes)
         async.execute<String>()
     }else if (get_Pets!=null && async.javaClass==get_Pets!!.javaClass && get_Pets!!.getStatus()==AsyncRq.Status.RUNNING){
         cancelapi(get_Pets)
         async.execute<String>()
     }
    }

    override fun cancelapi(async: AsyncRq<String, Any>?) {
        async.let {
            if (it!!.getStatus()==AsyncRq.Status.RUNNING){
                it.cancel()
            }
        }
    }
}