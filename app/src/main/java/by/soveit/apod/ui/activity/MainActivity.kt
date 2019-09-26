package by.soveit.apod.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.soveit.apod.R
import by.soveit.apod.ui.activity.adapter.ImageAdapter
import by.soveit.apod.ui.activity.scroll.PaginationScrollListener
import by.soveit.apod.ui.activity.viewholder.ImageViewHolder
import by.soveit.apod.viewmodel.ImageViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), ImageViewHolder.OpenItem, ImageViewHolder.Share,
    PaginationScrollListener.ScrollCallback {


    private val viewModel by viewModel<ImageViewModel>()
    private lateinit var imageAdapter: ImageAdapter

    private var loading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(R.string.app_full_name)
        val layoutManager = LinearLayoutManager(this)
        mainList.addOnScrollListener(PaginationScrollListener(layoutManager, this))

        mainList.layoutManager = layoutManager
        imageAdapter = ImageAdapter(this, this)
        mainList.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        mainList.adapter = imageAdapter
        viewModel.imageLiveData.observe(this, Observer {
            loading = false
            progressBar.visibility = View.GONE
            imageAdapter.submitList(it)
            if (it.isEmpty()) errorShow()
        })
        loadMoreImages()
    }

    override fun onItemClick(date: Long) {
        startActivity(newPictureDayInfoIntent(date))
    }

    override fun onShareItemClick(title: String) {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, title)
        startActivity(shareIntent)
    }

    override fun updateRecycleView() {
        if (!loading) {
            loadMoreImages()
        }
    }

    fun loadMoreImages() {
        viewModel.loadNextPage()
        loading = true
        progressBar.visibility = View.VISIBLE
    }

    private fun errorShow() {
        Toast.makeText(this, getString(R.string.bad_response), Toast.LENGTH_SHORT).show()
    }
}
