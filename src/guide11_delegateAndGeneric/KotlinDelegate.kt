package guide11_delegateAndGeneric

import kotlin.reflect.KProperty

/**
 * 委托分为类委托和属性委托：
 * 1.类委托的核心思想是：将一个类的一些具体实现委托给另一个类去完成。
 * 2.属性委托的核心思想：是将一个属性的具体实现委托给另一个类去完成。
 * author: qonyqian
 * created on: 2021/9/15 4:58 下午
 * version：1.0
 * description:
 */

interface IInterface {
    fun method1()
    fun method2()
    fun method3()
}

class OneInterface : IInterface {
    override fun method1() {
        println("OneInterface method1")
    }

    override fun method2() {
        println("OneInterface method2")
    }

    override fun method3() {
        println("OneInterface method3")
    }
}

/**
 * 类委托。OneInterface和TwoInterface实现同一个接口。TwoInterface中IInterface接口的方法，即可由OneInterface委托实现，也
 * 可以由TwoInterface自己实现。
 * 这样可以避免Java中代理模式中的很多模板代码。算是在语言层面，通过 by 关键字支持了代理模式。
 */
class TwoInterface(one: OneInterface) : IInterface by one {

    override fun method3() {
        println("TwoInterface method3")
    }

    fun method4() {
        println("TwoInterface method4")
    }
}

/**
 * 使用 by 关键字连接了左边的 one 属性和右边的 Delegate 实例
 * 这种写法就代表着将 one 属性的具体实现委托给了 Delegate 去完成
 */
class MyClass {

    /**
     * 属性委托。
     * 由于Kotlin有类型推断，所以也可以省略类型。
     */
    var one: OneInterface by Delegate()

    /**
     * 属性延迟委托。当调用的时候才会执行初始化。
     * 只能用于修饰 val 类型的属性。
     *
     * 默认情况下，对于 lazy 属性的求值是同步锁的（synchronized）：该值只在一个线程中计算，并且所有线程会看到相同的值。如果初始
     * 化委托的同步锁不是必需的，这样多个线程可以同时执行，那么将 LazyThreadSafetyMode.PUBLICATION 作为参数传递给 lazy() 函
     * 数。 而如果你确定初始化将总是发生在与属性使用位于相同的线程， 那么可以使用 LazyThreadSafetyMode.NONE 模式：它不会有任
     * 何线程安全的保证以及相关的开销。
     */
    val two: TwoInterface by lazy {
        println("延迟委托")
        val oneIn = OneInterface()
        TwoInterface(oneIn)
    }
}

/**
 * 属性委托类有固定的代码模板：
 * 一、getValue 方法和setValue 方法必须使用 operator 关键字修饰
 *
 * 二、getValue 方法主要接收两个参数：
 * 1、第一个参数表明 Delegate 类的委托功能可以在什么类中使用
 * 2、第二个参数 KProperty<*> 是 Kotlin 中的一个属性操作类，
 *    可用于获取各种属性的相关值，<*>这种泛型的写法类似 Java 的
 *    <?>，表示我不关心泛型的具体类型
 *
 * 三、setValue 方法也是相似的，接收三个参数：
 * 1、前面两个参数和 getValue 是一样的
 * 2、第三个参数表示具体要赋值给委托属性的值，这个参数的类型必须和
 *    getValue 方法返回值的类型保持一致
 *
 *
 * 一种特殊情况：用 val 定义的变量不需要实现 setValue 方法，因为 val
 *             关键字声明的属性只可读，赋值之后就不能更改了
 */
class Delegate {

    var propValue: OneInterface = OneInterface()

    operator fun getValue(any: Any?, prop: KProperty<*>): OneInterface {
        return propValue
    }

    operator fun setValue(any: Any?, prop: KProperty<*>, value: OneInterface) {
        propValue = value
    }
}

fun main(vararg arg: String) {
    val two = TwoInterface(OneInterface())
    two.method1()
    two.method2()
    two.method3()
    two.method4()
}