/**
 * 变量
 */

var age: Int = 1
val school: String = "ZJU"
var sex = 0 //类型推断
val name = "lily"
var size: Any? = 1 //任何类型，可为空，kotlin会推断 size 是 Int 类型
val siza1 = size as? String // siza1 = null
val siza2 = size as? Int // siza2 = 1
var s1 = "a is 3"
val s2 = "$name, ${s1.replace("is", "was")} ever"

var grade: Int = if (age > 0) {
    println(age)
    1
} else if (age == 0) {
    0
} else {
    println(age)
    -1
}

var count: Int = when {
    age == 0 -> 1
    age > 0 -> 2
    else -> 3
}

/**
 * 函数
 */

fun getName(age: Int): String {
    var name = if (age < 18) {
        "girl"
    } else if (age < 30) {
        "miss"
    } else {
        "mrs"
    }
    return name
}

fun getName2(age: Int): String {
    return if (age < 18) {
        "miss"
    } else {
        "mrs"
    }
}

// 当函数体只有一个表达式时，可以用 等号 简化 函数体
fun getName3(age: Int): String = if (age < 18) {
    "miss"
} else {
    "mrs"
}

var hername = getName(18)

//用lambda表达式表示一个函数
var length: (String) -> Int = { input ->
    input.length
}

//简化lambda表达式
var length2: (String) -> Int = {
    it.length
}

var nLength = length("Android")

//高阶函数
fun strMapper(str: String, mapper: (String) -> Int): Int {
    return mapper(str)
}

fun goSchool() {
    //局部函数
    fun getCar(): Car {
        return Car()
    }

    val car = getCar();
    car.drive("ZJU")
}

fun printUnit(a: Int, b: Int): Unit { // Unit 相当于没有返回值
    println("$a + $b = ${a + b}")
}

/**
 * 类
 */

class Wheel {

}

class Car {
    var wheel = listOf<Wheel>()

    fun drive(destination: String): Boolean {
        return destination.length < 20
    }

    fun wash() {
        println("洗澡真舒服")
    }
}

class Bus(var wheels: List<Wheel>) {
    private var door: Int = 4;

    var type: String = "byd"
        get() {
            return field.toUpperCase()
        }
        set(value) {
            field = value.toLowerCase()
        }

    fun lockDoor(): Boolean {
        door.dec()
        return true
    }

    fun getWheelSize() = wheels.size
}

var car = Car()
var wheels = car.wheel
var bus = Bus(listOf(Wheel()));



