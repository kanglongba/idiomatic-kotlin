package guide3_classAndObject

/**
 * 伴生类中可以定义常量
 * author: qonyqian
 * created on: 2021/6/14 11:58 上午
 * version：1.0
 * description:
 */
class KotlinCompanionObject {

    var name = "kotlin"

    fun printKotlin(address: String) {
        //父类可以访问伴生类的变量和方法
        printInfo(15)
        println("address=$address NAME=$NAME")
    }

    //一个类只能有一个伴生类
    //伴生类的名字可省略
    //伴生类不能访问父类的变量和方法
    //经过编译后，伴生类被编译成了一个静态内部类，它的方法被保留，但是成员变量变成了父类的静态变量。静态内部类不会持有父类的引用，所以它不能访问父类的变量和方法
    //但是经过编译后，父类内部会生成一个伴生类的对象，所以父类可以访问伴生类的变脸和方法。
    companion object Friend {
        const val NAME = "Friend"
        var city = "hangzhou"

        fun printInfo(age: Int) {
            println("NAME=$NAME age=$age")
        }
    }
}