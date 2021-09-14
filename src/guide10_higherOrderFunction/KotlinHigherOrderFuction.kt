package guide10_higherOrderFunction

/**
 * author: qonyqian
 * created on: 2021/9/12 5:39 下午
 * version：1.0
 * description: 函数在kotlin里是一等公民。
 */

/**
 * 定义一个函数类型的变量
 */
val func1: (Int, Int) -> Boolean = { x: Int, y: Int ->
    println("x=$x")
    println("y=$y")
    x > y
}

/**
 * 类型推断，省略函数类型定义
 */
val func2 = { x: Int, y: Int ->
    println("x=$x")
    println("y=$y")
    x > y
}

/**
 * 类型推断，省略参数类型
 */
val func3: (Int, Int) -> Boolean = { x, y ->
    println("x=$x")
    println("y=$y")
    x > y
}

/**
 * lambda只有一个参数时，可省略，默认是it，也可以自定义。
 */
val func4: (Int) -> Boolean = {
    println("x=$it")
    it % 2 == 0
}

/**
 * 高阶函数：函数作为参数
 */
fun compare(x: Int, y: Int, func: (Int, Int) -> Boolean): Boolean {
    println("x=$x")
    println("y=$y")
    return func(x, y)
}

/**
 * 高阶函数：函数作为返回值
 */
fun compare(x: Int, y: Int): (Int, Int) -> Boolean {
    println("x=$x")
    println("y=$y")
    return if (x > y) {
        { a: Int, b: Int ->
            a > b
        }
    } else {
        { a, b ->
            a <= b
        }
    }
}

/**
 * 调用高阶函数
 */
fun compareThem(x: Int, y: Int): Boolean {
    return compare(x, y)(x, y)
}

/**
 * 高阶函数
 */
fun numberPlus(a: Int, b: Int, plus: (Int, Int) -> Int): Int {
    return plus(a, b)
}

/**
 * 普通函数
 */
fun plus(a: Int, b: Int): Int {
    return a + b
}

/**
 * 高阶函数里面直接引用普通函数
 */
fun plusThem(a: Int, b: Int) {
    //直接引用一个函数
    val result = numberPlus(a, b, ::plus)
    println("plus=$result")
}

fun main(vararg args: String) {
    plusThem(1, 2)
    //直接引用一个函数类型的变量
    compare(1, 2, func3)
    //用lambda表达式表示函数
    compare(2, 1, { a, b ->
        a > b
    })
    //如果lambda表达式是最后一个参数，可以移到括号外面
    compare(3, 2) { a, b ->
        a > b
    }
}

