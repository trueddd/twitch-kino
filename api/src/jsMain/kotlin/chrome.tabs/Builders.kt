package chrome.tabs

@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
inline fun QueryInfo(block: QueryInfo.() -> Unit) = (js("{}") as QueryInfo).apply(block)
