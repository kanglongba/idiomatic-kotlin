package guide10_higherOrderFunction

/**
 * 内联函数：可以消除 Lambda 表达式运行时带来的开销。
 * 参数中的 Lambda 表达式在编译后变成了 Function 接口的匿名类实现，这就是 Lambda 表达式的底层转换逻辑，因此我们每调用
 * 一次 Lambda 表达式，都会创建一个新的匿名类实例，这样就会造成额外的内存和性能开销。但是我们使用内联函数，就可以很
 * 好地去解决这个问题。
 * 内联函数的工作流程：Kotlin 编译器会把内联函数中的代码在编译的时候自动替换到调用它的地方 ，这样也就不存在运行时的开销了
 * author: qonyqian
 * created on: 2021/9/14 11:31 下午
 * version：1.0
 * description:
 */

/**
 * 定义高阶函数时加上 inline 关键字修饰，我们就可以把这个函数称之为内联函数
 * 第一步替换过程：Kotlin 编译器会把 Lambda 表达式中的代码替换到函数类型参数调用的地方（内联函数内部）
 * 第二步替换过程：Kotlin 编译器会把内联函数中的全部代码替换到函数调用的地方（内联函数外部）
 */
inline fun numberPlus2(a: Int, b: Int, func: (Int, Int) -> Int): Int {
    return func(a, b)
}

/**
 * 使用 noinline 关键字修饰的函数类型参数，表示该函数类型参数不需要进行内联
 * 一般使用 noinline 关键字，是在一个内联函数中存在多个函数类型的参数
 */
inline fun inlineFunc(block1: () -> Unit, noinline block2: () -> Unit) {

}

/**
 * 1、内联函数在编译的时候会进行代码替换，因此它没有真正的参数属性，它的函数类型参数只能传递给另外一个内联函数，而非内联函数的函数类
 * 型参数可以自由地传递给其他任何函数
 * 2、内联函数所引用的 Lambda 表达式可以使用 return 关键字来进行函数返回，非内联函数所引用的 Lambda 表达式可以使用 return@Method
 * 语法结构来进行局部返回
 *
 * 情况1：非内联函数所引用的 Lambda 表达式不可以使用 return 关键字来进行局部返回，只能使用 return@Method 进行局部返回
 */
fun printString(str: String, block: (String) -> Unit) {
    println("printString1 start...")
    block(str)
    println("printString1 end...")
}

/**
 * 情况2：内联函数所引用的 Lambda 表达式可以使用 return 关键字来进行函数返回，也可以使用 return@Method 进行局部返回
 *
 * 因为内联函数直接进行代码替换，return获得了全局返回效果。
 */
inline fun printString2(str: String, block: (String) -> Unit) {
    println("printString2 start...")
    block(str)
    println("printString2 end...")
}

/**
 * 使用 crossinline 关键字保证内联函数的 Lambda 表达式中一定不会使用 return 关键字，但是还是可以使用 return@Method 语法结构
 * 进行局部返回，其他方面和内联函数特性一致。
 */
inline fun runRunnable(crossinline block: () -> Unit) {
    println("runRunnable start...")
    val runnable = Runnable {
        block()
    }
    runnable.run()
    println("runRunnable end...")
}


fun main(vararg arg: String) {
    println("main start...")
    val str = ""
    test1(str)
    test2(str)
    test3(str)
    println("main end...")
}

fun test1(str: String) {
    printString(str) {
        println("lambda1 start...")
        /**
         * 1，非内联函数不能直接使用 return 关键字进行局部返回
         * 2，需要使用 return@printString 进行局部返回(从lambda表达式中返回，回到外层函数)
         */
        if (str.isEmpty()) return@printString
        println(it)
        println("lambda1 end...")
    }
}

fun test2(str: String) {
    printString2(str) {
        println("lambda2 start...")
        /**
         * 因为内联函数会进行代码替换，因此这个 return 就相当于外层函数调用的一个返回，具有函数返回效果
         */
        if (str.isEmpty()) return
        println(it)
        println("lambda2 end...")
    }
}

fun test3(str: String) {
    runRunnable {
        println("lambda3 start...")
        /**
         * runRunnable函数中创建了一个 Runnable 对象，在 Runnable 中的 Lambda 表达式中调用了函数类型参数，
         * Runnable 中的 Lambda 表达式在编译的时候会被转换成匿名内部类的形式，内联函数允许我们在函数类型参数
         * 的 Lambda 表达式中使用 return 关键字进行函数返回，但是由于我们是在 Runnable 匿名类中调用的函数类
         * 型参数，此时是不可能进行外层调用函数返回的，最多是在匿名函数中进行返回，因此为了避免歧义，使用 crossinline
         * 关键字表示禁止使用 return 返回，但是可以使用 return@Method 进行局部返回。
         */
        return@runRunnable
        println("lambda3 end...")
    }
}






