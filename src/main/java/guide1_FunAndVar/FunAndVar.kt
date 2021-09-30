package guide1_FunAndVar

/**
 * author: qonyqian
 * created on: 2021/6/13 7:16 下午
 * version：1.0
 * description:
 */

class Variate {

    //var可以多次赋值
    var age: Int = 5

    //val相当于Java中的final，不可变
    val height: Int = 175

    //类型推导，省略变量类型
    var name = "Lucy"

    //变量声明时就要初始化，且一般情况，kotlin中不允许赋null。
    val address = "Hanzhou"

    //问号允许变量为空。问号表明这个变量可能为空
    var weight: Int? = null

    //lateinit允许变量延迟初始化，表明在使用之前，一定会初始化
    lateinit var country: String

    //会自动为变量生成get和set方法，也可以重写，加入自定义逻辑
    var sex: String = "male"
        get() {
            /**
             * field 代表变量本身
             */
            return field.uppercase()
        }
        set(value) {
            /**
             * field 代表变量本身
             */
            field = value.lowercase()
        }

    //val变量没有set方法，只有get方法
    val school: String = "Bupt"
        get() {
            return "I study in $field"
        }

    //常量。单例类。
    object Singleton {
        const val NAME = "BUPT"
    }

    //常量。一个类只能有一个伴生类。伴生类的名字可省略。
    companion object Univercity {
        const val NAME = "ZJU"
    }
}

class Function {

    fun printInfo(): Unit {
        print("who's your dad")
    }

    fun age(): Int {
        return 5
    }

    //只有一行，省略函数体
    fun name(): String = "mike"

    //类型推断，省略返回值类型
    fun address() = "hangzhou"

    //类型推导，省略返回类型
    fun height() = 175

    fun compare(x: Int, y: Int): Int {
        return Math.max(x, y)
    }
}

//常量
const val Grade = "2021"

/**
 * 常量：
 * 1.使用 const val 来修饰，并初始化
 * 2.修饰的类型只能是字符串和基础对象类型
 * 3.只有单例类、伴生类、顶层声明中，才可以定义常量。
 */
fun main(vararg args: String) {
    //单例类
    println(Variate.Singleton.NAME)
    //伴生类
    print(Variate.NAME)
    //顶层声明
    print(Grade)

    val func = Function()
    val name = func.name()
    val address = func.address()
}