package guide15_coroutine

import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * 零、协程本质
 * 协程并不是线程，它是位于线程之上的一套任务调度框架。开发者把任务交给协程，协程再把任务分配给底层的线程，最终仍然是线程完成任务，协程
 * 只是起了一个调度任务的作用。
 * 使用协程与直接使用线程相比有什么优势呢？最主要的就是代码简单。直接使用线程，要等线程执行完后异步回调才能拿到结果，涉及到合作，还要处
 * 理线程间通信，这些用Java语言处理起来比较复杂。而协程在框架层面帮我们处理了这些事情，让我们可以用同步的方式写出异步的代码。其次就是
 * 协程的吞吐量高。协程中的挂起是非阻塞式挂起，它只挂起任务，不挂起线程。线程一刻不停地在工作，只是执行的任务被切换了。
 * 所以协程的工作模式变成了：任务 -> 协程框架 -> 线程
 *
 * 一、协程五要素
 * 1.协程作用域
 * 2.协程作用域的扩展函数（launch、async）
 * 3.协程上下文
 * 4.协程启动模式
 * 5.lambda表达式
 *
 * 二、协程作用域
 * 协程必须在协程作用域中才能启动，协程作用域中定义了一些父子协程的规则，Kotlin 协程通过协程作用域来管控域中的所有协程。
 * 协程作用域间可并列或包含，组成一个树状结构，这就是 Kotlin 协程中的结构化并发。
 *
 * 作用域类型：
 * 1.顶级作用域：没有父协程的协程所在的作用域
 * 2.协同作用域：协程中启动新协程(即子协程)，此时子协程所在的作用域默认为协同作用域，子协程抛出的未捕获异常都将传递给父协程处理，父协
 * 程同时也会被取消；
 * 3.主从作用域：与协同作用域父子关系一致，区别在于子协程出现未捕获异常时不会向上传递给父协程
 *
 * 父子协程间的规则：
 * 1.父协程如果取消或结束了，那么它下面的所有子协程均被取消或结束
 * 2.父协程需等待子协程执行完毕后才会最终进入完成状态，而不管父协程本身的代码块是否已执行完
 * 3.子协程会继承父协程上下文中的元素，如果自身有相同 Key 的成员，则覆盖对应 Key，覆盖效果仅在自身范围内有效
 *
 * 二、使用 CoroutineScope 函数创建一个协程作用域
 * 使用颇多，单纯就是为了创建一个协程作用域。
 *
 * 三、使用 coroutineScope 函数执行一段协程
 * coroutineScope 函数为了方便地执行一个协程，自动在内部创建了一个协程作用域，使调用者只需要专注协程的业务逻辑。
 * 1.coroutineScope 函数是一个挂起函数，它的参数也是一个挂起函数。
 * 2.coroutineScope 函数会继承外部的协程作用域并创建一个子作用域
 * 3.coroutineScope 函数只是为了方便地执行一个协程，它的返回结果是一个具体数据。
 *
 * 四、使用 launch 函数在当前的协程作用域下创建子协程
 * launch 函数可以创建一个子协程，但是 launch 函数只能用于执行一段逻辑，却不能获取执行的结果，因为它的返回值永远是一
 * 个 Job 对象。
 *
 * 五、使用 async 函数在当前的协程作用域下创建一个子协程，并获取协程执行结果
 * 我们可以使用 async 函数，创建一个子协程并获取它的执行结果。
 * 1.async 函数 和 launch 函数都是协程作用域的扩展函数
 * 2.async 函数会创建一个子协程并返回一个 Deferred 对象，如果需要获取 async 函数代码块中的执行结果，只需要调用 Deferred 对
 * 象的 await() 方法即可
 * 3.async 函数在调用后会立刻执行，当调用 await() 方法时，如果代码块中的代码还没执行完，那么 await() 方法会将当前协程阻塞住，直
 * 到可以获取 async 函数中的执行结果
 *
 * 六、Kotlin中的挂起
 * Kotlin中的挂起，挂起的是协程，底层的线程不受影响，将继续执行协程下面的代码。而当被挂起的协程恢复运行后，协程框架会自动切换回原来的
 * 线程，并且从协程里被挂起的地方继续向下执行。
 *
 * 七、使用 suspend 关键字将一个函数声明成挂起函数
 * 1.suspend 关键字能将一个函数声明成挂起函数
 * 2.挂起函数必须在协程或者另一个挂起函数里被调用
 *
 * 八、使用 Delay 函数延迟协程执行
 * 1.delay 函数是一个非阻塞式挂起函数，它可以让当前协程延迟到指定的时间执行，且只能在协程的作用域或者其他挂起函数中调用
 *
 * 九、使用 runBlocking 函数创建一个能阻塞当前线程的协程作用域
 * 1.runBlocking 函数可以保证在协程作用域内的所有代码和子协程没有全部执行完之前一直阻塞当前线程
 *
 * 十、使用 withContext 函数构建一个简化版的 async 函数
 * 1.withContext 函数是一个挂起函数，并且强制要求我们指定一个协程上下文参数，这个调度器其实就是指定协程具体的运行线程
 * 2.withContext 函数在调用后会立刻执行，它可以保证其作用域内的所有代码和子协程在全部执行完之前，一直阻塞当前协程
 * 3.withContext 函数会创建一个子协程并将最后一行的执行结果作为返回值
 *
 * 十一、使用 suspendCoroutine 函数简化回调的写法
 * 1.suspendCoroutine 函数必须在协程作用域或者挂起函数中调用，它接收一个 Lambda 表达式，主要作用是将当前协程立即挂起，然后
 * 在一个普通线程中去执行 Lambda 表达式中的代码
 * 2.suspendCoroutine 函数的 Lambda 表达式参数列表会传入一个 Contination 参数，调用它的 resume() 或 resumeWithException()
 * 方法可以让协程恢复执行
 *
 * 十二、Kotlin中的调度器
 * Dispatchers 调度器，它可以将协程限制在一个特定的线程执行，或者将它分派到一个线程池，或者让它不受限制地运行。常用的
 * Dispatchers ，有以下三种：
 * 1.Dispatchers.Main：Android 中的主线程
 * 2.Dispatchers.IO：针对磁盘和网络 IO 进行了优化，适合 IO 密集型的任务，比如：读写文件，操作数据库以及网络请求
 * 3.Dispatchers.Default：适合 CPU 密集型的任务，比如计算
 *
 * 总结：
 * 1、被 suspend 修饰的挂起函数比普通函数多两个操作：
 * * 挂起：暂停当前协程的执行，保存所有的局部变量
 * * 恢复：从协程被暂停的地方继续执行协程
 * 2.协程在执行到有 suspend 标记的挂起函数时，会被挂起
 * 3.协程被挂起之后需要恢复，而恢复这个操作是协程框架给我们做的
 *
 *
 * author: qonyqian
 * created on: 2021/9/20 9:07 下午
 * version：1.0
 * description:
 */

