@file:Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")

package chrome.tabs

fun QueryInfo(block: QueryInfo.() -> Unit) = (js("{}") as QueryInfo).apply(block)

fun query(block: QueryInfo.() -> Unit) = query(QueryInfo(block))
