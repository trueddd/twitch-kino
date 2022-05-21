import org.w3c.dom.Element
import org.w3c.dom.HTMLCollection
import org.w3c.dom.HTMLElement
import org.w3c.dom.get

fun HTMLCollection.forEach(block: (HTMLElement) -> Unit) {
    for (index in 0 until length) {
        (get(index) as? HTMLElement)?.let(block)
    }
}

fun HTMLCollection.firstOrNull(): Element? {
    return when (length) {
        0 -> null
        else -> this[0]
    }
}
