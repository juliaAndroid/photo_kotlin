package by.morozova.apod.ui.activity.scroll

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

open class PaginationScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val scrollCallback: ScrollCallback
) : RecyclerView.OnScrollListener() {

    interface ScrollCallback {
        fun updateRecycleView()
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (totalItemCount != 0) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                && firstVisibleItemPosition >= 0
            ) {
                scrollCallback.updateRecycleView()
            }
        }
    }
}