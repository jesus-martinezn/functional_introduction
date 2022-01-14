class CreditCard(val name: String, val number: Int)
class Coffee(val name: String, val price: Int)

object Payments {
    fun charge(cc: CreditCard, price: Int): Unit =
        println("Charging $price from '${cc.name}' credit card")
}

class Cafe {
    fun buyCoffee(cc: CreditCard, p: Payments): Coffee {
        val cup = Coffee("Expresso", 1)
        p.charge(cc, cup.price) //SIDE EFFECT!!!
        return cup
    }
}

fun main(args: Array<String>) {
    println("Lets make a Café!")

    val mastercardGold = CreditCard("mastercard", 1234567891)

    val cafe = Cafe()
    val myCoffee = cafe.buyCoffee(mastercardGold, Payments)
    println("Bought '${myCoffee.name}' with card '${mastercardGold.number}'")

}

