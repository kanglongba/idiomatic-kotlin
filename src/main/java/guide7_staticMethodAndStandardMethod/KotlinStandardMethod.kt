package guide7_staticMethodAndStandardMethod

/**
 * 1.let、run和with类似，返回经过变换的对象。
 * 2.also和apply类型，返回调用者本身。
 *
 * author: qonyqian
 * created on: 2021/8/29 10:27 上午
 * version：1.0
 * description:
 */
class KotlinStandardMethod {

    val student = Student()

    /**
     * 1.let方法是一个Kotlin的扩展函数
     * 2.let方法接受一个函数作为参数
     * 3.函数的参数是调用方，返回经过变换的对象
     * 4.函数的返回值，作为let方法的返回值
     */
    fun letMethod() {
        val score = student.let {
            it.name = "qony"
            it.age = 90
            it.grade = 2
            //最后一行，作为返回值
            5
        }
    }

    /**
     * 1.also方法是一个kotlin的扩展函数
     * 2.函数作为参数
     * 3.函数的参数是调用方，返回Unit
     * 4.also方法返回调用者本身
     */
    fun alsoMethod() {
        //lambda表达式的特性，可以自定义参数名字。否则默认是it。
        val studentA: Student = student.also { stu ->
            stu.name = "qony"
            stu.age = 90
        }
    }

    /**
     * 1.扩展方法
     * 2.函数作为参数
     * 3.函数也是调用者的扩展方法，所以拥有调用者的上下文this对象。
     * 4.函数返回经过变换的对象。
     * 5.函数的返回值，作为run的返回值
     */
    fun runMethod() {
        val score = student.run {
            this.name = "qony"
            //this也可以省略
            age = 90
            5
        }
    }

    /**
     * 1.扩展方法
     * 2.函数作为参数
     * 3.函数也是调用者的扩展方法，所以拥有调用者的上下文this对象
     * 4.函数返回Unit
     * 5.apply方法返回调用者本身
     */
    fun applyMethod() {
        val studentA: Student = student.apply {
            name = "qony"
            age = 90
        }
    }

    /**
     * 1.with接受两个参数：receiver和receiver的扩展函数
     * 2.扩展函数的返回值作为with方法的返回值
     */
    fun withMethod() {
        val score = with(student) {
            this.name = "qony"
            age = 90
            5
        }
    }

}