package guide8_sealedAndLateInit

/**
 * 密封类：
 * 1.使用 sealed class 定义一个密封类
 * 2.密封类及其子类，只能定义在同一个文件的顶层位置
 * 3.密封类可被继承
 * 4.当我们使用条件语句的时候，需要实现密封类所有子类的情况，否则编译器会提示警告，这样可以避免写出永远不会执行的代码
 *
 * author: qonyqian
 * created on: 2021/8/29 1:00 下午
 * version：1.0
 * description:
 */
sealed class KotlinSealedClass {

}

class KotlinSealedClassSubA : KotlinSealedClass() {

}

class KotlinSealedClassSubB : KotlinSealedClass() {

}


/**
 * 1.密封类主要用在条件表达式中。
 * 2.条件表达式需实现所有子类的分支，可以省略else。并且新增类型时，编译器会给提示，防止遗漏。
 * 3.密封类及其子类，只能定义在同一个文件的顶层位置
 */
fun getType(clz: KotlinSealedClass) {
    when (clz) {
        is KotlinSealedClassSubA -> {
            print("SubA")
        }
        is KotlinSealedClassSubB -> {
            print("SubB")
        }
    }
}

