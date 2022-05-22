package com.example.fitdroid.calorielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitdroid.database.Calorie
import com.example.fitdroid.databinding.CalorieItemBinding

/**
 * A RecyclerView adapter that uses the DiffCallback for better performance and a listener to handle
 * clicks/taps on each item.
 */
class CalorieListAdapter(val clickListener: CalorieListener) : ListAdapter<Calorie,
        CalorieListAdapter.ItemViewHolder>(CalorieDiffCallback()) {

    /**
     * Inner class used to store data about each element in the RecyclerView. We provide a binding
     * to make it easy to access elements of the layout.
     */
    class ItemViewHolder(val binding: CalorieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Assign an calorie object and clickListener to the ItemViewHolder
         */
        fun bind(item: Calorie, clickListener: CalorieListener) {
            binding.calorie = item
            binding.clickListener = clickListener
        }
    }

    /**
     * Creates a View to visualize one element in the RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // We use an inflater based on the parent (CalorieListFragment) and create an
        // ItemViewHolder with binding to the layout to simplify access.
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CalorieItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    /**
     * The function is called whenever the RecyclerView displays a specific element. It provides
     * access to the ItemViewHolder and its position.
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // Assign the corresponding element from the data and the click listener. Take note getItem
        // is a function provided by ListAdapter.
        holder.bind(getItem(position), clickListener)
    }
}

/**
 * Manages a RecyclerView list using the Eugene W. Myers's difference algorithm to reduce processing.
 */
class CalorieDiffCallback : DiffUtil.ItemCallback<Calorie>() {
    /**
     * We use calorieId because it is a unique ID referring to a single element.
     */
    override fun areItemsTheSame(oldItem: Calorie, newItem: Calorie): Boolean {
        return oldItem.calorieId == newItem.calorieId
    }

    /**
     * We check all properties to check equality between Calorie objects.
     */
    override fun areContentsTheSame(oldItem: Calorie, newItem: Calorie): Boolean {
        return oldItem.name == newItem.name && oldItem.location == newItem.location
    }
}

/**
 * Listener that accepts a lambda function clickListener. It will be called when an element of the
 * RecyclerView is clicked/tapped.
 */
class CalorieListener(val clickListener: (calorieId: Long) -> Unit) {
    fun onClick(calorie: Calorie) = clickListener(calorie.calorieId)
}