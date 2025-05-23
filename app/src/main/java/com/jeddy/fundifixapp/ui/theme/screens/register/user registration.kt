package com.jeddy.fundifixapp.ui.theme.screens.register

import AuthViewModel
import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jeddy.fundifixapp.navigation.ROUTE_FINDFUNDI
import com.jeddy.fundifixapp.navigation.ROUTE_LOGIN
import java.nio.file.WatchEvent

@Composable
fun Userregistration(navController: NavController) {

    val authViewModel: AuthViewModel= viewModel()
    val context = LocalContext.current
    val passwordVisible by remember { mutableStateOf(false) }


    val backgroundColor = Color(0xFFF1F4FB)
    val primaryColor = Color(0xFF0A1D4E)
    val accentGold = Color(0xFFFFB300)
    val buttonCornerRadius = 24.dp

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    )
    {
        Text(
            text = "Register here",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            color = primaryColor
        )
        Spacer(modifier = Modifier.height(23.dp))
        Text(
            text = "Quick and  easy access to fundis with FundiFix",
            color = accentGold,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Cursive,
            fontSize = 14.sp
        )


        // input fields

        OutlinedTextField(
            value = name,
            onValueChange = { newName->name = newName },
            label = { Text(text = "Full Name") },
            placeholder = { Text(text = "Please enter your name") },
            modifier = Modifier.fillMaxWidth().wrapContentWidth().align(Alignment.CenterHorizontally),
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Person Icon")})



        Spacer(modifier = Modifier.height(16.dp))




        OutlinedTextField(
            value = email,
            onValueChange = { newEmail->email=newEmail },
            label = { Text(text = "Email") },
            placeholder = { Text(text = "Please enter your email") },
            modifier = Modifier.fillMaxWidth().wrapContentWidth().align(Alignment.CenterHorizontally),
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email Icon")})

        Spacer(modifier = Modifier.height(16.dp))




        OutlinedTextField(
            value = phone,
            onValueChange = { newPhone-> phone = newPhone },
            label = { Text(text = "Phone Number") },
            modifier = Modifier.fillMaxWidth().wrapContentWidth().align(Alignment.CenterHorizontally),
            leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone Icon")})


        Spacer(modifier = Modifier.height(16.dp))





        OutlinedTextField(
            value = password,
            onValueChange = {newPassword -> password = newPassword},
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().wrapContentWidth().align(Alignment.CenterHorizontally),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Password Icon")})

        Spacer(modifier = Modifier.height(16.dp))









        Button(
            onClick = {
                authViewModel.signup(
                    name.trim(),
                    phone.trim(),
                    email.trim(),
                    password.trim(),
                    navController,
                    context
                )
            },
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(buttonCornerRadius)
        ) {
            Text(
                "Register",
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
        }


        Spacer(modifier = Modifier.height(24.dp))

                TextButton(onClick = { navController.navigate(ROUTE_LOGIN) }) {
                    Text(
                        text = "Already have an account? Log In",
                        color = primaryColor
                    )
                }
    }
}

