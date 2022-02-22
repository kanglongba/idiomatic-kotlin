package guide16_destructure

/**
 * author: qonyqian
 * created on: 2022/1/12 11:43 下午
 * version：1.0
 * description:
 *
 * Kotlin 解构
 * 参考文章：
 * 1.https://juejin.cn/post/6983967718273712141  【非常好，一看就懂】
 * 2.https://www.kotlincn.net/docs/reference/multi-declarations.html
 *
 * 把一个对象拆解成多个变量，叫做解构
 * 1.只有数据类（data class）可以被解构
 * 2.普通类要想支持解构，要自己声明componentN()函数，需要用到 operator 修饰符
 * 3.可以使用下划线，忽略对某个变量的解构
 */


fun main(vararg args: String) {
    val tencent = Tencent("pony", "mark", "xidan")
    //解构Tencent
    val (ceo, coo, cpo) = tencent
    println("ceo=$ceo, coo=$coo, cpo=$cpo")

    //使用下划线忽略coo
    val (ceo1, _, cpo1) = tencent
    println("ceo1=$ceo1, cpo1=$cpo1")

    //自定义解构
    val alibaba = Alibaba("逍遥子", "鲁肃")
    val (ceo3, cto) = alibaba
    println("ceo3=$ceo3, cto=$cto")

    //lambda表达式中使用解构
    val age = alibaba.let { (ceo, cto) ->
        println("$ceo, $cto")
        102
    }

    //还可以再数组、集合中使用解构
    val array = arrayOf(1, 2, 3)
    val (a, b, c) = array
    println("-> a: $a b: $b c: $c")

    val list = listOf(1, 2, 3)
    val (d, e, f) = list
    println("-> d: $d e: $e f: $f")

    val map = mapOf("key1" to 1, "key2" to 2, "key3" to 3)
    for ((key, value) in map) {
        println("-> $key : $value")
    }
}

/**
 * 数据类可以直接解构
 */
data class Tencent(val ceo: String, val coo: String, val cpo: String) {

}

/**
 * 普通类需要自己声明解构函数，才可以支持解构。需要使用 operator 关键字。
 */
class Alibaba(val ceo: String, val cto: String) {
    operator fun component1(): String = ceo
    operator fun component2(): String = cto
}