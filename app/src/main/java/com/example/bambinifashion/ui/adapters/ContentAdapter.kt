package com.example.bambinifashion.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bambinifashion.R
import com.example.bambinifashion.databinding.ListItemContentBinding
import com.example.bambinifashion.domain.models.Content
import com.example.bambinifashion.ui.utils.visibleOrGone
import com.squareup.picasso.Picasso

class ContentAdapter : RecyclerView.Adapter<ContentAdapter.ViewHolder>() {

    private val differ = AsyncListDiffer(this, ContentDiffUtil())

    fun setListItems(items: List<Content>) {
        differ.submitList(items)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemContentBinding.bind(view)
        fun bind(item: Content) {
            binding.run {
                with(tvTitle) {
                    visibleOrGone(!item.title.isNullOrBlank())
                    text = item.title
                }
                with(ivBanner) {
                    visibleOrGone(!item.bannerUrl.isNullOrBlank())
                    Picasso
                        .get()
                        .load(item.bannerUrl)
                        .fit()
                        .centerCrop()
                        .into(ivBanner)
                }
                with(recCategories) {
                    visibleOrGone(!item.categories.isNullOrEmpty())
                    item.categories?.let {
                        setHasFixedSize(false)
                        adapter = CategoriesAdapter().apply { setListItems(it) }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_content, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    class ContentDiffUtil : DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
            return oldItem == newItem
        }
    }
}
