package by.morozova.apod.ui.activity.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.morozova.apod.R
import by.morozova.apod.models.entity.MediaType
import by.morozova.apod.models.entity.ShortImageInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class ImageViewHolder(
    itemView: View,
    private val itemClickListener: OpenItem,
    private val shareClickListener: Share
) : RecyclerView.ViewHolder(itemView) {


    interface OpenItem {
        fun onItemClick(date: Long)
    }

    interface Share {
        fun onShareItemClick(title: String)
    }

    private val title: TextView = itemView.findViewById(R.id.tvImage_title)
    private val ivShare: ImageView = itemView.findViewById(R.id.ivShare)
    private val imageBackground: ImageView = itemView.findViewById(R.id.ivImage)

    fun bindModel(image: ShortImageInfo) {
        title.text = image.title
        itemView.setOnClickListener { itemClickListener.onItemClick(image.date) }
        ivShare.setOnClickListener { shareClickListener.onShareItemClick(image.title) }
        var validUrl: String? = null
        when (image.mediaType) {
            MediaType.IMAGE -> validUrl = image.url
            else -> {
                if (image.thumbnailUrl != null)
                    validUrl = image.thumbnailUrl
            }
        }

        validUrl?.let {
            Glide.with(itemView)
                .load(it)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .error(R.drawable.ic_terrain_white_24dp)
                .into(imageBackground)
        }
    }
}