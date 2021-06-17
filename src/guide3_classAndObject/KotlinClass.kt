package guide3_classAndObject

/**
 * Kotlin类：文件名=类名。Kotlin类默认是final类型，不能继承。方法默认也是final类型。
 * author: qonyqian
 * created on: 2021/6/13 10:34 下午
 * version：1.0
 * description:
 */
class KotlinClass {
    //变量默认是public类型。
    //Kotlin自动为变量创建get和set方法，可重写get和set方法。
    var name = "Kotlin"
    get() { //field代指变量本身
        return field.toUpperCase()
    }
    set(value) {
        field = value.toLowerCase()
    }
    var size = 1

    //val只能读不能写，所以只有get方法，没有set方法
    val sex = "male"

    //函数默认也是public类型
    fun printInfo() {
        //实际上调用了name的get方法，所以打印出来是大写：KOTLIN
        println("name=$name size=$size sex=$sex")
    }

    //私自创建get/set方法，编译器会报错
//    fun setSize(size: Int) {
//        this.size = size
//    }
//
//    fun getSize() : Int = size
}