package guide5_nullpointcheck

/**
 * 1.Kotlin中所有的变量默认都是非空的
 * 2.Koltin中变量后面加上?，表示可能为null
 * 3.在对象调用的时候，使用 ?. 操作符，它表示如果当前对象不为空则调用，为空则什么都不做
 * 4.?: 操作符表示如果左边的结果不为空，返回左边的结果，否则返回右边的结果
 * 5.在对象后面加 !! 操作符表示告诉Kotlin我这里一定不会为空,你不用进行检测了，如果为空，则抛出空指针异常
 * author: qonyqian
 * created on: 2021/6/14 8:00 下午
 * version：1.0
 * description:
 * commit1
 */
