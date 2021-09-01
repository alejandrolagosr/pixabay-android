package com.flagos.photos.presentation.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.flagos.photos.common.inflater
import com.flagos.photos.databinding.ItemPhotoBinding
import com.flagos.photos.domain.Hits
import com.flagos.photos.presentation.adapter.viewholder.PhotoViewHolder

class PhotosAdapter : PagingDataAdapter<Hits, PhotoViewHolder>(PhotosItemDiff()) {

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = parent.inflater
        return PhotoViewHolder(ItemPhotoBinding.inflate(inflater))
    }

    private class PhotosItemDiff : DiffUtil.ItemCallback<Hits>() {
        override fun areItemsTheSame(oldItem: Hits, newItem: Hits): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Hits, newItem: Hits): Boolean = oldItem == newItem
    }
}
