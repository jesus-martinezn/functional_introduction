package com.letgo.func

class CreditCard(val name: String, val number: Int)
class Coffee(val name: String, val price: Int)

class Charge(private val cc: CreditCard, val amount: Int) {
    fun combine(other: Charge): Charge {
        if (cc == other.cc)
            return Charge(cc, amount + other.amount)
        else
            throw Exception("Can't combine charges to differnt cards")
    }
}

class Cafe {
    fun buyCoffee(cc: CreditCard): Pair<Coffee, Charge> {
        val cup = Coffee("Expresso", 1)
        return Pair(cup, Charge(cc, cup.price))
    }

    fun buyCoffees(cc: CreditCard, n: Int): Pair<List<Coffee>, Charge> {
        val purchases: List<Pair<Coffee, Charge>> = List(n) {buyCoffee(cc)}
        val pair: Pair<List<Coffee>, List<Charge>> = purchases.unzip()
        val coffees = pair.first
        val charges = pair.second
        return Pair(coffees, charges.reduce { c1,c2 -> c1.combine(c2) })
    }
}
fun main(args: Array<String>) {
    println("Lets make many Cafés!")

    val mastercardGold = CreditCard("mastercard", 1234567891)

    val cafe = Cafe()
    val myCoffeeWithCharge = cafe.buyCoffees(mastercardGold, 3)
    val coffeeNames = myCoffeeWithCharge.first.map { e -> e.name }.reduce { s1, s2 -> "$s1, $s2" }
    println("Bought '$coffeeNames' with card '${mastercardGold.number}', total price of ${myCoffeeWithCharge.second.amount}")

}

