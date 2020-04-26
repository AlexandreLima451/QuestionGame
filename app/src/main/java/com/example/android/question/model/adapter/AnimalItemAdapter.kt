package com.example.android.question.model.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.question.R
import com.example.android.question.model.AnimalItem
import kotlinx.android.synthetic.main.animal_list_item.view.*

class AnimalItemAdapter (private val animals : List<AnimalItem>,
                         context : Context, private val listener : AnimalItemClick) : Adapter<AnimalItemAdapter.ViewHolder>() {

    private val applicationContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(applicationContext)
                .inflate(R.layout.animal_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return animals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val animal = animals[position]
        holder.bindView(applicationContext, animal, listener, position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private fun selectAnimalItem(itemView : View, isSelected : Boolean){
            if(isSelected){
                itemView.setBackgroundColor(Color.WHITE)
                itemView.animal_breed.setTextColor(Color.BLACK)
                itemView.animal_description.setTextColor(Color.BLACK)
            }else{
                itemView.setBackgroundColor(Color.BLACK)
                itemView.animal_breed.setTextColor(Color.WHITE)
                itemView.animal_description.setTextColor(Color.WHITE)
            }
        }

        fun bindView(context : Context, animal : AnimalItem, listener : AnimalItemClick, position: Int){
            itemView.animal_breed.text = context.resources.getString(animal.animalBreed)
            itemView.animal_description.text = context.resources.getString(animal.animalDescription)
            itemView.animal_image.setImageResource(animal.animalImage)

            selectAnimalItem(itemView, animal.isSelected)

            itemView.setOnClickListener {
                listener.onClick(itemView, animal, position)
            }
        }
    }
}

interface AnimalItemClick{
    fun onClick(itemView : View, content : AnimalItem, position : Int)
}