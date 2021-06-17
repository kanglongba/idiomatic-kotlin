package guide2_control

/**
 * author: qonyqian
 * created on: 2021/6/13 9:39 下午
 * version：1.0
 * description:
 */
class ControlLogic {
    var age = 15

    fun controlIf() : Int {
        if (age > 15) {
            "mike"
            return 100
        } else if (age > 100) {
            "Lucy"
            return 200
        } else {
            "Lily"
            return 300
        }
    }

    //if语句的最后一行是返回值
    fun controlIf2(age : Int) : Int {
        return if (age > 15) {
            "mike"
             100
        } else if (age > 100) {
            "Lucy"
             200
        } else {
            "Lily"
             300
        }
    }

    //if语句可以看成是一个代码块，省略函数体
    fun controlIf3(age : Int) : Int = if (age > 15) {
        "mike"
        100
    } else if (age > 100) {
        "Lucy"
        200
    } else {
        "Lily"
        300
    }

    //条件代码是一行时，可以省略大括号。并且不像Java，ide并不会提示错误
    fun controlIf4(age : Int) : Int = if (age > 15)
        100
    else if (age > 100)
        200
    else
        300

    fun controlWhen(name : String) : String {
        when(name) {
            "Lily" -> {
                age = 18
                return "Lily"
            }
            "Lucy" -> {
                return "Lily"
            }
            "Mike" -> return "Mike"
            else -> return "Jim"
        }
    }

    //代码块的最后一行是返回值
    fun controlWhen2(name : String) : String {
        return when(name) {
            "Lily" -> {
                age = 18
                "Lily"
            }
            "Lucy" -> {
                "Lily"
            }
            "Mike" ->  "Mike"
            else -> "Jim"
        }
    }

    //可以省略when的条件
    fun controlWhen3(name : String) : String {
        return when {
            name == "Lily" -> {
                age = 18
                "Lily"
            }
            name == "Lucy" -> {
                "Lily"
            }
            name == "Mike" ->  "Mike"
            else -> "Jim"
        }
    }

    //when代码块可以看成一行
    fun controlWhen4(name : String) : String  = when {
            name == "Lily" -> {
                age = 18
                "Lily"
            }
            name == "Lucy" -> {
                "Lily"
            }
            name == "Mike" ->  "Mike"
            else -> "Jim"
        }

    //左闭右闭
    fun controlCircle(age : Int) : Unit {
        for (score in 1..10) {
            print(score)
        }
    }

    //左闭右开
    fun controlCircle1(age : Int) : Unit {
        for (score in 1 until 10) {
            print(score)
        }
    }

    //左闭右闭
    fun controlCircle2(age : Int) : Unit {
        for (score in 10 downTo 1) {
            print(score)
        }
    }

    fun controlCircle3() {
        var list = listOf<String>("Lily", "Lucy", "Mike")
        for (name in list) {
            print(name)
        }
    }

    fun controlCircle4() {
        var list = arrayOf("Lily", "Lucy", "Mike")
        for (name in list) {
            print(name)
        }
    }

    fun controlCircle5() {
        for (age in 1..10 step 3) {
            print(age)
        }
    }

    fun controlWhile() {
        while (age < 20) {
            print(age)
            age--
        }
    }

}