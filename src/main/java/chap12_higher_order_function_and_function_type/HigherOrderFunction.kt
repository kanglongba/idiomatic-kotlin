package chap12_higher_order_function_and_function_type

/**
 * author: qonyqian
 * created on: 2021/5/30 6:35 下午
 * version：1.0
 * description:
 */
class HigherOrderFunction {

    /**
     * 用lambda表示函数
     */
    val filter : (Int) -> Boolean = { it < 2 }

    val filter1 : (Int) -> Boolean = { value ->
        value < 2
    }

    /**
     * 类型推断，省略函数类型
     */
    val filter2 = { x : Int -> x < 2 }

    /**
     * 简化lambda，推断不出参数类型
     */
//    val filter3 = {
//        it < 2
//    }

    val filter4 : (Int) -> Boolean = {
        it < 2
    }

    /**
     * 匿名函数
     */
    val filter5 = fun(x : Int) : Boolean = x < 2

    val filter6 = fun(x : Int) : Boolean {
        return x < 2
    }

    /**
     * 函数引用
     */
    val filter7 = this::filterFunc

    fun filterFunc(value : Int) : Boolean {
        return value < 2
    }

    fun callFilter() {
        val result : Boolean = filter(5)
        val result1 : Boolean = filter5(5)
        val result7 : Boolean = filter7(5)
        val result8 : Boolean = filterFunc(5)
        filter5.invoke(5)
    }
}