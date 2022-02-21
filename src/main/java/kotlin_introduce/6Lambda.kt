package kotlin_introduce

/**
 * 逐步精简lambda
 */

//函数类型的参数
val sum: (Int, Int) -> Int = { one: Int, two: Int ->
    println("one=$one two=$two")
    one + two
}
val sum1: (Int, Int) -> Int = { one, two ->  //省略类型
    println("one=$one two=$two")
    one + two
}

val sum2 = { one: Int, two: Int ->  //省略变量声明
    println("one=$one two=$two")
    one + two
}

// 匿名类
val addImpl = object : Add {
    override fun add(one: Int, two: Int): Int {
        return one + two
    }
}