package chap15_reified_parameters

//内联函数、物化泛型，可以保存泛型的参数类型
inline fun <reified T> doSomethingWithType(obj: T) {
    val typeName = T::class.java
    println(typeName)
}

fun main(args: Array<String>) {
    doSomethingWithType(String())
}