package com.android.rickandmortyapp

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_character.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.Exception

const val BASE_URL = "https://rickandmortyapi.com/api/"

class MainActivity : AppCompatActivity() {

    //листы с инфой, полученной от API
    private var namesList = mutableListOf<String>()
    private var imagessList = mutableListOf<String>()
    private var genderList = mutableListOf<String>()
    private var statusList = mutableListOf<String>()
    private var speciesList = mutableListOf<String>()
    private var typeList = mutableListOf<String>()
    private var episodeList = mutableListOf<String>()
    private var createdList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //RecyclerView и адаптер, получивший листы с инфой
        rv_recyclerView.layoutManager = LinearLayoutManager (this)
        rv_recyclerView.adapter = RecyclerAdapter (namesList,
            imagessList,
            genderList,
            statusList,
            speciesList,
            typeList,
            episodeList,
            createdList)


        makeAPIRequest()
    }


    //добавить данные полученные по запросу к API
    private fun addToList (name: String,
                           image: String,
                           gender:String,
                           status:String,
                           species:String,
                           type:String,
                           episode:List<String>,
                           created:String ) {

        namesList.add(name)
        imagessList.add(image)
        genderList.add(gender)
        statusList.add(status)
        speciesList.add(species)
        typeList.add(type)
        episodeList.add(episode.toString())
        createdList.add(created)
    }



    private fun makeAPIRequest() {

        //запрос к API через Retrofit
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIRequest::class.java)


            //попытка получить ответ на запрос
        GlobalScope.launch(Dispatchers.IO) {

            try {
                val response = api.getAllCharacters()

                //каждый полученный результат добавляется в addToList
                for (result in response.results) {
                    Log.i ("MainActivity", "Результат = $result")
                    addToList(result.name,
                        result.image,
                        result.gender,
                        result.status,
                        result.species,
                        result.type,
                        result.episode,
                        result.created)
                }

            } catch (e: Exception) { //в случае ошибки
                Log.e("MainActivity", e.toString())
            }

        }
    }
}
