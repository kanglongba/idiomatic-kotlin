/**
 * 类构造函数、扩展函数：let、run、also、apply、with
 */

//主要构造函数
class Doctor constructor(name: String) {

    fun getName(): String {
        return name
    }
}

class ClassExtend {
    var className: String = ""

    //次要构造函数
    constructor(className: String) {
        this.className = className
    }

}

//主要构造函数可省略 constructor 关键字
class Sale(name: String) {
    var age: Int = 20

    var page: Int = 1
        get() {
            return field + 1
        }
        set(value) {
            field = value - 1
        }

    //初始化代码块，主构造函数之后，次构造函数之前执行
    init {
        println("her name is $name")
    }
}

//主构造函数为空
class Human() {
    private val children: MutableList<Human> = mutableListOf()

    //次要构造函数直接或间接继承主构造函数
    constructor(parent: Human) : this() {
        parent.children.add(this)
    }
}

//没有任何构造函数
class Driver {
    var name = "擎天柱"

    fun drive() {
        println("骑着我心爱的小摩托")
    }
}

/**
 * String 的扩展函数
 */
fun String.appendHello(): String {
    return "$this hello"
}

/**
 * Driver的扩展函数
 */
fun Driver.clearCar(car: Car) {
    println("${this.name} 洗车")
    car.wash()
}

/**
 * https://cloud.tencent.com/developer/article/1591238
 */
fun main() {
    val doctor = Doctor("喜来乐")
    var classExtend = ClassExtend("lucy")
    val sale = Sale("lily")
    val father = Human()
    var child = Human(father)
    val driver = Driver()

    // let返回lambda表达式处理后的结果
    val num1 = sale.let {
        it.age = 33
        it.hashCode()
        4
    }
    println(num1) // num1 = 4

    // run也返回lambda表达式处理后的结果
    var num2 = sale.run {
        age = 34
        hashCode()
        999
    }
    println(num2) // num2 = 999

    // also返回扩展对象自己
    var num3 = sale.also {
        it.age = 25
        it.hashCode()
        it.page = 5
        4
    }
    println(num3.age) // num3就是对象本身，num3.age = 25

    // apply返回扩展对象自己
    var num4 = sale.apply {
        age = 18
        hashCode()
        888
    }
    println(num4.age) // num4就是对象本身，num4.age = 18

    // with返回lambda表达式处理后的结果，类似于 run
    val num5 = with(sale) {
        hashCode()
        println("她的年龄：$age")
        777
    }
    println(num5) // num5 = 777

    println(sale.page) // sale.page = 5

    println(doctor.getName().appendHello())
    println(driver.clearCar(Car()))
}





