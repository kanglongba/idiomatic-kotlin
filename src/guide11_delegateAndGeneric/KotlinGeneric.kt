package guide11_delegateAndGeneric

/**
 * author: qonyqian
 * created on: 2021/9/15 12:19 上午
 * version：1.0
 * description:
 */

/**
 * 1.为类定义一个泛型
 */
class Number<T> {
    fun method(param: T) {
        println(param)
    }

    /**
     * 2.为方法定义一个泛型
     */
    fun <R, V> method2(param: T, param2: R, block: (T, R) -> V): V {
        return block(param, param2)
    }

    fun <R> method3(param: T, param2: R): R {
        return param2
    }

    /**
     * 为泛型指定上界。类似于Java中的extend关键字
     * Kotlin中没有下界，而Java中有super关键字
     */
    fun <R : kotlin.Number> method4(param: R) {

    }
}

/**
 * 3.为接口定义泛型
 */
interface NInterface<T> {
    fun method(param: T)
    fun <R> method2(param: T): R
}

fun main(vararg arg: String) {
    val num1 = Number<Int>()
    num1.method(5)
    val res1 = num1.method2(5, "edison") { p1, p2 ->
        p2.length > p1
    }
    println("res1=$res1")

    val num2 = Number<String>()
    num2.method("edison")
    val res2 = num2.method2("edison", 6) { p1, p2 ->
        p1.length < p2
    }
    //方法添加泛型参数
    num2.method3<Int>("edison", 2)
    //类型推断，省略泛型
    num2.method3("edison", 2)
    //指定泛型上界
    num2.method4(5)
    num2.method4(5.5)
    println("res2=$res2")
}