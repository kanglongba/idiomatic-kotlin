package chap04_class_delegation

class DelegationSample {
    interface AttackType {
        fun getAttackType(): String
    }

    class Ranged : AttackType {
        override fun getAttackType(): String = "Ranged"
    }

    interface HeroType {
        fun getAttributeType(): String
    }

    class Strength : HeroType {
        override fun getAttributeType(): String = "Strength"
    }

    class Huskar : AttackType by Ranged(), HeroType by Strength() {
        override fun getAttackType(): String {
            return "square"
        }
    }
}

fun main(vararg args: String) {
    val huskar : DelegationSample.Huskar = DelegationSample.Huskar()
    println(huskar.getAttackType())
    println(huskar.getAttributeType())
}