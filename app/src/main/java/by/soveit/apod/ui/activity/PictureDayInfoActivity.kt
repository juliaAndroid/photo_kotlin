package by.soveit.apod.ui.activity

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import by.soveit.apod.R
import by.soveit.apod.models.action.DateConverter
import by.soveit.apod.models.entity.ImageGallery
import by.soveit.apod.models.entity.MediaType
import by.soveit.apod.viewmodel.ImageInfoViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_picture_day_info.*
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ID = "id"
private const val PATTERN_VIEW = "dd.MM.yyyy"

fun Context.newPictureDayInfoIntent(id: Long): Intent {
    val intent = Intent(this, PictureDayInfoActivity::class.java)
    intent.putExtra(ID, id)
    return intent
}

class PictureDayInfoActivity : AppCompatActivity() {

    private val viewModel by viewModel<ImageInfoViewModel>()
    private var dateConverter: DateConverter = DateConverter(PATTERN_VIEW)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_day_info)

        val id = getIdFromIntent()
        if (id >= 0) {
            viewModel.id = id
            viewModel.imageLiveData.observe(this, Observer {
                if (it != null) {
                    parseDataToForm(it)
                }
            })
        }
    }

    private fun getIdFromIntent(): Long {
        return intent.getLongExtra(ID, -1)
    }

    private fun parseDataToForm(imageGallery: ImageGallery) {
        tvTitle.text = imageGallery.title
        tvDate.text = dateConverter.longDateToString(imageGallery.date)
        tvAllText.text = imageGallery.explanation
        var validUrl: String? = null
        when (imageGallery.mediaType) {
            MediaType.IMAGE -> imageGallery.hdurl
            else -> {
                imageGallery.thumbnailUrl != null ?: imageGallery.thumbnailUrl
                ivPlay.visibility = View.VISIBLE
                ivPlay.setOnClickListener { openVideo(imageGallery.url) }
            }
        }
        validUrl?.let {
            Glide.with(this)
                .load(it)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(ivImage)
        }
        setBtnAction(imageGallery)
    }

    private fun setBtnAction(imageGallery: ImageGallery) {
        ivBack.setOnClickListener { onBackPressed() }
        ivShare.setOnClickListener { share(imageGallery) }
    }

    private fun share(imageGallery: ImageGallery) {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, imageGallery.title)
        shareIntent.putExtra(Intent.EXTRA_TEXT, imageGallery.explanation)
        startActivity(Intent.createChooser(shareIntent, getString(R.string.app_full_name)))
    }

    private fun Context.safeStartActivity(intent: Intent, title: String = ""): Boolean {
        return try {
            startActivity(intent)
            true
        } catch (e: ActivityNotFoundException) {
            val chooser = Intent.createChooser(intent, title)
                .addFlags(intent.flags)
            startActivity(chooser)
            false
        }
    }

    private fun openVideo(url: String) {
        val openVideoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        safeStartActivity(openVideoIntent, getString(R.string.app_full_name))
    }
}
