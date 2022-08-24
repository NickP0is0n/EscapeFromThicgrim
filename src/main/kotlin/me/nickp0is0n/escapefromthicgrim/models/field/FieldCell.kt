package me.nickp0is0n.escapefromthicgrim.models.field

import me.nickp0is0n.escapefromthicgrim.models.field.entities.CellEntity
import me.nickp0is0n.escapefromthicgrim.models.field.perks.CellPerk

data class FieldCell(
    var entity: CellEntity? = null,
    var perk: CellPerk? = null
)
