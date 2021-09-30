package guide3_classAndObject

/**
 * 单例类中可以定义常量
 * author: qonyqian
 * created on: 2021/6/14 12:47 上午
 * version：1.0
 * description:
 */
object KotlinSingleton {
    var name = "Mike"
    const val CITY = "杭州"

    fun printName() {
        println("name=$name")
    }

    fun printInfo(age: Int = 18) {
        println("name=$name age=$age")
    }
}