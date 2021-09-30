package guide3_classAndObject

/**
 * kotlin类加入open关键字，就可以继承了
 * author: qonyqian
 * created on: 2021/6/13 10:47 下午
 * version：1.0
 * description:
 */

open class OpenKotlinClass {
    var name = "Kotlin"
    var version = 1

    //方法默认也是final类型，如需要重写，也要加上open关键字
    open fun printInfo() {
        println("name=$name version=$version")
    }
}