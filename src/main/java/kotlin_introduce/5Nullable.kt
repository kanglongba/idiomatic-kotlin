class A {
    var code = 0
    fun getMyCode(): Int {
        return code
    }
}

lateinit var e: A // lateinit 表示延迟初始化，保证在用到变量的时候，变量已经初始化完成。

val p: String by lazy { // by lazy 修饰的变量，会在第一次使用到的时候再初始化
    println("延迟初始化 p")
    "123"
}

/**
 * 1.声明的变量，必须初始化
 * 2.lateinit 可以延迟初始化
 * 3.非空变量不能赋予空值
 * 4.只有可空变量可以赋予空值
 */
fun main() {
    val a = A()
    a.code = 3
    a.getMyCode()

    // 变量类型后面接?，表示变量为可空类型
    val b: A? = A()
    b?.code = 4 // ?. 表示不为空则执行
    b!!.getMyCode() // !!. 表示保证变量不为空，编译器不要再检查了

    // 只有可空类型，才能赋予空值
    var d: A? = null
    var myCode: Int? = d?.getMyCode() // 可空类型具有传递性，myCode也是个可空类型，可省略 myCode 的可空声明。

    var e = d ?: a // ?: 是 If not null and else 的缩写，表示如果 d 不为空，就把 d 赋值给 e，否则把 a 复制给 e。
    println(e.getMyCode())

    //延迟初始化
    e = A()
    e.getMyCode()
}