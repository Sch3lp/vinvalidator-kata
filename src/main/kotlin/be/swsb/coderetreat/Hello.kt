package be.swsb.coderetreat

class Hello {

    fun greet(): String {
        return "World!"
    }
}

open class HelloService {
    open fun sayGreeting(): String {
        return Hello().greet()
    }
}