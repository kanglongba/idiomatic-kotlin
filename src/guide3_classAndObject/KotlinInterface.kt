package guide3_classAndObject

/**
 * author: qonyqian
 * created on: 2021/6/14 12:05 上午
 * version：1.0
 * description:
 */
interface KotlinInterface {
    //kotlin中的接口，不能定义变量
    //val name = "Mike"

    fun plus(x : Int, y : Int) : Int

    //kotlin中的方法，可以有默认实现。类似于Java8中的default关键字
    fun minus(x: Int,y: Int) : Int {
        return x-y
    }

    fun divide(x: Int,y: Int) : Int = x/y
}