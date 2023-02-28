package me.nickp0is0n.escapefromthicgrim.models.field.entities

import me.nickp0is0n.escapefromthicgrim.models.GadgetPool
import me.nickp0is0n.escapefromthicgrim.models.gadgets.Gadget
import kotlin.random.Random
import kotlin.reflect.full.primaryConstructor

class BasicSellerEntity: SellerCellEntity {
    var items: List<Gadget> = run {
        val list = arrayListOf<Gadget>()
        val gadgetPool = GadgetPool.pool.toMutableList()
        repeat(3) {
            val index = Random.nextInt(0, gadgetPool.size)
            list.add(gadgetPool[index].primaryConstructor!!.call())
            gadgetPool.removeAt(index)
        }
        listOf(*list.toTypedArray())
    }

    override fun removeItemFromAvailable(item: Gadget) {
        items = items.toMutableList().also {
            it.remove(item)
        }
    }

    override fun getAvailableItems(): List<Gadget> {
        return items
    }

    //TODO:seller mechanics
    override fun getEntityHealth(): Int {
        return 100
    }

    override fun getEntityName(): String {
        return "Friendly good ol' seller"
    }
}