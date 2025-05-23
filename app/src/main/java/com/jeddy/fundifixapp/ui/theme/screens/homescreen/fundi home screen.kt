import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.jeddy.fundifixapp.R // make sure this matches your package name
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.jeddy.fundifixapp.navigation.ROUTE_FUNDIDETAILS

@Composable
fun FundiHomeScreen(
    navController: NavHostController,
    fundiId: String,
    fundiName: String
) {
    val auth = FirebaseAuth.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(id = R.drawable.fundifix_logo),
            contentDescription = "Fundi Fix Logo",
            modifier = Modifier
                .height(100.dp)
                .width(200.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Welcome, $fundiName!",
            fontSize = 24.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF001F3F) // Navy Blue
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                navController.navigate("fundi_details/$fundiId") // this should lead to the screen you show fundi's data
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)), // Gold
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Profile", color = Color.Black, fontFamily = FontFamily.Cursive )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                auth.signOut()
                navController.navigate("login") {
                    popUpTo("fundihomescreen") { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Logout", color = Color.Black,fontFamily = FontFamily.Cursive)
        }
    }
}

