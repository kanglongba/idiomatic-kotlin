package guide6_stringAndDefaultParam

/**
 * Kotlin的字符串处理，可以直接在字符串中添加表达式
 * 1.Kotlin 中，字符串里面可以使用 ${} 引用变量值和表达式，当 {} 里面只有一个变量，非表达式时，{}也可以去掉
 * author: qonyqian
 * created on: 2021/8/28 10:47 下午
 * version：1.0
 * description:
 */
class KotlinString {

    val myAge = 99

    //引用方法
    fun getMyName(): String {
        return "edison@${System.currentTimeMillis()}"
    }

    //引用表达式
    fun getMyGrade(): String {
        return "high ${1 + 2}"
    }

    fun printInfo() {
        //引用变量
        println("PersonInfo: name=${getMyName()} age=$myAge grade=${getMyGrade()}")
    }
}