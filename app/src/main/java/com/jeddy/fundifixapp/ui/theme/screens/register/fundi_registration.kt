package com.jeddy.fundifixapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jeddy.fundifixapp.model.Fundi
import com.jeddy.fundifixapp.viewmodel.FundiRegistrationViewModel

@Composable
fun FundiRegistrationScreen(
    navController: NavHostController,
    viewModel: FundiRegistrationViewModel = viewModel()
) {
    val name = remember { mutableStateOf("") }
    val service = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val location = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val isLoading by viewModel.isLoading
    val registrationSuccess by viewModel.registrationSuccess
    val errorMessage by viewModel.errorMessage
    val registeredFundi by viewModel.registeredFundi

    // Navigate to Fundi Home Screen after successful registration
    LaunchedEffect(registrationSuccess, registeredFundi) {
        if (registrationSuccess && registeredFundi != null) {
            navController.navigate("fundi_home/${registeredFundi!!.id}/${registeredFundi!!.name}") {
                popUpTo("fundi_register") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Register Your Business",
            fontFamily = FontFamily.Cursive,
            fontSize = 24.sp,
            color = Color(0xFF001F3F) // Navy Blue
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = service.value,
            onValueChange = { service.value = it },
            label = { Text("Service") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = phone.value,
            onValueChange = { phone.value = it },
            label = { Text("Phone") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = location.value,
            onValueChange = { location.value = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val fundi = Fundi(
                    name = name.value,
                    service = service.value,
                    phone = phone.value,
                    location = location.value,
                    email = email.value,
                    password = password.value
                )
                viewModel.registerFundi(fundi)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)), // Gold
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text(if (isLoading) "Registering..." else "Register", color = Color.Black)
        }

        if (!errorMessage.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = errorMessage!!, color = Color.Red)
        }
    }
}
