package me.nickp0is0n.escapefromthicgrim.models.field.entities

import kotlin.random.Random

class EvilKnightEntity(val difficultyLevel: Int): AggressiveCellEntity {
    private var health = 0
    private var damage = 0
    private var armor = 0

    init {
        health = (500 * (Random.nextInt(1, difficultyLevel)) / 2)
        damage = (300 * (Random.nextInt(1, difficultyLevel)) / 2)
        armor = (100 * (Random.nextInt(1, difficultyLevel)) / 2)
    }

    override fun getEntityName(): String {
        return "Evil ancient knight"
    }

    override fun getEntityDamage(): Int {
        return damage
    }

    override fun getEntityArmor(): Int {
        return armor
    }

    override fun reduceEntityHealth(amount: Int) {
        this.health -= amount
    }

    override fun getEntityHealth(): Int {
        return health
    }

    override fun reduceEntityArmor(amount: Int) {
        this.armor -= amount
    }
}