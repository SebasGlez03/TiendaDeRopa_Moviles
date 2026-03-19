package gonzalez.sebastian.tiendaderopa.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import gonzalez.sebastian.tiendaderopa.data.models.Prenda
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue


class PrendaViewModel: ViewModel(){

    private val catalogoPrendas = listOf<Prenda>(
        Prenda("verde"),
        Prenda("verde"),
        Prenda("verde"),
        Prenda("verde"),
        Prenda("verde")
    )

    var prendaDisponible by mutableStateOf<Prenda?>(null)
        private set

    var prendaProbada by mutableStateOf(listOf<Prenda>())
        private set

    fun probarPrenda(){
        prendaDisponible.let{ prenda ->
            val esTallaDisponible = (1..2).random()

            if (esTallaDisponible == 1) {

            } else {

            }
        }
    }



}