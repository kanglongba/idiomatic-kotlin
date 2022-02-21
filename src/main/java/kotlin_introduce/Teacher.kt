package kotlin_introduce

/**
 * Kotlin 与 Java 互相调用
 */
class Teacher {

    /**
     * Kotlin中 getter 和 setter 可以直接定义在变量上
     */
    var age: Int = 25
        get() {
            return field.coerceAtLeast(18)
        }
        set(value) {
            field = if (value > 18) {
                18
            } else {
                18
            }
        }

    /**
     * 不显示定义 getter 和 setter，就默认 返回原值 和 直接赋值
     */
    var name = "Lily"

    /**
     * 方法重载。
     * 使用Java中的接口
     */
    fun descript(name: String) {
        //使用 object 构建一个匿名类
        val study = object : Study {
            override fun study() {
                println("$name study")
            }
        }
        study.study()
    }

    /**
     * 方法重载
     * 使用Java中的接口
     */
    fun descript(name: String, age: Int) {
        val study = Study {
            println("$name $age study")
        }
        study.study()
    }

    /**
     * 方法重载
     */
    fun descript(name: String, age: Int, sex: Boolean) {
        val study: () -> Unit = {
            println("$name $age $sex study")
        }
        study.invoke()
    }

    /**
     * 默认参数
     */
    fun descript(name: String, age: Int = 18, sex: Boolean = false, job: String = "Math") {
        val student = Student()
        student.apply {
            this.age = 16
            this.grade = 2
        }
        with(student) {
            println(" it's time to study")
            study()
        }
    }

    fun printTeacher(): Int {
        // 参数按声明顺序，可省略参数名称
        descript("lily", 2, false)
        // 参数无序，必须带上参数名称
        descript("lucy", job = "English")
        return 1
    }
}