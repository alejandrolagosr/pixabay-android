package com.flagos.photos.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.flagos.photos.R
import com.flagos.photos.common.loadImageFromUrl
import com.flagos.photos.databinding.ItemPhotoBinding
import com.flagos.photos.domain.Hits
import com.google.android.material.chip.Chip

private const val COMMA = ","

class PhotoViewHolder(private val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(hit: Hits) {
        with(binding) {
            imagePhoto.loadImageFromUrl(hit.largeImageURL)
            imageUserPhoto.loadImageFromUrl(hit.userImageURL)
            textUserName.text = hit.user
            textLikes.text = itemView.context.resources.getQuantityString(
                R.plurals.likes,
                hit.likes,
                hit.likes
            )

            val tagList = hit.tags.split(COMMA).map { it.trim() }
            tagList.forEach { tag ->
                val chip = Chip(binding.root.context).apply { text = tag }
                chipGroupExamples.addView(chip)
            }
        }
    }
}
