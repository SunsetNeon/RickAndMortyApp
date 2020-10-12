package com.android.rickandmortyapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*

//листы на месте
class RecyclerAdapter(private var names: List<String>,
                      private var images: List<String>,
                      private var genders: List<String>,
                      private var status: List<String>,
                      private var species: List<String>,
                      private var type: List<String>,
                      private var episode: List<String>,
                      private var created: List<String>,):
RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemTitle: TextView = itemView.tv_title
        val itemPicture: ImageView = itemView.iv_image


        init {
            itemView.setOnClickListener {v: View ->
                val position: Int = adapterPosition // позиция айтема в recyclerview

                //фрагмент
                val activity1 = v.context as AppCompatActivity
                val characterFragment = CharacterFragment()



                activity1.supportFragmentManager.beginTransaction().apply {//смена фрагмента
                    replace(R.id.flFragment, characterFragment)
                    addToBackStack(null)
                    commit()
                }
                //получение инфы о конкретном персонаже
                characterFragment.getInfoAbout(names[position],
                    genders[position],
                    status[position],
                    species[position],
                    type[position],
                    episode[position],
                    created[position],
                    images[position]
                )
                Log.d("characterFragmentP", episode.toString())
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from (parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    //бинд вью в RecyclerView, имя + картинка
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemTitle.text = names[position]




        Glide.with(holder.itemPicture)
            .load(images[position])
            .into(holder.itemPicture)

    }

    override fun getItemCount(): Int {
        return names.size
    }

}