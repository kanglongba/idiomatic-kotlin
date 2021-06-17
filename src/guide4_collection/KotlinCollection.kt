package guide4_collection

/**
 * author: qonyqian
 * created on: 2021/6/14 12:24 下午
 * version：1.0
 * description:
 * commit4
 * commit5
 */
//不可变集合
var list1 = listOf("Mike", "Lily", "Lucy")
var array1 = arrayOf("Mike", "Lily", "Lucy")
var set1 = setOf("Mike", "Lily", "Lucy")
var map1 = mapOf("Mike" to 10, "Lily" to 20, "Lucy" to 30) //to实际上是一个中缀(infix)函数

//不可变集合
var list2 = mutableListOf("Mike", "Lily", "Lucy")
var set2 = mutableSetOf("Mike", "Lily", "Lucy")
var map2 = mutableMapOf("Mike" to 10, "Lily" to 20, "Lucy" to 30)

//vararg关键字是可变参数的意思，等于Java中的String...
fun main(vararg args: String) {
    list1.get(1)
    list1[0]
    array1[0]
    map1["Mike"]
    map1.get("Mike")
    list2.add("Jim")
    set2.add("Jim")
    map2.put("Jim", 40)
    for (value in list1) {
        println(value)
    }
    for (value in set2) {
        println(value)
    }
    for ((key, value) in map2) {
        println("key=$key value=$value")
    }

    //集合的函数式api。实际上minBy是一个高阶函数。
    var min = list2.minBy {
        it.length
    }
    println(min)

    //minBy的简化过程
    //1.参数selector: (T) -> R
    var selector = { name: String ->
        name.length
    }
    //2.高阶函数，参数是函数类型
    list2.minBy(selector)
    //3.替换lambda表达式
    list2.minBy({ name: String ->
        name.length
    })
    //4.lambda表达式作为最后一个参数，可以放在扩展外面
    list2.minBy() { name: String ->
        name.length
    }
    //5.函数只有lambda表达式一个参数，可以省略扩展
    list2.minBy { name: String ->
        name.length
    }
    //6.lambda表达式只有一个参数，可以省略参数声明，在代码块中用it代替
    list2.minBy {
        it.length
    }
    //7.有时候如果觉得it表意不明显，可以自定义名字(其实就是类型推导，省略了声明中参数类型）
    list2.minBy { name ->
        name.length
    }

    //todo 还有很多其他函数式api。这一块要多加练习。
    list2.map {
        it.toLowerCase()
    }
    map2.filter {
        it.key.length > 3
    }
    set2.count()
}