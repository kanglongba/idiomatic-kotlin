package guide7_staticMethodAndStandardMethod

/**
 * author: qonyqian
 * created on: 2021/8/29 10:29 上午
 * version：1.0
 * description:
 */
class Student {
    var name = "edison"
    var age = 99
    var grade = 1
    var school = "CX"

    fun getMyScore(): Int {
        return 5
    }

    /**
     * Student对象原本没有这些运算符，利用运算符重载，使Student对象也支持运算符操作
     * 运算符重载，需要使用 operator 关键字。
     */
    operator fun plus(other: Student): Int {
        return age + other.age
    }

    operator fun inc(): Student {
        age++
        return this
    }

    operator fun minus(other: Student): Int {
        return age - other.age
    }
}