@OptIn(DelicateCoroutinesApi::class)
fun main(vararg args: String) {
    //通过launch启动的协程，不一定会被分配到当前线程执行，有可能被分配到其他子线程中执行。
    // 同理在其他方法中启动的协程也一样。
    GlobalScope.launch {
        println("codes run in coroutine scope")
        //delay 挂起当前协程，但是底层线程仍然正常运行。
        delay(500)
        println("codes run in coroutine scope finished")
    }
    //Thread.sleep(1000)

    //runBlocking 挂起当前线程，直到协程完成
    val res = runBlocking {
        println("codes run in runBlocking coroutine scope")
        println("codes run in runBlocking coroutine scope finished")
        5
    }
    println("res = $res")

    //launch函数创建子协程，直到两个子协程都完成，父协程才算完成。
    runBlocking {
        println("start runBlocking " + Thread.currentThread().name)
        launch {
            println("launch1 " + Thread.currentThread().name)
            delay(1000)
            println("launch1 finished " + Thread.currentThread().name)
        }
        launch {
            println("launch2 " + Thread.currentThread().name)
            delay(1000)
            println("launch2 finished " + Thread.currentThread().name)
        }
        println("end runBlocking " + Thread.currentThread().name)
    }

    runBlocking {
        println("开始 挂起 " + Thread.currentThread().name)
        doSomething()
        println("结束 挂起")
    }

    runBlocking {
        doSomething2()
        println("runBlocking finished")
    }

    //await方法将父协程阻塞住，所以两个async启动的协程是串行关系。这样效率比较低。
    runBlocking {
        val start = System.currentTimeMillis()
        val result1 = async {
            delay(1000)
            5 + 5
        }.await()


        val result2 = async {
            delay(1000)
            4 + 6
        }.await()
        println("result is ${result1 + result2}")
        val end = System.currentTimeMillis()
        println("cost: ${end - start} ms.")
    }

    //相较上面的写法，这样写两个 async 启动的协程是并行关系，效率较高。
    runBlocking {
        val start = System.currentTimeMillis()
        val deferred1 = async {
            delay(1000)
            5 + 5
        }


        val deferred2 = async {
            delay(1000)
            4 + 6
        }
        println("result is ${deferred1.await() + deferred2.await()}")
        val end = System.currentTimeMillis()
        println("cost: ${end - start} ms.")
    }

    //withContext 函数在调用后会立刻执行，它可以保证其作用域内的所有代码和子协程在全部执行完之前，一直阻塞父协程
    runBlocking {
        val result = withContext(Dispatchers.Default) {
            //最后一行是返回值
            5 + 5
        }
        println(result)
    }
}

suspend fun doSomething() {
    println("start doSomething " + Thread.currentThread().name)
    //挂起函数只能运行在协程或另一个挂起函数中
    delay(1000)
    println("end doSomething")
}

suspend fun doSomething2() {
    coroutineScope {
        launch {
            for (i in 1..5) {
                println(i)
            }
        }
        println("coroutineScope finished")
    }
}

//定义成功和失败的接口
interface OnHttpCallBackListener {
    fun onSuccess(response: String)
    fun onError(exception: Exception)
}

//模拟发送一个网络请求
fun sendHttpRequest(url: String, httpCallBack: OnHttpCallBackListener) {

}

/**
 * 这就是以同步的方式写出异步的代码，类似于Java中 CountDownLatch 的效果。
 * 对发送的网络请求回调使用 suspendCoroutine 函数进行封装
 */
suspend fun request(url: String): String {
    //suspendCoroutine 会立即挂起当前协程，并在子线程执行lambda表达式
    return suspendCoroutine { continuation ->
        sendHttpRequest(url, object : OnHttpCallBackListener {
            override fun onSuccess(response: String) {
                //恢复被挂起的父协程，并且response作为suspendCoroutine函数的返回值
                continuation.resume(response)
            }

            override fun onError(exception: Exception) {
                //如果出错，返回异常，将在下面的catch代码块中得到处理
                continuation.resumeWithException(exception)
            }
        })
    }
}

//具体使用
suspend fun getBaiduResponse() {
    try {
        val response = request("https://www.baidu.com/")
    } catch (e: Exception) {
        //对异常情况进行处理
    }
}