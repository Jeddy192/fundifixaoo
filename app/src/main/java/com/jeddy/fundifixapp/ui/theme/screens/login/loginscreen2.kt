package com.jeddy.fundifixapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jeddy.fundifixapp.navigation.ROUTE_FINDFUNDI
import com.jeddy.fundifixapp.navigation.ROUTE_FUNDI_HOMESCREEN
import com.jeddy.fundifixapp.viewmodel.logInViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: logInViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isLoading = authViewModel.isLoading.value
    val loginError = authViewModel.loginError.value
    val loggedInAsFundi = authViewModel.loggedInAsFundi.value
    val loggedInAsUser = authViewModel.loggedInAsUser.value
    val context = LocalContext.current

    // React to login success by navigating and showing Toast
    LaunchedEffect(loggedInAsFundi, loggedInAsUser) {
        if (loggedInAsFundi) {
            Toast.makeText(context, "Logged in as Fundi", Toast.LENGTH_SHORT).show()
            navController.navigate(ROUTE_FUNDI_HOMESCREEN) {
                popUpTo("login") { inclusive = true }
            }
        } else if (loggedInAsUser) {
            Toast.makeText(context, "Logged in as User", Toast.LENGTH_SHORT).show()
            navController.navigate(ROUTE_FINDFUNDI) {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Light Gray background
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login to FundiFix",
            fontSize = 28.sp,
            color = Color(0xFF001F54), // Navy Blue
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF001F54),
                focusedLabelColor = Color(0xFF001F54),
                cursorColor = Color(0xFF001F54)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF001F54),
                focusedLabelColor = Color(0xFF001F54),
                cursorColor = Color(0xFF001F54)
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                authViewModel.login(email.trim(), password.trim())
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)), // Gold
            shape = RoundedCornerShape(12.dp),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color(0xFF001F54), // Navy Blue
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text("Login", color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (!loginError.isNullOrEmpty()) {
            Text(text = loginError, color = Color.Red, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(onClick = {
            navController.navigate("register")
        }) {
            Text("Don't have an account? Register", color = Color.Gray)
        }
    }
}
