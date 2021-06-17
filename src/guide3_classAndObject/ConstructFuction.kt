package chap08_lambdas_sam_constructors
//Kotlin的package路径不必和文件路径相对应，可以随便定义

/**
 * 在kotlin中：
 * 1.如果父类有构造函数，子类要显式继承父类的构造函数（Java是隐式继承）
 * 2.主要构造函数和次要构造函数都可省略
 * 3.如果存在主要构造函数，次要构造函数要直接或间接继承主要构造函数。
 * 4.如果没有主要构造函数，次要构造函数之间可以不必相互继承
 *
 * author: qonyqian
 * created on: 2021/6/13 6:19 下午
 * version：1.0
 * description:
 */

/**
 * 父类有构造函数(无论主次)的情况
 */
open class Person {
    var name : String = "judy"
    get() {
        return field.toUpperCase()
    }
    set(value) {
        field = value.toLowerCase()
    }
    var age = 15

    constructor(name : String) {
        this.name = name
    }

    //如果这样定义，会和上面的构造函数冲突，因为函数签名是一样的，编译器区分不出来
//    constructor(name1 : String) {
//
//    }

    constructor(name : String, age: Int) {
        this.name = name
        this.age = age
    }

    constructor(age: Int) : this("Mike") {
        this.age = age
    }

    fun print() : Unit {
        print("name=$name, age=$age")
    }
}

/**
 * 父类有构造函数的情况：
 * 1.子类必须显式继承一个
 * 2.子类可以没有构造函数
 */
class Student : Person("Lily") {

}

/**
 * 父类有构造函数的情况：
 * 1.子类有主要构造函数
 */
class Driver(age : Int) : Person("Mike") {
    init {
        //init代码块是主要构造函数的初始化代码
        this.age = age
    }
}

/**
 * 父类有构造函数的情况：
 * 1.子类有主要构造函数和次要构造函数
 */
class Teacher(var city: String) : Person("Lucy", 30) {
    //主构造函数中用var或val关键字修饰变量，变量会自动添加为类的成员变量，并且被构造函数自动初始化

    init {
        println("city=$city")
    }

    //次要构造函数继承主要构造函数
    constructor(career: Int) : this("Hangzhou") {
        println("career=$career")
    }

    //次要构造函数也可以间接继承主要构造函数
    constructor(career: Int, subject: String) : this(career) {
        println("career=$career subject=$subject")
    }

}

/**
 * 父类有构造函数的情况：
 * 1.子类只有次要构造函数会报错
 */
class Sporter : Person(18) {

//    constructor(sport: String) : super("Mike") {
//
//    }

}

/**
 * 没有任何构造函数
 */
open class Car {
    var width : Int = 2
    var height : Int = 2
    var name = "car"

    fun print() : Unit {
        print("$name width=$width height=$height")
    }
}

/**
 * 父类没有构造函数的情况：
 * 1.子类也没有构造函数，继承的父类要有括号
 */
class BYD : Car() {

}

/**
 * 父类没有构造函数的情况：
 * 1.子类有主构造函数，继承的父类要有括号
 */
class Changcheng(var cost :Int) : Car() {

    /**
     * 次要构造函数继承主要构造函数
     */
    constructor(name: String) : this(1000){

    }

}

/**
 * 父类没有构造函数的情况：
 * 1.子类只有次构造函数，继承的父类不能有括号
 */
class Jili : Car {
    var cost = 0

    constructor(cost: Int) {
        this.cost = cost
    }
}

/**
 * 1.父类没有构造函数
 * a.子类如果没有构造函数，或者有主要构造函数，继承的父类要有括号。
 * b.子类如果只有次构造函数，继承的父类不能带有括号。
 * 总结：没有就要继承一个，声明了主构造函数就要确立继承关系，次构造函数是自己的不应该继承
 *
 * 2.父类有构造函数
 * a.子类必须继承一个父类的构造函数
 * b.子类可以没有构造函数
 * c.子类可以只有主构造函数
 * d.子类不能只有次构造函数
 * 总结：有就必须继承一个，可以没有任何构造函数，有次要就必须有主要
 */
fun main(vararg args: String) {
    //子类无论继承了多少父类的构造函数，只能用自己的构造函数初始化
    var student = Student()
    student.print()
    var driver = Driver(19)
    var teacher = Teacher(18)
    val sporter = Sporter()
    var byd = BYD()
    var changcheng = Changcheng(100)
    var jili = Jili(200)
}