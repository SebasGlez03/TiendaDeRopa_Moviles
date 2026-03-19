package gonzalez.sebastian.tiendaderopa.data.models

data class CounterMode(val count: Int = 0)

data class PrendaState(
    val listaPrendas: List<Prenda> = emptyList(),
    val prendaProbada: Prenda? = null
)

data class Prenda (
    val color: String,
)