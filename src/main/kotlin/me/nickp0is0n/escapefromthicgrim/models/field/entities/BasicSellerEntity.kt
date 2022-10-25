package me.nickp0is0n.escapefromthicgrim.models.field.entities

class BasicSellerEntity: FriendlyCellEntity {
    //TODO:seller mechanics
    override fun getEntityHealth(): Int {
        return 100
    }

    override fun getEntityName(): String {
        return "Friendly good ol' seller"
    }
}