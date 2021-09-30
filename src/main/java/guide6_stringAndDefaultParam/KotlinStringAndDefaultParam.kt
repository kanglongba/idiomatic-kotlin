package guide6_stringAndDefaultParam

/**
 * author: qonyqian
 * created on: 2021/8/28 10:55 下午
 * version：1.0
 * description:
 */

fun main(vararg args: String) {
    val kotlinString = KotlinString()
    kotlinString.printInfo()

    val kotlinDefaultParam = KotlinDefaultParam()
    kotlinDefaultParam.printMyInfo()
}