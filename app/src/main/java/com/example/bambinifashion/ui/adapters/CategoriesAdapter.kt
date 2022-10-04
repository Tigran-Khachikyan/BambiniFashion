package com.example.bambinifashion.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bambinifashion.R
import com.example.bambinifashion.databinding.ListItemCategoryBinding
import com.example.bambinifashion.domain.models.Category
import com.squareup.picasso.Picasso

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private val differ = AsyncListDiffer(this, CategoriesDiffUtil())

    fun setListItems(items: List<Category>) {
        differ.submitList(items)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemCategoryBinding.bind(view)
        fun bind(item: Category) {
            binding.run {
                val isFullScreenImage = item.backgroundColor.isNullOrBlank()
                item.backgroundColor?.let {
                    root.setBackgroundColor(Color.parseColor(it))
                }

                val imageSideParam = if (isFullScreenImage) {
                    ViewGroup.LayoutParams.MATCH_PARENT
                } else {
                    ViewGroup.LayoutParams.WRAP_CONTENT
                }
                with(ivCategory) {
                    layoutParams.height = imageSideParam
                    layoutParams.width = imageSideParam
                    Picasso
                        .get()
                        .load(item.imageUrl)
                        .apply { if (isFullScreenImage) fit() else resize(320, 320) }
                        .centerCrop()
                        .into(this)
                }

                with(tvTitle) {
                    text = item.title
                    setBackgroundColor(if (isFullScreenImage) Color.WHITE else Color.TRANSPARENT)
                }


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    class CategoriesDiffUtil : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
}


