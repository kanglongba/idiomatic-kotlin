package guide7_staticMethodAndStandardMethod

/**
 * author: qonyqian
 * created on: 2021/8/29 11:43 上午
 * version：1.0
 * description:
 */
object Singleton {

    var name = "edison"

    fun getMyName(): String {
        return "$name@${System.currentTimeMillis()}"
    }

    @JvmStatic
    fun getMyAge(): Int {
        return 90
    }
}