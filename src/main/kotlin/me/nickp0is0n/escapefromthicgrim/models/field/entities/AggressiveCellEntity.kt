package me.nickp0is0n.escapefromthicgrim.models.field.entities

interface AggressiveCellEntity: CellEntity {
    fun getEntityDamage(): Int
    fun getEntityArmor(): Int //not sure if it will make it to final game
    fun reduceEntityHealth(amount: Int)
    fun reduceEntityArmor(amount: Int)
}