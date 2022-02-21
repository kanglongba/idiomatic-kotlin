//数据类，默认实现 toString 方法
data class Customer(var name: String, var age: Int) {

}

//单例类
object Resource {
    //只有单例类和伴生类可以定义常量
    const val TAG: String = "Resource"
    var name = "name"

    fun work() {
        println("$name is working")
    }
}

class Phone(var deviceName: String, var price: Int) {

    //伴生类
    companion object {
        const val BRAND: String = "Pixel"

        fun start() {
            println("Phone start")
        }

        fun shutdown() {
            println("Phone shutdown")
        }
    }

    fun deviceInfo() {
        println("$deviceName $price")
    }
}

fun main() {
    val customer = Customer("Jack", 55)
    println(customer.toString())

    Resource.name = "martin"
    Resource.work()

    val phone = Phone("贾维斯", 1999)
    phone.deviceInfo()
    Phone.start()
    Phone.shutdown()
}

