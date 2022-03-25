package guide6_stringAndDefaultParam

/**
 * 1.定义一个函数时，我们可以给函数的参数添加一个默认值，这样子我们就不需要去传那个参数
 * 2.在我们调用一个函数时，我们可以使用 key value 的形式来传参
 * 3.方法（包含普通方法+构造方法）中使用默认参数，可以省略很多重载方法。比如Android中View的那四个构造函数，就可以合并成一个了。
 * author: qonyqian
 * created on: 2021/8/28 10:55 下午
 * version：1.0
 * description:
 */
class KotlinDefaultParam {

    fun printAge(age: Int = 99) {
        println("myAge=$age")
    }

    // @JvmOverloads 注解会为默认参数生成重载函数
    @JvmOverloads
    fun printName(name: String = "edison") {
        println("myName=$name")
    }

    fun printInfo(name: String = "edison", age: Int = 99, grade: Int, school: String = "CX") {
        println("$name, $age $grade $school")
    }

    fun printMyInfo() {
        //直接使用默认参数
        printAge()
        //覆盖默认参数
        printName("qony")
        //key-value形式指定参数，其余使用默认参数
        printInfo(grade = 1)
        //覆盖默认参数+使用默认参数+<key,value>指定参数
        printInfo("qony", grade = 1)
        //key-value的形式，可以打乱参数的顺序
        printInfo(school = "XZ", name = "nike", grade = 2)
    }
}