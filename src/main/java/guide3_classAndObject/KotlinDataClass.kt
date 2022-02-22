package guide3_classAndObject

/**
 * 数据类会自动重写 toString、equals、hashCode三个方法
 * author: qonyqian
 * created on: 2021/6/14 12:17 上午
 * version：1.0
 * description:
 */
//默认参数
//数据类必须有主改造函数，且参数类型必须是var或val。 主构造函数中，被var和val修饰的变量，会自动变成类的成员变量。
data class KotlinDataClass(var name: String, var age: Int = 18) {
    var address: String = "south"

    //执行主构造函数时，会自动执行 init 代码块。因此，init 代码块可以当成主构造函数的函数体。
    init {
        address = if (age > 20) "zhejiang" else "hangzhou"
    }

    //次构造函数。必须显示调用主构造函数。
    constructor(address: String) : this("Mike", 18) {
        this.address = address
    }

    //默认参数
    fun printInfo(school: String = "zju") {
        println("name=$name school=$school address=$address")
    }

    //默认参数。Kotlin的函数默认值，可以消除方法重载。
    fun printInfo(school: String = "zju", sex: String = "male") {
        println("name=$name school=$school sex=$sex address=$address")
    }
}