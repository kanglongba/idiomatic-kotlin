package guide8_sealedAndLateInit

import kotlin.properties.Delegates

/**
 * author: qonyqian
 * created on: 2021/8/29 12:06 下午
 * version：1.0
 * description:
 */
class KotlinLateInit {

    /**
     * 使用 lateinit 延迟初始化
     * 1、只能作用于 var 属性，且该属性没有自定义 get 和 set 方法
     * 2、该属性必须是非空类型，且不能是原生类型
     */
    lateinit var name: String

    /**
     * 使用 by lazy 延迟初始化
     * 1.只能作用于 val 属性
     * 2.调用属性的时候，才会执行初始化
     * 3.lazy是一个顶层函数，接收一个lambda表达式作为参数
     * 4.lambda表达式的最后一行返回结果
     */
    val school: String by lazy { "123" }

    constructor() {
        name = "edison"
    }

    fun getMyName(): String {
        return name.toUpperCase()
    }
}