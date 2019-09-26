package by.morozova.apod.ui.activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.morozova.apod.models.entity.ShortImageInfo
import by.morozova.apod.ui.activity.viewholder.ImageViewHolder


class ImageAdapter(
    private val clickListener: ImageViewHolder.OpenItem,
    private val shareClickListener: ImageViewHolder.Share
) : ListAdapter<ShortImageInfo, ImageViewHolder>(
    object : DiffUtil.ItemCallback<ShortImageInfo>() {
        override fun areItemsTheSame(old: ShortImageInfo, new: ShortImageInfo) =
            old.date == new.date

        override fun areContentsTheSame(old: ShortImageInfo, new: ShortImageInfo) = old == new

    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                by.morozova.apod.R.layout.item_image,
                parent,
                false
            ), clickListener, shareClickListener
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindModel(getItem(position))
    }
}