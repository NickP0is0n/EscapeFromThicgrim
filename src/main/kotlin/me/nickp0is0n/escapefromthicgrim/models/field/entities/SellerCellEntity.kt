package me.nickp0is0n.escapefromthicgrim.models.field.entities

import me.nickp0is0n.escapefromthicgrim.models.gadgets.Gadget

interface SellerCellEntity: FriendlyCellEntity {
    fun getAvailableItems(): List<Gadget>
    fun removeItemFromAvailable(item: Gadget)
}