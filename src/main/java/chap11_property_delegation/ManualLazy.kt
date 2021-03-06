package chap11_property_delegation

class ManualLazy {
    data class Hero(val attribute: String, val attackType: String)

    private var _hero: Hero? = null

    val hero: Hero
        get() {
            if (_hero == null) {
                _hero = Hero("agility", "melee")
            }
            return _hero!!
        }

    var hero1 : Hero = Hero("lol", "dota")
        get() {
            return Hero(field.attackType, field.attribute)
        }
        set(value) {
            field.attribute + value.attribute
            field = value
        }
}