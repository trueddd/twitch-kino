package chrome.scripting

@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
inline fun ScriptInjection(block: ScriptInjection.() -> Unit) = (js("{}") as ScriptInjection).apply(block)

@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
inline fun Target(block: InjectionTarget.() -> Unit) = (js("{}") as InjectionTarget).apply(block)
