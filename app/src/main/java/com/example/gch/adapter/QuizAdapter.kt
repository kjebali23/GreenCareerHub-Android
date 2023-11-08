

package com.example.gch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.gch.R // Replace with the actual resource reference for your image
import com.example.gch.Models.Quiz

class GridViewAdapter(private val context: Context, private val newsList: List<Quiz>) : BaseAdapter() {

    override fun getCount(): Int {
        return newsList.size
    }

    override fun getItem(position: Int): Any {
        return newsList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.single_quiz, null)
            viewHolder = ViewHolder()
            viewHolder.title = view.findViewById(R.id.formationTitle)
//            viewHolder.description = view.findViewById(R.id.formationDescription)
            viewHolder.image = view.findViewById(R.id.formationImage)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val formation = newsList[position]
        viewHolder.title.text = formation.title
//        viewHolder.description.text = formation.description
        viewHolder.image.setImageResource(formation.imageRes)

        return view
    }

    private class ViewHolder {
        lateinit var title: TextView
//        lateinit var description: TextView
        lateinit var image: ImageView
    }
}
