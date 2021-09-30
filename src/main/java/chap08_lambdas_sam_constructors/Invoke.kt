package chap08_lambdas_sam_constructors

fun main(args: Array<String>) {
    // Regular f(x)
    val addOffset = { x: Int -> x + 1 }
    val addoffset1 : (Int) -> Int = { x : Int ->
        x + 1
    }
    addoffset1(5)
    addoffset1.invoke(6)
    println(addOffset(2))

    // Invoke
    println(addOffset.invoke(2))

    // Anonymous invocation
    println({ x: Int -> x + 1 }(2))
}
