package guide17_typealias

/**
 * author: qonyqian
 * created on: 2022/1/13 3:39 下午
 * version：1.0
 * description:
 *
 * Kotlin 类型别名。Dart中也有类似的语法。
 */

open class BaseAction(open val msg: String)

/**
 * 给 BaseAction 起一个类型别名
 */
typealias StoreAction = BaseAction

/**
 * 给 lambda 起一个类型别名
 * 利用类型别名
 */
typealias Middleware = (recorder: (StoreAction) -> Unit) -> StoreAction

/**
 * 给 lambda 起一个类型别名
 */
typealias AccessTouchedModels = (x: Float, y: Float) -> List<Any>

/**
 * 使用类型别名声明一个变量
 */
var accessModels: AccessTouchedModels = { _, _ -> emptyList() }

/**
 * 使用类型别名作为参数
 */
fun registerAccessTouchedModels(accessTouchedModels: AccessTouchedModels) {
    accessModels = accessTouchedModels
}