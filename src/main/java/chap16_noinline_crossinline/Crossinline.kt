package chap16_noinline_crossinline

//一个函数中，如果存在一个lambda表达式，在该lambda中不支持直接进行return退出.支持通过标签，退出lambda表达式(局部退出)。
//如果是具名函数或匿名函数，则支持直接进行return，退出当前函数（但不会退出外层函数）。
fun someHigherOrderFunction(message: String, func: (String) -> Unit) {
}

//但是如果函数是内联的，那么它的lambda表达式支持非局部控制流，即可直接使用裸return
inline fun someInlineHigherOrderFunction(message: String, func: (String) -> Unit) {
    func(message)
}

//直接在lambda返回外部函数的情况称为非局部返回
// https://www.kotlincn.net/docs/reference/inline-functions.html
// 在 Kotlin 中，我们只能对具名或匿名函数使用正常的、非限定的 return 来退出。 这意味着要退出一个 lambda 表达式，我们必须使用一个标签，并且在 lambda 表达
// 式内部禁止使用裸 return
// crossinline 标识该 lambda 表达式中不允许非局部控制流
//
// 首先，非内联函数的lambda表达示，不支持非局部返回（不能使用裸return，只能使用标签）
// 其次，内联函数的lambda表达式，没有这个限制，支持非局部返回（可以使用裸return，但由于内联，会直接返回外层函数）
// 所以，crossinline关键字，就用来标识在内联函数中，禁止lambda表达式使用非局部返回（本意是为了防止意外return，所以就干脆禁止了这种行为，那么就彻底没有这个风险了）
// crossinline 必须 与 inline（内联函数）配合使用
inline fun crossInlineTest(message: String, crossinline func: (String) -> Unit) {
    someHigherOrderFunction("crossInlineTest") {
        func(it) // Compile error here
    }
}

fun main(arg: Array<String>) {
    someHigherOrderFunction("Hello") {
        println(it)
        // Uncomment to experience compile error
        //return // Compile error here because crossinline does not allow return
    }
    var func = fun() { //匿名函数
        return
    }
    func()
    someInlineHigherOrderFunction("hello") {
        println(it)
        return
    }
    println("Main finished")
}
