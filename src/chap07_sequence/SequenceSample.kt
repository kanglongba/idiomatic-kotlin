package chap07_sequence

import com.sun.org.apache.xalan.internal.xsltc.dom.Filter
import com.sun.xml.internal.xsom.impl.scd.Iterators

// Copy of some stdlib functions. Use the actual function in stdlib for the latest code
class SequenceSample {
    public inline fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T> {
        return filterTo(ArrayList<T>(), predicate)
    }

    public inline fun <T, C : MutableCollection<in T>> Iterable<T>.filterTo(destination: C, predicate: (T) -> Boolean): C {
        for (element in this) if (predicate(element)) destination.add(element)
        return destination
    }

    public fun <T> Sequence<T>.filter(predicate: (T) -> Boolean): Sequence<T> {
        return FilteringSequence(this, true, predicate)
    }

    internal class FilteringSequence<T>(
            private val sequence: Sequence<T>,
            private val sendWhen: Boolean = true,
            private val predicate: (T) -> Boolean
    ) : Sequence<T> {

        override fun iterator(): Iterator<T> = object : Iterator<T> {
            val iterator = sequence.iterator()
            var nextState: Int = -1 // -1 for unknown, 0 for done, 1 for continue
            var nextItem: T? = null

            private fun calcNext() {
                while (iterator.hasNext()) {
                    val item = iterator.next()
                    if (predicate(item) == sendWhen) {
                        nextItem = item
                        nextState = 1
                        return
                    }
                }
                nextState = 0
            }

            override fun next(): T {
                if (nextState == -1)
                    calcNext()
                if (nextState == 0)
                    throw NoSuchElementException()
                val result = nextItem
                nextItem = null
                nextState = -1
                @Suppress("UNCHECKED_CAST")
                return result as T
            }

            override fun hasNext(): Boolean {
                if (nextState == -1)
                    calcNext()
                return nextState == 1
            }
        }
    }

    fun handle(predicate: (Int) -> Boolean) {
        predicate(1)
    }
}

fun main(vararg args: String) {
    val sample = SequenceSample()
    //函数类型的变量
    val func1 : (Int) -> Boolean = { num : Int ->
        num > 100
    }
    //传入函数类型的变量
    sample.handle(func1)
    //传入函数引用
    sample.handle(::func2)
    //传入一个函数，并且逐渐lambda化
    sample.handle({ num: Int ->
        num > 200
    })
    sample.handle({ num ->
        num > 200
    })
    sample.handle({
        it > 200
    })
    sample.handle() {
        it > 200
    }
    sample.handle {
        it > 200
    }
    //匿名类对象，错误
//    sample.handle(object : Filter {
//        override fun test(node: Int): Boolean {
//            return node > 10
//        }
//    })
}

fun func2(num : Int) : Boolean {
    return num > 50
}