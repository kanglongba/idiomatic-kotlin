/**
 * 使用标签控制程序执行
 */

fun main() {
    //标签
    sara@ for (i in 1..5) {
        println(i)
        for (j in 6..9) {
            println(j)
            if (i == 3 && j == 8) {
                break@sara //直接退出了大循环
            }
        }
    }

    fun foo(): Unit { // Unit 相当于什么也不返回
        listOf(21, 22, 23, 24, 25).forEach {
            println(it)
            if (it == 23) {
                return //因为 forEach 是一个 内联函数（inline），会直接从函数foo中返回。
            }
        }
        println("this point is unreachable")
    }

    //标签
    fun goo() {
        listOf(31, 32, 33, 34, 35).forEach hockey@{
            if (it == 33) {
                return@hockey //虽然 forEach 是一个 内联函数（inline），但我们使用标签控制返回位置，仅从foreach中返回。
            }
            println(it)
        }
        print(" done with explicit label")
    }

    foo()
    goo()
}