package chap11_property_delegation

class LazyTest {
    data class Hero(val attribute: String, val attackType: String)

    val _hero: Hero by lazy { Hero("agility", "melee") }

    val _hero2: Hero by lazy {
        val value = 3
        Hero("agility", "melee")
    }

    //编译错误，Hero没有实现委托接口
    //val _hero1: Hero by Hero("agility", "melee")
}