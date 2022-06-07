@file:JsQualifier("chrome.tabs")

package chrome.tabs

import kotlin.js.Json
import kotlin.js.Promise

external fun query(queryInfo: QueryInfo): Promise<Array<Tab>>

external fun sendMessage(tabId: Int, message: Json): Promise<dynamic>

external interface QueryInfo {

    var active: Boolean
}

external interface Tab {

    var id: Int
}
