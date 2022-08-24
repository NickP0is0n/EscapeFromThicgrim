package me.nickp0is0n.escapefromthicgrim.models

object AvailableGadgets {
    val BasicArmor = Gadget("Basic Armor", listOf(Pair(PlayerProperty.ARMOR, 30), Pair(PlayerProperty.STAMINA, -2)))
    val SteelArmor = Gadget("Steel Armor", listOf(Pair(PlayerProperty.ARMOR, 50), Pair(PlayerProperty.STAMINA, -5)))
    val MasterSteelArmor = Gadget("Master Handcrafted Steel Armor", listOf(Pair(PlayerProperty.ARMOR, 70), Pair(PlayerProperty.STAMINA, -7)))
    val DemonicArmor = Gadget("Demonic Armor", listOf(Pair(PlayerProperty.ARMOR, 100), Pair(PlayerProperty.STAMINA, -10), Pair(PlayerProperty.HEALTH, -20)))
}