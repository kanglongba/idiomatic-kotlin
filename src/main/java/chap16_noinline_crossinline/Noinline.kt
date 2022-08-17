package chap16_noinline_crossinline

// 关闭传给内联函数的 lambda 表达式的内联。即切断内联函数的传导。
// noinline 和 crossinline 都是用来修饰lambda表达式的
inline fun <reified T> doSomething(obj: T, noinline func: (T) -> T) {
    func(obj)
}

fun main(args: Array<String>) {
    doSomething(String()) { it }
}