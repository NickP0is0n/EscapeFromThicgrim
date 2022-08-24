package me.nickp0is0n.escapefromthicgrim.models.field

import me.nickp0is0n.escapefromthicgrim.models.field.entities.CellEntity

data class FieldCell(
    var entity: CellEntity? = null,
    var perk: CellPerk? = null
)
