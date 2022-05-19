@file:JsQualifier("chrome.scripting")

package chrome.scripting

import kotlin.js.Promise

external fun executeScript(injection: ScriptInjection): Promise<InjectionResult>

external interface ScriptInjection {

    var args: Array<dynamic>?

    var func: dynamic

    var target: InjectionTarget
}

external interface InjectionTarget {

    var tabId: Int
}

external interface InjectionResult {

    val frameId: Int

    val result: dynamic
}
