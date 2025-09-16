package com.example.myassssmentapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EntityAdapter(
    private val items: MutableList<EntityDto> = mutableListOf(),
    private val onClick: (EntityDto) -> Unit
) : RecyclerView.Adapter<EntityAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val tvP1: TextView = view.findViewById(R.id.tvP1) // title
        val tvP2: TextView = view.findViewById(R.id.tvP2) // subtitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_entity, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]

        val title = item.title?.takeIf { it.isNotBlank() } ?: "Untitled"
        val subtitle = buildList {
            if (!item.director.isNullOrBlank()) add(item.director!!)
            if (!item.genre.isNullOrBlank()) add(item.genre!!)
            if (item.releaseYear != null) add(item.releaseYear.toString())
        }.joinToString(" • ")

        holder.tvP1.text = title
        if (subtitle.isEmpty()) {
            holder.tvP2.visibility = View.GONE
        } else {
            holder.tvP2.visibility = View.VISIBLE
            holder.tvP2.text = subtitle
        }

        // ✅ Pass the data item to the click callback
        holder.itemView.setOnClickListener { onClick(item) }
    }

    override fun getItemCount(): Int = items.size

    fun submitList(newItems: List<EntityDto>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}





