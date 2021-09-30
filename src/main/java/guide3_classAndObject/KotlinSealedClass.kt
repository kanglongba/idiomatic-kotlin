package guide3_classAndObject

/**
 * 密封类
 * 1.密封类一般结合when使用，可以避免漏条件(编译器会提示)和省略else分支
 * 2.密封类可以直接继承
 * 3.密封类及其子类，只能定义在同一个文件的顶层位置(其他位置会报错)
 * 4.密封类不能被初始化
 * author: qonyqian
 * created on: 2021/6/14 12:06 下午
 * version：1.0
 * description:
 */
sealed class KotlinSealedClass {
    var name = "sealed"
}

class KotlinSealedClass1 : KotlinSealedClass() {

}

class KotlinSealedClass2 : KotlinSealedClass() {

}

class KotlinSealedClass3 : KotlinSealedClass() {

}