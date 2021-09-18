package guide7_staticMethodAndStandardMethod

/**
 * author: qonyqian
 * created on: 2021/8/29 11:39 上午
 * version：1.0
 * description:
 */

/**
 * 只能在顶层声明、伴生类、单例类中定义常量。
 */
const val TAG = "Static"

/**
 * 1.顶层方法就是不定义在任何类中的方法，顶层方法在任何位置都能被调用到，Kotlin 编译器会把所有的顶层方法编译成静态方法
 * 2.如果在 Java 中调用顶层方法，Java 默认是没有顶层方法的概念的，Kotlin 编译器会生成一个我们定义这个文件的 Java 类，例
 * 如我在 Kotlin 中的 Util.kt 文件中定义了一个顶层方法，那么就会生成一个 UtilKt 的 Java 类供在 Java 中调用
 */
fun getMySchool(): String {
    return "CX"
}

/**
 * 1.Kotlin中没有定义静态方法的关键字，一般使用伴生类和单例类模拟静态调用
 * 2.如果想定义真正的静态方法，可以使用 @JvmStatic 注解和定义顶层方法
 * 3.@JvmStatic 注解只能修饰伴生类和单例类里面的方法
 */
fun main(vararg args: String) {
    //伴生类的调用
    KotlinStaticMethod.getMyName()
    KotlinStaticMethod.getMyScore()
    KotlinStaticMethod.school

    //单例类的调用
    Singleton.getMyName()
    Singleton.name

    //顶层方法也可以当做静态方法使用
    getMySchool()

    //使用 @JvmStatic 注解
    Singleton.getMyAge()
    KotlinStaticMethod.getMyGrade()
}