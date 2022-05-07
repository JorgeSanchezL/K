package com.example.k.logic

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.k.R

class ItemAdapter(var context: Context, var arrayList: ArrayList<Valor>) : BaseAdapter() {

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return arrayList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = View.inflate(context, R.layout.item_list_view, null)

        var imageView : ImageView = view.findViewById(R.id.icon_image_view)
        var cartaText : TextView = view.findViewById(R.id.card_text_view)

        var valor = arrayList.get(position)

        imageView.setImageResource(context.resources.getIdentifier(valor.toString()+"corazones", "drawable", context.packageName))
        cartaText.text = valor.toString().replaceFirstChar { it.uppercase() }

        return view!!
    }

}