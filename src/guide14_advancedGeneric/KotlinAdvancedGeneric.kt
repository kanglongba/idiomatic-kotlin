package guide14_advancedGeneric

/**
 * Kotlin 泛型和 Java 泛型基本上是一样的，只不过在 Kotlin 上有些东西换了新的写法
 *
 * 一、基本使用
 * 参考1、2、3
 * 二、边界
 * 1.为泛型指定边界，我们可以使用 <T : Class> 这种语法结构，如果不指定泛型的边界，默认为 Any?
 * 2.如果有多个边界，可以使用 where 关键字，中间使用 : 隔开，多个边界中只能有一个边界是类，且类必须放在最前面（之所以有这个规定，因为
 * Kotlin代码最终要编译成Java字节码）
 * 三、泛型实化
 * 泛型实化在 Java 中是不存在的，Kotlin 中之所以能实现泛型实化，是因为使用的内联函数会对代码进行替换，那么在内联函数中使用泛型，最终
 * 也会使用实际的类型进行替换
 * 1.使用内联函数配合 reified 关键字对泛型进行实化
 * 四、泛型协变，逆变和不变
 * 1.泛型协变的语法规则：<out T> 类似于 Java 的 <? extends Bound>，它限定的类型是当前上边界类或者其子类，如果是接口的话就是当前
 * 上边界接口或者实现类，协变的泛型变量只读，不可以写，可以添加 null ，但是没意义
 * 2.泛型逆变的语法规则：<in T> 类似于 Java 的 <? super Bound>，它限定的类型是当前下边界类或者其父类，如果是接口的话就是当前下边
 * 界接口或者其父接口，逆变的泛型变量只能写，不建议读
 * 3.Kotlin中的泛型通常是不变的，为了实现协变和逆变，才有的 <out T> 和 <in T>
 * 4.Kotlin 使用 <*> 这种语法结构来表示无界通配符，它等价于 <out Any>，类似于 Java 中的 <?>。在定义一个类的时候你如果使
 * 用<out T : Number> ，那么使用这个类的时候 * 就相当于 <out Number>
 *
 * author: qonyqian
 * created on: 2021/9/20 5:57 下午
 * version：1.0
 * description:
 */

//1.定义一个泛型类
class City<T> {

    fun getName(country: T) {
        println(country.toString())
    }

    //2.定义一个泛型方法
    fun <C, V> produce(metal: C, block: C.() -> V): V {
        return block(metal)
    }
}

//3.定义一个泛型接口
interface IGrowth<K> {

    fun getGrowth(): K
    fun <C, K> flowOut(c: C): K
}

//情况1 单个边界
class MyClass1<T : Number> {

    var data: T? = null

    fun <T : Number> method(params: T) {

    }

    fun <R : Number> method2(params: R) {

    }
}

//情况2 多个边界使用 where 关键字
open class Animal
interface Food
interface Food2

class MyClass2<T> where T : Animal, T : Food, T : Food2 {

    fun <T> method(params: T) where T : Animal, T : Food, T : Food2 {

    }
}

//泛型实化。在内联函数中，使用reified关键字，会把泛型 T 替换为真实的类型
inline fun <reified T> getGenericType(): Class<T> {
    return T::class.java
}


open class Person
class Student : Person()
class Teacher : Person()

//泛型不变
class SimpleData<T> {
}

//泛型协变
class SimpleData1<out T> {
}

//泛型逆变
class SimpleData2<in T> {
}

//Kotlin中的通配符
class KotlinGeneric<out T : Number> {
}


fun main(vararg args: String) {
    val person: Person = Student()
    //泛型协变
    val personGeneric: SimpleData1<Person> = SimpleData1<Student>()
    val list1: ArrayList<out Person> = ArrayList<Student>()
    //list1.add(person) //error
    val person1 = list1.get(0) //只能读

    //泛型逆变
    val personGeneric1: SimpleData2<Student> = SimpleData2<Person>()
    val list2: ArrayList<in Person> = ArrayList<Any>()
    //val person3: Person = list2.get(0) //error
    list2.add(person) // 只能写

    //泛型不变。编译器不允许，会报错。
    //val personGeneric2: SimpleData<Person> = SimpleData<Student>()

    //无界通配符<*> 等价于 <out Any>，但是声明这个类时限制了泛型边界为 Number，因此这里相当于 <out Number>
    val noBound1: KotlinGeneric<*> = KotlinGeneric<Int>()
    //根据协变规则 编译器不允许这样写，会报错
    //val noBound2: KotlinGeneric<*> = KotlinGeneric<Any>()

}



