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

    fun buyCoffees(cc: CreditCard, items: Int): Pair<List<Coffee>, Charge> {
        val pair: Pair<List<Coffee>, List<Charge>> = List(items) {buyCoffee(cc)}.unzip()
        return Pair(pair.first, pair.second.reduce { c1,c2 -> c1.combine(c2) })
    }
}

fun main() {
    println("Lets make many Cafés!")

    val mastercardGold = CreditCard("mastercard", 1234567891)

    val myCoffeeWithCharge = Cafe().buyCoffees(mastercardGold, 3)
    val coffeeNames = myCoffeeWithCharge.first.map { e -> e.name }.reduce { s1, s2 -> "$s1, $s2" }
    println("Bought '$coffeeNames' with card '${mastercardGold.number}', total price of ${myCoffeeWithCharge.second.amount}")

}

