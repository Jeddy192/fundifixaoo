package com.jeddy.fundifixapp.ui.screens

import FundiListViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jeddy.fundifixapp.R
import com.jeddy.fundifixapp.model.Fundi

val NavyBlue = Color(0xFF001F54)
val Gold = Color(0xFFFFF8DC)
val LightGray = Color(0xFFF0F0F0)

@Composable
fun FundiListScreen(
    navController: NavController,
    viewModel: FundiListViewModel = viewModel()
) {
    val fundis = viewModel.fundiList
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.fundifix_logo),
            contentDescription = "Fundi Fix Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(80.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Available Fundis",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = NavyBlue,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp, bottom = 12.dp)
        )

        when {
            isLoading -> Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    color = NavyBlue,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            error != null -> Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = error,
                    color = Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            fundis.isEmpty() -> Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "No fundis available right now.",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                items(fundis) { fundi ->
                    FundiCard(fundi = fundi) {
                        navController.navigate("fundi_details/${fundi.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun FundiCard(fundi: Fundi, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Gold),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = fundi.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = NavyBlue
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Service: ${fundi.service}",
                fontSize = 16.sp,
                color = Color.DarkGray
            )
            Text(
                text = "Location: ${fundi.location}",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = "Phone: ${fundi.phone}",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FundiCardPreview() {
    FundiCard(
        fundi = Fundi(
            id = "1",
            name = "Jane Doe",
            service = "Plumber",
            location = "Nairobi",
            phone = "0712345678"
        ),
        onClick = {}
    )
}
