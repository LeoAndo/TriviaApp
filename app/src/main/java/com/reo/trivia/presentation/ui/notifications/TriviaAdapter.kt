package com.reo.trivia.presentation.ui.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.reo.trivia.R
import com.reo.trivia.databinding.CardTodoBinding

internal class TriviaAdapter : RecyclerView.Adapter<TriviaAdapter.ItemViewHolder>() {

    val data = mutableListOf<String>()

    init {
        stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_todo, parent, false)
        return ItemViewHolder(v)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.content.text = data[position]
    }

    fun getItem(position: Int): String {
        return data[position]
    }

    fun addAllItems(data: List<String>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun updateItems(data: List<String>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    class ItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val binding = DataBindingUtil.bind<CardTodoBinding>(v)!!
    }
}