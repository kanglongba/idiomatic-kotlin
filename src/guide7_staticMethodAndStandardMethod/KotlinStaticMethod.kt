package guide7_staticMethodAndStandardMethod

/**
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

        @JvmStatic
        fun getMyGrade(): Int {
            return 1
        }
    }
}