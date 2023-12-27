package com.example.petfinder

import android.content.Context
import com.example.petfinder.Viewmodel.Pet_list_viewmodel
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class Pet_list_test: TestCase() {
    lateinit var context: Context
    @Before
    @Throws(Exception::class)
    fun before() {

        this.context = Mockito.mock(MainActivity::class.java)

    }
    @Test
    fun handleresulttype() {
        val result="fafadas"
        val model = Pet_list_viewmodel()
        val list= model.parse_Types(result)
        assertNull(list)
    }
    @Test
    fun handleresultpets() {
        val result="fafadas"
        val model = Pet_list_viewmodel()
        val list= model.parse_pets(result)
        assertNull(list)
    }
    @Test
    fun handleresulttype2() {
        val result="{\n" +
                "    \"types\": [\n" +
                "        {\n" +
                "            \"name\": \"Rabbit\",\n" +
                "            \"coats\": [\n" +
                "                \"Short\",\n" +
                "                \"Long\"\n" +
                "            ],\n" +
                "            \"colors\": [\n" +
                "                \"Agouti\",\n" +
                "                \"Black\",\n" +
                "                \"Blue / Gray\",\n" +
                "                \"Brown / Chocolate\",\n" +
                "                \"Cream\",\n" +
                "                \"Lilac\",\n" +
                "                \"Orange / Red\",\n" +
                "                \"Sable\",\n" +
                "                \"Silver Marten\",\n" +
                "                \"Tan\",\n" +
                "                \"Tortoiseshell\",\n" +
                "                \"White\"\n" +
                "            ],\n" +
                "            \"genders\": [\n" +
                "                \"Male\",\n" +
                "                \"Female\"\n" +
                "            ],\n" +
                "            \"_links\": {\n" +
                "                \"self\": {\n" +
                "                    \"href\": \"/v2/types/rabbit\"\n" +
                "                },\n" +
                "                \"breeds\": {\n" +
                "                    \"href\": \"/v2/types/rabbit/breeds\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"Bird\",\n" +
                "            \"coats\": [],\n" +
                "            \"colors\": [\n" +
                "                \"Black\",\n" +
                "                \"Blue\",\n" +
                "                \"Brown\",\n" +
                "                \"Buff\",\n" +
                "                \"Gray\",\n" +
                "                \"Green\",\n" +
                "                \"Olive\",\n" +
                "                \"Orange\",\n" +
                "                \"Pink\",\n" +
                "                \"Purple / Violet\",\n" +
                "                \"Red\",\n" +
                "                \"Rust / Rufous\",\n" +
                "                \"Tan\",\n" +
                "                \"White\",\n" +
                "                \"Yellow\"\n" +
                "            ],\n" +
                "            \"genders\": [\n" +
                "                \"Male\",\n" +
                "                \"Female\",\n" +
                "                \"Unknown\"\n" +
                "            ],\n" +
                "            \"_links\": {\n" +
                "                \"self\": {\n" +
                "                    \"href\": \"/v2/types/bird\"\n" +
                "                },\n" +
                "                \"breeds\": {\n" +
                "                    \"href\": \"/v2/types/bird/breeds\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}"
        val model = Pet_list_viewmodel()
        val list= model.parse_Types(result)
        assertNotNull(list)
    }
    @Test
    fun handleresultpet2() {
        val result="{\n" +
                "    \"animals\": [\n" +
                "        {\n" +
                "            \"id\": 124,\n" +
                "            \"organization_id\": \"NJ333\",\n" +
                "            \"url\": \"https://www.petfinder.com/cat/nebula-124/nj/jersey-city/nj333-petfinder-test-account/?referrer_id=d7e3700b-2e07-11e9-b3f3-0800275f82b1\",\n" +
                "            \"type\": \"Cat\",\n" +
                "            \"species\": \"Cat\",\n" +
                "            \"breeds\": {\n" +
                "                \"primary\": \"American Shorthair\",\n" +
                "                \"secondary\": null,\n" +
                "                \"mixed\": false,\n" +
                "                \"unknown\": false\n" +
                "            },\n" +
                "            \"colors\": {\n" +
                "                \"primary\": \"Tortoiseshell\",\n" +
                "                \"secondary\": null,\n" +
                "                \"tertiary\": null\n" +
                "            },\n" +
                "            \"age\": \"Young\",\n" +
                "            \"gender\": \"Female\",\n" +
                "            \"size\": \"Medium\",\n" +
                "            \"coat\": \"Short\",\n" +
                "            \"name\": \"Nebula\",\n" +
                "            \"description\": \"Nebula is a shorthaired, shy cat. She is very affectionate once she warms up to you.\",\n" +
                "            \"photos\": [\n" +
                "                {\n" +
                "                    \"small\": \"https://photos.petfinder.com/photos/pets/124/1/?bust=1546042081&width=100\",\n" +
                "                    \"medium\": \"https://photos.petfinder.com/photos/pets/124/1/?bust=1546042081&width=300\",\n" +
                "                    \"large\": \"https://photos.petfinder.com/photos/pets/124/1/?bust=1546042081&width=600\",\n" +
                "                    \"full\": \"https://photos.petfinder.com/photos/pets/124/1/?bust=1546042081\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"videos\": [\n" +
                "                {\n" +
                "                    \"embed\": \"<iframe src=\\\"https://www.youtube.com/embed/xaXbs1fRFRM\\\" frameborder=\\\"0\\\" allowfullscreen></iframe>\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"status\": \"adoptable\",\n" +
                "            \"attributes\": {\n" +
                "                \"spayed_neutered\": true,\n" +
                "                \"house_trained\": true,\n" +
                "                \"declawed\": false,\n" +
                "                \"special_needs\": false,\n" +
                "                \"shots_current\": true\n" +
                "            },\n" +
                "            \"environment\": {\n" +
                "                \"children\": false,\n" +
                "                \"dogs\": true,\n" +
                "                \"cats\": true\n" +
                "            },\n" +
                "            \"tags\": [\n" +
                "                \"Cute\",\n" +
                "                \"Intelligent\",\n" +
                "                \"Playful\",\n" +
                "                \"Happy\",\n" +
                "                \"Affectionate\"\n" +
                "            ],\n" +
                "            \"contact\": {\n" +
                "                \"email\": \"petfindertechsupport@gmail.com\",\n" +
                "                \"phone\": \"555-555-5555\",\n" +
                "                \"address\": {\n" +
                "                    \"address1\": null,\n" +
                "                    \"address2\": null,\n" +
                "                    \"city\": \"Jersey City\",\n" +
                "                    \"state\": \"NJ\",\n" +
                "                    \"postcode\": \"07097\",\n" +
                "                    \"country\": \"US\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"published_at\": \"2018-09-04T14:49:09+0000\",\n" +
                "            \"distance\": 0.4095,\n" +
                "            \"_links\": {\n" +
                "                \"self\": {\n" +
                "                    \"href\": \"/v2/animals/124\"\n" +
                "                },\n" +
                "                \"type\": {\n" +
                "                    \"href\": \"/v2/types/cat\"\n" +
                "                },\n" +
                "                \"organization\": {\n" +
                "                    \"href\": \"/v2/organizations/nj333\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"pagination\": {\n" +
                "        \"count_per_page\": 20,\n" +
                "        \"total_count\": 320,\n" +
                "        \"current_page\": 1,\n" +
                "        \"total_pages\": 16,\n" +
                "        \"_links\": {}\n" +
                "    }\n" +
                "}\n"
        val model = Pet_list_viewmodel()
        val list= model.parse_pets(result)
        assertNotNull(list)
    }
}