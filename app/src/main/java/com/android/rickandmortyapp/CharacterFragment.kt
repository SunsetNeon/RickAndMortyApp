package com.android.rickandmortyapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_character.*
import kotlinx.android.synthetic.main.fragment_character.view.*
import kotlinx.android.synthetic.main.item_layout.*
import kotlinx.android.synthetic.main.item_layout.view.*


class CharacterFragment : Fragment() {

    //переменные с инфой полученной от getInfoAbout
    lateinit var name:String
    lateinit var gender:String
    lateinit var image:String
    lateinit var status:String
    lateinit var species:String
    lateinit var type:String
    lateinit var episode:String
    lateinit var created:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val myFragmentView = inflater.inflate(R.layout.fragment_character, container, false)

        //Блок со всеми вью фрагмента
        var textViewName: TextView = myFragmentView.tv_name
        textViewName.text ="Name:"+"  "+ name
        myFragmentView.tv_gender.text ="Gender:"+"  "+gender
        myFragmentView.tv_status.text ="Status:"+"  "+status
        myFragmentView.tv_species.text ="Species:"+"  "+species
        myFragmentView.tv_type.text ="Type:"+"  "+type
        myFragmentView.tv_episode.text ="Episodes:"+"  "+episode.replace("https://rickandmortyapi.com/api/episode/", "").substringAfter("[").substringBefore("]")//минус мусор
        myFragmentView.tv_created.text ="Created:"+"  "+created.substringBefore("T")
        if (myFragmentView.tv_type.text == "Type:  ")  {
            myFragmentView.tv_type.text ="Type:  Unknown"//если апи дает пустой ответ = Unknown тип(type)
        }
        Glide.with(myFragmentView.iv_characterPicture)
            .load(image)
            .into(myFragmentView.iv_characterPicture)

        return myFragmentView
    }

    //функция, для того, что бы получить инфу о конкретном персонаже из RecyclerAdapter
    public fun getInfoAbout(
        namesInfo: String,
        genderInfo:String,
        statusInfo:String,
        speiciesInfo:String,
        typeInfo:String,
        episodeInfo: String,
        createdInfo:String,
        imageInfo:String) {
        name = namesInfo
        gender= genderInfo
        status = statusInfo
        species = speiciesInfo
        type = typeInfo
        episode = episodeInfo
        created = createdInfo
        image = imageInfo
    }

}