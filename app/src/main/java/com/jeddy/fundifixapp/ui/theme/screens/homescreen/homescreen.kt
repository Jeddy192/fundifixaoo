package com.jeddy.fundifixapp.ui.theme.screens.homescreen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jeddy.fundifixapp.R
import com.jeddy.fundifixapp.navigation.ROUTE_LOGIN
import com.jeddy.fundifixapp.navigation.ROUTE_REGISTER

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Fundi Fix",
                        color = Color(0xFFFFD700), // Gold
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 26.sp
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF001F54) // Navy Blue
                )
            )
        },
        containerColor = Color(0xFFFAFAFA) // Light Gray Background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(60.dp))
                Image(
                    painter = painterResource(id = R.drawable.fundifix_logo),
                    contentDescription = "Fundi Fix logo",
                    modifier = Modifier
                        .size(180.dp)
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = "Welcome to Fundi Fix",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    color = Color(0xFF001F54)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Connecting skilled fundis with customers across Kenya.",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { navController.navigate(ROUTE_REGISTER) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFD700) // Gold
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .height(55.dp)
                ) {
                    Text(
                        text = "Get Started",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                }
                OutlinedButton(
                    onClick = { navController.navigate(ROUTE_LOGIN) },
                    border = BorderStroke(2.dp, Color(0xFF001F54)),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF001F54)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .height(55.dp)
                ) {
                    Text(
                        text = "I already have an account",
                        color = Color(0xFF001F54),
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
