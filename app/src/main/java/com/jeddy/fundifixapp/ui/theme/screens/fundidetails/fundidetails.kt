import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jeddy.fundifixapp.model.Fundi

@Composable
fun FundiDetailsScreen(
    navController: NavController,
    fundiId: String,
    viewModel: FundiDetailsViewModel = viewModel()
) {
    val fundi = viewModel.selectedFundi
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage

    LaunchedEffect(fundiId) {
        viewModel.loadFundiById(fundiId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Light gray
            .padding(16.dp)
    ) {
        Text(
            text = "Fundi Profile",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF001F54) // Navy Blue
        )

        Spacer(modifier = Modifier.height(16.dp))

        when {
            isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color(0xFFFFD700)) // Gold
                }
            }

            errorMessage != null -> {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            fundi != null -> {
                FundiDetailCard(fundi)
            }
        }
    }
}

@Composable
fun FundiDetailCard(fundi: Fundi) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // ✅ FIXED
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFD700)) // Gold
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = fundi.name,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF001F54) // Navy Blue
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Service: ${fundi.service}",
                color = Color(0xFFFFD700), // Gold
                fontSize = 16.sp
            )
            Text(
                text = "Location: ${fundi.location}",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Phone: ${fundi.phone}",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
            Text(
                text = "Email: ${fundi.email}",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }
    }
}
