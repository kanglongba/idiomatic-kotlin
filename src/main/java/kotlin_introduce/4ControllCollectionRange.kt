package edison.demo //Kotlin中File或Class的包路径可以与它们的实际路径不一致。所以Kotlin中可以随意设置包路径。

/**
 * when
 */
fun getType(obj: Any): String {
    return when (obj) {
        is Int -> "Integer"
        is String -> "String"
        else -> "null"
    }
}

/**
 * when
 */
fun max(a: Int, b: Int): Int {
    return when {
        a > b -> {
            println("a is $a, b is $b")
            a
        }
        else -> {
            b
        }
    }
}

/**
 * if
 */
fun max2(a: Int, b: Int): Int {
    return if (a > b) {
        println("a is $a, b is $b")
        a;
    } else {
        b;
    }
}

/**
 * 像 max1 和 max2 这种函数体只有一个表达式，可以用 = 简化 函数体
 */
fun max3(a: Int, b: Int) = if (a > b) a else b

/**
 * 返回类型可为空
 */
fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        return obj.length
    }
    return null
}

var items = listOf("铭银", "茂林", "罗俊")

/**
 * is、in、range
 */
fun main() {
    // for 循环
    for (item in items) {
        println(item)
    }

    // while 循环
    val it = items.iterator()
    while (it.hasNext()) {
        println(it.next())
    }

    // when，相当于switch-case
    println(getType(1688))

    // .. 表示一个Range，属于运算符重载，左闭右闭。对集合与Range，可以使用 in 操作符
    val company = listOf("Tencent", "Alibaba", "Baidu")
    if (2 in 0..company.size) {
        println(company[2])
    }
    if (5 !in 0..company.size) {
        println("5 is out of range")
    }
    if ("ByteDance" !in company) {
        println("ByteDance NB")
    }
    if ("Baidu" in company) {
        println("Baidu NB")
    }

    // indices 表示 list 的下标区间 [0, size-1]，也是一个Range
    if (company.size !in company.indices) {
        println("list size is out of valid list indices range, too")
    }
    //遍历列表
    for (element in company) {
        println(element)
    }
    //遍历列表
    for (index in company.indices) {
        println(company[index])
    }

    // for循环，区间 [1, 10] 但每次步进2：1、3、5、7、9
    for (x in 1..10 step 2) {
        println(x)
    }
    // for 循环 downTo 属于 中缀函数 (infix)，左闭右闭，但每次步进3：29、26、23、20
    for (x in 29 downTo 20 step 3) {
        println(x)
    }
    for (x in 30 until 35 step 1) { //左闭右开
        println("$x")
    }

    //集合的流式操作
    val tencent = listOf("pcg", "pc", "pgc", "pjhg", "csig", "teg", "ieg", "wxg", "cdg")
    tencent.filter { it.startsWith("p") }
        .sortedBy { it.length }
        .map { it.toUpperCase() }
        .forEach { println(it) }

    //map
    val map = mutableMapOf("a" to 1, "b" to 2, "c" to 3) // to 也是一个中缀函数
    println(map["b"])
    map["b"] = 4
    println(map.get("b"))

    //遍历一个map
    for ((k, v) in map) {
        println("$k $v")
    }
}



