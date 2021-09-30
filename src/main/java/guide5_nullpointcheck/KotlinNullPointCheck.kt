package guide5_nullpointcheck

/**
 * 1.Kotlin中所有的变量默认都是非空的
 * 2.Koltin中变量后面加上?，表示可能为null
 * 3.在对象调用的时候，使用 ?. 操作符，它表示如果当前对象不为空则调用，为空则什么都不做
 * 4.?: 操作符表示如果左边的结果不为空，返回左边的结果，否则返回右边的结果
 * 5.在对象后面加 !! 操作符表示告诉Kotlin我这里一定不会为空,你不用进行检测了，如果为空，则抛出空指针异常
 * 6.可空类型具有传导性
 * author: qonyqian
 * created on: 2021/6/14 8:00 下午
 * version：1.0
 * description:
 */

//在Kotlin中变量声明了，就要初始化，且不能为空
var name = "edison"

//声明一个可空类型
var address: String? = "china"

//如果要初始化为null，就要加上 ? ，表示变量是可空类型
var school: String? = null

//如果不想立即初始化，要加上 lateinit 关键字，表示我保证在变量使用前初始化
lateinit var country: String

fun printInfo(company: String, city: String?, province: String?) {
    //Kotlin中默认变量和参数全不为空
    val length = company.length

    //如果要为空，需加上 ?。对可空变量的调用，也要加上 ?,表示如果不为空就调用，如果为空就什么都不做直接返回null。
    val length2 = city?.length

    //加上双叹号，表示我保证此变量一定不为空。如果为空，会直接抛出空指针异常。
    val length3 = province!!.length

    //在使用前，初始化 lateinit变量
    country = "china"
    val length4 = country.length

    // ?: 的使用，类似Java中的三目运算符，但是不完全一样。
    val length5 = city?.length ?: 1

    //引用可空类型的变量，一定要加上 ? , 即使你确定它一定不为空。不过如果能确定不为空，使用双叹号更好。
    val length6 = address?.length

    // 注意上面得到的 length6 ，虽然没有明确声明，但也是一个可空类型。所以 可空类型 具有传导性。
    val length7 = length6?.inc()

    // 我能确定address不为空，就可以用 !! 替换 ?。这样得到的 length8 也是非空类型。
    val length8 = address!!.length

    //相比 length6，length8是一个非空类型。
    val length9 = length8.inc()
}