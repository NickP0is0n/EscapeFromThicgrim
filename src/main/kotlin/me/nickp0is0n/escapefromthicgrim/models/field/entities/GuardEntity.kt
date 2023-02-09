package me.nickp0is0n.escapefromthicgrim.models.field.entities

import kotlin.random.Random

class GuardEntity(val difficultyLevel: Int): AggressiveCellEntity {
    private var health = 0
    private var damage = 0
    private var armor = 0

    init {
        health = (150 * (Random.nextInt(1, difficultyLevel + 1)) / 2)
        damage = (70 * (Random.nextInt(1, difficultyLevel + 1)) / 2)
        armor = (20 * (Random.nextInt(1, difficultyLevel + 1)) / 2)
    }

    override fun getEntityName(): String {
        return "Kingdom guard"
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