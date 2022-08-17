package chap17_variance

interface Producer<out T> {
    fun produce(): T
}

// 表明只能读，相当于协变
class NumberProducer : Producer<Number> {
    override fun produce(): Number = Math.random()
}

val producer: Producer<Any> = NumberProducer()
