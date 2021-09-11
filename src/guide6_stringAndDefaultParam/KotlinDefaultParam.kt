package guide6_stringAndDefaultParam

/**
 * author: qonyqian
 * created on: 2021/8/28 10:55 下午
 * version：1.0
 * description:
 */
class KotlinDefaultParam {

    fun printAge(age: Int = 99) {
        println("myAge=$age")
    }

    fun printName(name: String = "edison") {
        println("myName=$name")
    }

    fun printInfo(name: String = "edison", age: Int = 99, grade: Int, school: String = "CX") {
        println("$name, $age $grade $school")
    }

    fun printMyInfo() {
        //默认参数
        printAge()
        //覆盖默认参数
        printName("qony")
        //key-value形式指定参数，其余使用默认参数
        printInfo(grade = 1)
        //覆盖默认参数
        printInfo("qony", grade = 1)
        //key-value的形式，可以打乱参数的顺序
        printInfo(school = "XZ", name = "nike", grade = 2)
    }
}