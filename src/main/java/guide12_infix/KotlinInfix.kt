package guide12_infix

/**
 * infix类型的函数又叫做 中缀函数
 * 使用 infix 关键字创建一个infix类型的函数，通常来说infix函数都是扩展函数。
 * author: qonyqian
 * created on: 2021/9/16 4:40 下午
 * version：1.0
 * description:
 */

/**
 * 这里的 to 就是一个 infix 类型的函数
 */
val map1 = mapOf("qq" to "tencent", "taobao" to "alibaba")

/**
 * 定义一个 infix 函数：在函数前面加上 infix 关键字，就可以声明这是一个 infix 函数
 */
public infix fun <A, B> A.eat(that: B): Pair<A, B> = Pair(this, that)

public infix fun <A, B> A.with(that: B): Pair<A, B> {
    return Pair(this, that)
}

/**
 * 使用新定义的 infix 函数
 */
val map2 = mapOf("wechat" eat "tencent", "tmall" with "alibaba", "douyin" to "bytedance")

/**
 * 定义一个 infix 函数
 */
infix fun Int.bigger(that: Int): Boolean {
    return this > that
}

val res = 5 bigger 4