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
        var anony = object : KotlinInterface {
            override fun plus(x: Int, y: Int): Int {
                return x + y
            }
        }
        anony.plus(1, 2)
    }

    fun anonymousInnerClass2() {
        //创建Java中匿名对象
        var anony = object : Runnable {
            override fun run() {
                println("Runnable1 run")
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

        //object关键字不仅能用来创建接口的匿名内部类，还能用来创建类的匿名内部类。
        val thread3 = object : Thread() {

            //可以重写类的方法，也可以不重写。
            override fun hashCode(): Int {
                return 20210916
            }

            //可以重写类的方法，也可以不重写。
            override fun run() {
                println("Runnable3 run")
            }
        }
        thread3.start()

        //这里的 thread 实际上是一个Kotlin顶层函数，它的返回值是一个 Thread 对象。
        //thread函数很有意思，可以看下源码。
        val thread4 = kotlin.concurrent.thread(false) {
            println("Runnable4 run")
        }
        thread4.start()
    }

}