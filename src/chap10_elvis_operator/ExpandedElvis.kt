package chap10_elvis_operator

class ExpandedElvis {
    fun elvisSample(arg: String?) {
        val value = if (arg != null) arg else ""
        val value1 = if (arg != null) {
            value.toUpperCase()
            arg
        } else {
            value.toLowerCase()
            ""
        }
    }
}