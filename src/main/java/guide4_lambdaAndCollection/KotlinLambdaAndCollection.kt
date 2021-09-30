package guide4_lambdaAndCollection

/**
 * lambda表达式：
 * 1.Lambda 就是一段可以作为参数传递的代码，它可以作为函数的参数，返回值，同时也可以赋值给一个变量
 * 2.Lambda 完整的表达式的语法结构：{ 参数名1：参数类型，参数名2：参数类型 -> 函数体 }，函数体中的最后一行代码作为返回值
 * 3.很多时候，我们会使用简化形式的语法结构，直接就是一个函数体：{函数体}，这种情况是当 Lambda 表达式的参数列表中只有一个参数的
 * 时候，我们可以把参数给省略，默认会有个 it 参数
 * 4.Kotlin 中规定，当 Lambda 表达式作为函数的最后一个参数的时候，我们可以把 Lambda 表达式移到函数括号的外面
 * 5.Kotlin 中规定，当 Lambda 表达式是函数的唯一参数的时候，函数的括号可以省略
 * 6.Java中的函数式接口（SAM）在Kotlin中可以简化成：函数式接口名称 { 参数名1：参数类型，参数名2：参数类型 -> 函数体 }。这种
 * 方式比匿名内部类简洁，但是仅限Java中的函数式接口。
 *
 * author: qonyqian
 * created on: 2021/6/14 12:24 下午
 * version：1.0
 * description:
 */
//不可变集合
var list1 = listOf("Mike", "Lily", "Lucy")
var array1 = arrayOf("Mike", "Lily", "Lucy")
var set1 = setOf("Mike", "Lily", "Lucy")
var map1 = mapOf("Mike" to 10, "Lily" to 20, "Lucy" to 30) //to实际上是一个中缀(infix)函数

//可变集合
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
    var min = list2.minByOrNull {
        it.length
    }
    println(min)

    //minByOrNull的简化过程
    //1.参数selector: (T) -> R
    var selector = { name: String ->
        name.length
    }
    //2.高阶函数，参数是函数类型
    list2.minByOrNull(selector)
    //3.替换lambda表达式
    list2.minByOrNull({ name: String ->
        name.length
    })
    //4.lambda表达式作为最后一个参数，可以放在扩号外面
    list2.minByOrNull() { name: String ->
        name.length
    }
    //5.函数只有lambda表达式一个参数，可以省略扩号
    list2.minByOrNull { name: String ->
        name.length
    }
    //6.lambda表达式只有一个参数，可以省略参数声明，在代码块中用it代替
    list2.minByOrNull {
        it.length
    }
    //7.有时候如果觉得it表意不明显，可以自定义名字(其实就是类型推导，省略了声明中参数类型）
    list2.minByOrNull { name ->
        name.length
    }

    //还有很多其他函数式api。这一块要多加练习。
    list2.map {
        it.lowercase()
    }
    map2.filter {
        it.key.length > 3
    }
    set2.count()

    //Java中的函数式接口，在Kotlin中也可以用lambda表达示转换成函数api的方式调用
    //创建一个线程并运行。这里使用了匿名内部类。
    val thread1 = Thread(object : Runnable {
        override fun run() {
            println("thread1 run")
        }
    })
    thread1.start()
    //Runnable是一个Java函数式接口，可以转换成Kotlin中的lambda表达式
    val runnable: () -> Unit = {
        println("thread2 run")
    }
    val thread2 = Thread(runnable)
    thread2.start()
    //既然使用了lambda表达式，可以进一步转化为函数式api调用。
    val thread3 = Thread({
        println("thread3 run") //直接把runnable拷贝过来
    })
    thread3.start()
    //转化为最终形态
    val thread4 = Thread {
        println("thread4 run")
    }
    thread4.start()

    //还有一种写法。与thread2殊途同归，最终都是转换为thread4.
    //只有Java中的函数式接口，才能这样写。因为函数式接口只有一个方法，即便不写明也知道实现的是哪个方法。
    val runnable5 = Runnable {
        println("thread5 run")
    }
    val thread5 = Thread(runnable5)
    thread5.start()
    //转换
    val thread6 = Thread(Runnable {
        println("thread5 run") //直接把runnable5拷贝过来
    })
    thread6.start()
    //再转。与thread3一模一样
    val thread7 = Thread({
        println("thread5 run") //直接把runnable5拷贝过来
    })
    thread7.start()
    //最终形态。与thread4一模一样
    val thread8 = Thread {
        println("thread8 run")
    }
    thread8.start()
}