package chap08_lambdas_sam_constructors

fun main(args: Array<String>) {
    // Assigning to a variable
    val runnable: Runnable = Runnable { print("I am a runnable") }

    // Returning a specific type of functional interface
    fun createOnClickListener(): View.OnClickListener {
        return View.OnClickListener { v -> print("I am clicked") }
    }

    //原始形态匿名类对象
    val runnable1 : Runnable = object : Runnable {
        override fun run() {
            print("runnable")
        }
    }

    //转换成lambda
    val runnable2 : Runnable = Runnable { print("runnable") }

    //错误
    //val runnable3 : Runnable =  { print("runnable") }
}