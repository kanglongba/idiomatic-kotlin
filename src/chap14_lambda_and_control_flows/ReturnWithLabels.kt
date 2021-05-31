package chap14_lambda_and_control_flows

class ReturnWithLabels {
    data class Student(val name: String, val id: Int)

    //与LambdaReturn对比来看
    private fun findSpock(list: List<Student>) {
        list.forEach customLabel@{
            // or simply omit and use return@foreach instead
            if (it.name == "Spock") {
                println("Found Spock")
                //返回forEach
                return@customLabel
            }
        }
        //等同于上面的写法
        list.forEach {
            // or simply omit and use return@foreach instead
            if (it.name == "Spock") {
                println("Found Spock")
                return@forEach
            }
        }
        println("Did we find Spock?")
    }

    fun findStudentTest() {
        val studentList = listOf(Student("Kirk", 12345), Student("Spock", 54321))
        findSpock(studentList)
        println("End of findStudentTest")
    }
}

fun main(args: Array<String>) {
    val test = ReturnWithLabels()
    test.findStudentTest()
}