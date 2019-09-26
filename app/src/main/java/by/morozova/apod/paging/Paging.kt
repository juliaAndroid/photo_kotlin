package by.morozova.apod.paging

class Paging<T>(private val loader: suspend (loaded: List<T>) -> List<T>?) {

    var items: List<T> = emptyList()

    suspend fun nextPage(): List<T>? {
        return loader(items)?.apply {
            items = items.plus(this)
        }
    }
}