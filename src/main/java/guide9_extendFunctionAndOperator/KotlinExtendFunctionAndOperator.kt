package guide9_extendFunctionAndOperator

import guide7_staticMethodAndStandardMethod.Student

/**
 * 扩展函数
 * 1.一般我们要定义哪个类的扩展函数，我们就定义一个同名的 Kotlin 文件，便于后续查找，虽然说也可以定义在任何一个类中，但是更推
 * 荐将它定义成顶层方法，这样可以让扩展方法拥有全局的访问域
 * 2.扩展函数默认拥有这个类的上下文环境
 *
 * 运算符重载
 * 1.Kotlin对一些函数提供了语法糖支持，使这些函数可通过运算符的方式调用。
 * 2.运算符重载使用的是 operator 关键字，我们只需要在指定函数前面加上 operator 关键字，就可以重载运算符的功能
 * 3.运算符重载只支持特定的一些函数，可参考：https://juejin.cn/post/6942251919662383134#heading-26
 *
 * author: qonyqian
 * created on: 2021/8/29 1:22 下午
 * version：1.0
 * description:
 */

fun Student.playFootBoll() {
    //Student的扩展函数拥有Student的上下文对象，因此可以直接调用Student的成员变量和方法
    println(this.school)
    //可以省略this
    println(name)
}

/**
 * 参数是一个普通类型
 */
fun Student.work(day: Int): Int {
    return age * day
}

//参数是lambda表达式的扩展函数
fun Student.cook(block: (Student) -> Int): Int {
    return block(this)
}

//略微修改一下参数，使lambda表达式变成Student的扩展函数形式
fun Student.eat(block: Student.() -> Int): Int {
    return block()
}

fun main(vararg args: String) {
    val student = Student()
    //调用扩展函数
    student.playFootBoll()

    val money = student.work(5)

    //定义一个函数类型的变量，完整写法
    val block: (Student) -> Int = { stu: Student ->
        stu.age
    }
    student.cook(block)
    student.cook({ stu: Student ->
        stu.age
    })
    student.cook() { stu ->
        stu.age
    }
    student.cook { stu ->
        stu.age
    }
    student.cook {
        it.age
    }

    //注意观察eat与cook的不同，就像also和apply
    student.eat {
        age
    }


    val score1 = 10
    val score2 = 20

    //这些都是运算符重载，只是Int类型的运算符重载，Kotlin都帮我们实现了
    var s1 = score1 + score2
    var s2 = score1 - score2
    var s3 = score1 * score2
    var s4 = score1 < score2
    var s5 = score1 == score2

    //自定义类型的运算符重载
    var stuA = Student()
    var stuB = Student()
    val t1 = stuA + stuB
    stuA++
    val t2 = stuA - stuB
    //val s3 = stuA * stuB  //不支持，因为我们没有重载过Student类的times方法
    println("+=$t1 ++=${stuA.age} -=$t2")

}