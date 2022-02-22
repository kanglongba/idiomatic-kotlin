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
        return x + y
    }

    override fun minus(x: Int, y: Int): Int {
        var resultDefault = super.minus(x, y)
        return plus(x, y) - resultDefault
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

    val childOpenKotlinClass: ChildOpenKotlinClass = ChildOpenKotlinClass()
    childOpenKotlinClass.printInfo()

    //多态
    val kotlinInterface: KotlinInterface = KotlinObjet()
    kotlinInterface.minus(5, 6)

    //数据类
    val dataClass = KotlinDataClass("Mike")
    dataClass.printInfo("icbu", "man") //正常调用
    dataClass.printInfo() //1.函数参数默认值2.匹配了参数少的那个函数(KotlinDataClass中的第一个函数)。
    dataClass.printInfo(sex = "woman") //1.第一个参数默认值2.靠别名区分了重载方法(与第一个区分)
    dataClass.printInfo(sex = "man", school = "cbu") //1.别名可以调整参数顺序
    println(dataClass.toString()) //toString只包含了主构造函数里面声明的变量，没有打印address

    //单例类。单例类可以获得类似静态方法和静态变量的调用形式。Kotlin编译器帮我们写了Java中的构造单例代码。
    KotlinSingleton.printName()
    KotlinSingleton.printInfo(age = 19)
    println(KotlinSingleton.name + KotlinSingleton.CITY)

    //伴生类
    val kotlinCompanionObject = KotlinCompanionObject();
    kotlinCompanionObject.printKotlin("zhejiang")
    KotlinCompanionObject.Friend.printInfo(20)
    //调用的时候，伴生类的名字可省略。因为一个类中只能有一个伴生类，写不写名字都知道是它。
    KotlinCompanionObject.printInfo(30)

    //密封类
    testSealedClass(KotlinSealedClass3())

    //匿名类
    val anony = KotlinAnonymousInnerClass()
    anony.anonymousInnerClass2()
}

fun testSealedClass(kotlinSealedClass: KotlinSealedClass) {
    //没有KotlinSealedClass3分支，编译器会提示
    when (kotlinSealedClass) {
        is KotlinSealedClass1 -> println("KotlinSealedClass1")
        is KotlinSealedClass2 -> println("KotlinSealedClass2")
    }
}


