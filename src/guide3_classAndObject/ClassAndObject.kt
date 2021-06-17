package guide3_classAndObject

/**
 * Kotlin文件：通常是用于编写 Kotlin 顶层函数和扩展函数等
 * author: qonyqian
 * created on: 2021/6/13 10:33 下午
 * version：1.0
 * description:
 */

class ChildOpenKotlinClass : OpenKotlinClass() {

    override fun printInfo() {
        super.printInfo()
        println("version=$version name=$name")
    }
}

class KotlinObjet : OpenKotlinClass(), KotlinInterface {

    override fun plus(x: Int, y: Int): Int {
        return x+y
    }

    override fun minus(x: Int, y: Int): Int {
        var resultDefault = super.minus(x, y)
        return plus(x,y) - resultDefault
    }
}

fun main(vararg args: String) {
    //相比Java，少了new关键字
    val kotlinClass = KotlinClass()
    kotlinClass.printInfo()
    //实际上调用了name的set方法，所以塞进去是小写："kotlin2"
    kotlinClass.name = "Kotlin2"
    kotlinClass.size = 2
    kotlinClass.printInfo()

    val childOpenKotlinClass : ChildOpenKotlinClass = ChildOpenKotlinClass()
    childOpenKotlinClass.printInfo()

    //多态
    val kotlinInterface : KotlinInterface = KotlinObjet()
    kotlinInterface.minus(5, 6)

    //数据类
    var dataClass = KotlinDataClass("Mike")
    dataClass.printInfo("icbu", "man") //正常调用
    dataClass.printInfo() //1.默认值2.匹配了第一个方法
    dataClass.printInfo(sex = "woman") //1.默认值2.靠别名区分了重载方法
    dataClass.printInfo(sex = "man", school = "cbu") //1.别名可以调整参数顺序
    println(dataClass.toString()) //toString只包含了主构造函数里面声明的变量，没有打印address

    //单例类
    KotlinSingleton.printName()
    KotlinSingleton.printInfo(age = 19)
    println(KotlinSingleton.name + KotlinSingleton.CITY)

    //伴生类
    var kotlinCompanionObject = KotlinCompanionObject();
    kotlinCompanionObject.printKotlin("zhejiang")
    KotlinCompanionObject.Friend.printInfo(20)
    KotlinCompanionObject.printInfo(30)

    //密封类
    testSealedClass(KotlinSealedClass3())
}

fun testSealedClass(kotlinSealedClass: KotlinSealedClass) {
    //没有KotlinSealedClass3分支，编译器会提示
    when(kotlinSealedClass) {
        is KotlinSealedClass1 -> println("KotlinSealedClass1")
        is KotlinSealedClass2 -> println("KotlinSealedClass2")
    }
}


