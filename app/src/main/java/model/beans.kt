package model


data class PokemonBean(
    var forms: List<FormsBean>,
    var sprites: SpriteBean,
    var height: Number,
    var weight: Number,
    var types: List<TypesBean>,
    var stats: List<StatsBean>
)

data class FormsBean(
    var name: String,
    var url: String
)

data class SpriteBean(
    var front_default: String
)

data class TypesBean(
    var type: TypeBean
)

data class TypeBean(
    var name: String
)

data class StatsBean(
    var base_stat: Number
)



