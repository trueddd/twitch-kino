@file:JsQualifier("chrome.runtime")

package chrome.runtime

external val onMessage: OnMessageEvents

external interface OnMessageEvents {

    fun addListener(callback: (changes: dynamic) -> Unit)
}
