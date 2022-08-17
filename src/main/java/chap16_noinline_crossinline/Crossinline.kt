package chap16_noinline_crossinline

fun someHigherOrderFunction(message: String, func: (String) -> Unit) {
}

// https://www.kotlincn.net/docs/reference/inline-functions.html
// 在 Kotlin 中，我们只能对具名或匿名函数使用正常的、非限定的 return 来退出。 这意味着要退出一个 lambda 表达式，我们必须使用一个标签，并且在 lambda 表达式内部禁止使用裸 return
// crossinline 标识该 lambda 表达式中不允许非局部控制流
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
    println("Main finished")
}
