package guide7_staticMethodAndStandardMethod

/**
 * 单例对象，可获得类似静态方法的调用形式
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

    //通过 @JvmStatic 注解定义真正的静态方法
    @JvmStatic
    fun getMyAge(): Int {
        return 90
    }
}