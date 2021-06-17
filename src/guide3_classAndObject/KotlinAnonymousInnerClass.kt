package guide3_classAndObject

/**
 * author: qonyqian
 * created on: 2021/6/14 4:19 下午
 * version：1.0
 * description:
 */
class KotlinAnonymousInnerClass {

    val name = KotlinAnonymousInnerClass::class.simpleName

    fun anonymousInnerClass() {
        //在kotlin中实现匿名内部类，可以直接使用object关键字.
        //object关键字只会创建一个对象，所以可以提高性能
        var  anony = object : KotlinInterface {
            override fun plus(x: Int, y: Int): Int {
                return x+y
            }
        }
        anony.plus(1,2)
    }

    fun anonymousInnerClass2() {
        //创建Java中匿名对象
        var anony = object : Runnable {
            override fun run() {
                println("Runnable run")
            }
        }

        var thread = Thread(anony)
        thread.start()

        //函数式接口的匿名内部类，可以用lambda表达式代替
        var anony2: () -> Unit = {
            println("Runnable2 run")
        }

        var thread2 = Thread(anony2)
        thread2.start()
    }

}