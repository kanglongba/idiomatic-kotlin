package chap17_variance

interface Consumer<in T> {
    fun consume(input: T): Boolean
}

class NumberConsumer : Consumer<Number> {
    //表明可以number或number的子类
    override fun consume(input: Number): Boolean = true
}

fun checkConsumer(consumer: NumberConsumer) {
    val anyConsumer: Consumer<Int> = consumer

    anyConsumer.consume(1)
}

