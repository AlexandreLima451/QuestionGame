package com.example.android.question.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.android.question.R

class AnimalItemAdapter(context: Context?, resource: Int, animalItens: MutableList<AnimalItem>?) : ArrayAdapter<AnimalItem>(context, resource, animalItens) {

    constructor(context: Context?, animalItens: MutableList<AnimalItem>?) : this(context, 0, animalItens)


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        var listItemView = convertView

        if (listItemView == null){
            listItemView = LayoutInflater.from(context).
                    inflate(R.layout.animal_list_item, parent, false)
        }

        val currentAnimalItem = getItem(position)

        val animalImageView = listItemView?.findViewById(R.id.animal_image) as ImageView
        val animalBreedTextView = listItemView.findViewById(R.id.animal_breed) as TextView
        val animalDescriptionView = listItemView.findViewById(R.id.animal_description) as TextView

        animalImageView.setImageResource(currentAnimalItem.animalImage)
        animalBreedTextView.text = context.resources.getString(currentAnimalItem.animalBreed)
        animalDescriptionView.text = context.resources.getString(currentAnimalItem.animalDescription)

        return listItemView
    }
}