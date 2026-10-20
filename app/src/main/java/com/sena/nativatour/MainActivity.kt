package com.sena.nativatour

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sena.nativatour.R

// ---------- 1. DATOS ----------
data class Categoria(val nombre: String, val emoji: String, val color: Color)

data class Destino(
    val nombre: String,
    val categoria: String,
    val puntaje: String,
    val descripcion: String,
    val imagen: Int
)

val categorias = listOf(
    Categoria("Montaña", "⛰️", Color(0xFFE6DDFB)),
    Categoria("Bosque", "🌲", Color(0xFFDDF3DD)),
    Categoria("Playa", "🏖️", Color(0xFFDDEEFB)),
    Categoria("Ciudad", "🏛️", Color(0xFFFBEBD6)),
    Categoria("Favoritos", "❤️", Color(0xFFFBDDEE))
)

val destinos = listOf(
    Destino("Laguna Azul", "Naturaleza", "4.8", "Un paraíso natural rodeado de montañas.", R.drawable.foto1),
    Destino("Playa del Sol", "Playa", "4.6", "Aguas cristalinas y arena blanca.", R.drawable.foto2),
    Destino("Centro Histórico", "Cultura", "4.7", "Historia y arquitectura en un solo lugar.", R.drawable.foto3)
)

// ---------- 2. ACTIVITY ----------
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PantallaDescubre()
            }
        }
    }
}

// ---------- 3. PANTALLA PRINCIPAL (Column) ----------
@Composable
fun PantallaDescubre() {
    Column(modifier = Modifier.fillMaxSize()) {

        // --- Encabezado ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF4A47C6))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Descubre", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Text("Nuevos destinos, nuevas historias", color = Color.White)
            }
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text("👤", fontSize = 24.sp)
            }
        }

        // --- Imagen destacada con texto encima ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(R.drawable.foto1),
                contentDescription = "Imagen destacada",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text("Explora", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                Text("Lugares increíbles cerca de ti", color = Color.White)
            }
        }

        // --- Categorías (LazyRow) ---
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categorias) { cat ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(cat.color),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(cat.emoji, fontSize = 28.sp)
                    }
                    Text(cat.nombre, fontSize = 12.sp)
                }
            }
        }

        // --- Título de la sección ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Destinos populares", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Ver todos", color = Color(0xFF4A47C6), fontWeight = FontWeight.Bold)
        }

        // --- Lista de destinos (LazyColumn) ---
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(destinos) { destino ->
                ItemDestino(destino)
            }
        }
    }
}

// ---------- 4. CADA ITEM DE LA LISTA (Row) ----------
@Composable
fun ItemDestino(destino: Destino) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(destino.imagen),
            contentDescription = destino.nombre,
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {
            Text(destino.nombre, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text("${destino.categoria} • ⭐ ${destino.puntaje}", color = Color.Gray)
            Text(destino.descripcion, fontSize = 14.sp)
        }
        Text("›", fontSize = 28.sp, color = Color.Gray)
    }
}