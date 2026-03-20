package gonzalez.sebastian.tiendaderopa.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import gonzalez.sebastian.tiendaderopa.R
import gonzalez.sebastian.tiendaderopa.data.models.Prenda

class PrendaViewModel : ViewModel() {

    val listaPrendas = listOf(
        Prenda(1, "Outfit color Militar Cargo", 299.0, R.drawable.ropa1, "Conjunto de estilo urbano con pantalones cargo y botas robustas."),
        Prenda(2, "Streetwear Skater Rojo", 599.0, R.drawable.ropa2, "Sudadera roja vibrante combinada con jeans anchos de estilo callejero."),
        Prenda(3, "Outfit Grunge Verde", 450.0, R.drawable.ropa3, "Sudadera verde oliva con detalles oscuros para un look alternativo."),
        Prenda(4, "Camisa Rayada Retro", 1200.0, R.drawable.ropa4, "Camisa a rayas amarillas y negras con un corte vintage muy popular."),
        Prenda(5, "Conjunto Falda ", 350.0, R.drawable.ropa5, "Top verde suave con falda de mezclilla larga, ideal para un estilo casual."),
        Prenda(6, "estilo pokemon", 150.0, R.drawable.ropa6, "Chamarra azul profundo con accesorios a juego para un outfit nocturno.")
    )

    var prendaSeleccionada by mutableStateOf<Prenda?>(null)
        private set

    var resultadoTalla by mutableStateOf<String?>(null)
        private set

    fun seleccionarPrenda(prenda: Prenda) {
        prendaSeleccionada = prenda
        resultadoTalla = null
    }

    fun volverALista() {
        prendaSeleccionada = null
        resultadoTalla = null
    }

    fun probarTalla() {
        val disponible = (1..100).random() > 50
        if (disponible) {
            resultadoTalla = "¡Talla disponible!"
        } else {
            val dias = (1..10).random()
            resultadoTalla = "Talla agotada. Se surtirá en $dias días."
        }
    }
}
