package guide13_dsl

/**
 * DSL英文全称：domain specific language，中文翻译即领域特定语言，例如：HTML，XML等 DSL 语言。
 * 特点：
 * 1.解决特定领域的专有问题
 * 2.它与系统编程语言走的是两个极端，系统编程语言是希望解决所有的问题，比如 Java 语言希望能做 Android 开发，又希望能做后台
 * 开发，它具有横向扩展的特性。而 DSL 具有纵向深入解决特定领域专有问题的特性。
 * 总的来说，DSL 的核心思想就是：“求专不求全，解决特定领域的问题”。
 *
 * 首先介绍一下Gradle：Gradle 是一个开源的自动化构建工具，是一种基于 Groovy 或 Kotin 的 DSL。我们的 Android 应用就是
 * 使用 Gradle 构建的，因此后续写脚本，写插件，我们可以使用 Kotlin 去编写，而且 AndroidStudio 对 Kotlin 的支持很友好，
 * 各种提示，写起来很爽。
 *
 * author: qonyqian
 * created on: 2021/9/16 5:25 下午
 * version：1.0
 * description:
 */

/**
 * 使用 Koltin DSL 实现下面的功能：
 *
 * dependencies {
 *   implementation 'androidx.core:core-ktx:1.3.2'
 *   implementation 'androidx.appcompat:appcompat:1.2.0'
 * }
 *
 */

class Dependency {
    fun implementation(lib: String) {

    }
}

fun dependencies(block: Dependency.() -> Unit) {
    val dependency = Dependency()
    dependency.block()
}

fun main() {
    /**
     * 这里用到了：高阶函数、扩展函数、lambda表达式缩写
     */
    dependencies {
        implementation("androidx.core:core-ktx:1.3.2")
        implementation("androidx.appcompat:appcompat:1.2.0")
    }
}