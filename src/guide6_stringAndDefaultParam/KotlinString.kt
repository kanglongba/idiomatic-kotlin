package guide6_stringAndDefaultParam

/**
 * Kotlin的字符串处理，可以直接在字符串中添加表达式
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