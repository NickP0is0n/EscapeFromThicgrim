package me.nickp0is0n.escapefromthicgrim.models.field.entities

interface CellEntity {
    //TODO:way to know if entity is aggressive, neutral or friendly
    //TODO:mechanics
    fun getEntityHealth(): Int
}