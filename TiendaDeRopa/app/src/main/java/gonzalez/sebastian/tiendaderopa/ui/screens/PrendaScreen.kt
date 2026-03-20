package gonzalez.sebastian.tiendaderopa.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import gonzalez.sebastian.tiendaderopa.R
import gonzalez.sebastian.tiendaderopa.data.models.Prenda
import gonzalez.sebastian.tiendaderopa.viewModel.PrendaViewModel

@Composable
fun TiendaApp(viewModel: PrendaViewModel = viewModel()) {
    val prendaSeleccionada = viewModel.prendaSeleccionada

    if (prendaSeleccionada == null) {
        ListaPrendasScreen(
            prendas = viewModel.listaPrendas,
            onPrendaClick = { viewModel.seleccionarPrenda(it) }
        )
    } else {
        DetallePrendaScreen(
            prenda = prendaSeleccionada,
            resultadoTalla = viewModel.resultadoTalla,
            onProbarTalla = { viewModel.probarTalla() },
            onVolver = { viewModel.volverALista() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaPrendasScreen(prendas: List<Prenda>, onPrendaClick: (Prenda) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Tienda de Ropa") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.tienda),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(prendas) { prenda ->
                PrendaCard(prenda = prenda, onClick = { onPrendaClick(prenda) })
            }
        }
    }
}

@Composable
fun PrendaCard(prenda: Prenda, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = prenda.imagenRes),
                contentDescription = prenda.nombre,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(text = prenda.nombre, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = "$${prenda.precio}", color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallePrendaScreen(
    prenda: Prenda,
    resultadoTalla: String?,
    onProbarTalla: () -> Unit,
    onVolver: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de Prenda") },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Text("<", fontWeight = FontWeight.Bold)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = prenda.imagenRes),
                contentDescription = prenda.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = prenda.nombre, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "$${prenda.precio}", fontSize = 20.sp, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = prenda.descripcion, fontSize = 16.sp)
            
            if (resultadoTalla != null) {
                Spacer(modifier = Modifier.height(24.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (resultadoTalla.contains("agotada")) {
                        Image(
                            painter = painterResource(id = R.drawable.relog),
                            contentDescription = "Reloj",
                            modifier = Modifier.size(32.dp).padding(end = 8.dp)
                        )
                    }
                    Text(
                        text = resultadoTalla,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (resultadoTalla.contains("disponible")) Color.Blue else Color.Red
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onProbarTalla,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Probar Talla")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
