package guide7_staticMethodAndStandardMethod

/**
 * 伴生类，可获得类似静态方法的调用形式。
 * 一个类中只能有一个伴生类，且伴生类的名称可省略。
 * author: qonyqian
 * created on: 2021/8/29 11:36 上午
 * version：1.0
 * description:
 */
class KotlinStaticMethod {


    var grade = 1

    companion object {

        const val school = "CX"

        fun getMyName(): String {
            return "edison"
        }

        fun getMyScore(): Int {
            return 5
        }

        //通过 @JvmStatic 注解定义真正的静态方法
        @JvmStatic
        fun getMyGrade(): Int {
            return 1
        }
    }
}