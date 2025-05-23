package com.jeddy.fundifixapp.ui.theme.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jeddy.fundifixapp.R
import com.jeddy.fundifixapp.navigation.ROUTE_FUNDI_REGISTRATION
import com.jeddy.fundifixapp.navigation.ROUTE_USER_REGISTRATION


@Composable
fun RegisterScreen(navController: NavController)
{
    val backgroundColor=Color(0xFFF1F4FB)
    val primaryColor=Color(0xFF1A73E8)
    val accentGold=Color(0xFFFFB300)
    val buttonCornerRadius=24.dp

    Box(modifier = Modifier
        .fillMaxSize()
        .background(backgroundColor)
    )
    { Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {


        Image(painter = painterResource(id=R.drawable.fundifix_logo),
            contentDescription = "FundiFix Logo",
            modifier = Modifier.height(180.dp)
                .width(180.dp)
                .clip(CircleShape)
                .background(Color.White)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))


        Text(
            text = "Fundi Fix",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            color = accentGold
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Choose how you want to register",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Button(onClick ={ navController.navigate(ROUTE_USER_REGISTRATION)},
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            shape = RoundedCornerShape(buttonCornerRadius),
            modifier = Modifier.fillMaxWidth()
                .shadow(8.dp,RoundedCornerShape(buttonCornerRadius))
        )
        {Text(text = "Register as User", fontSize = 20.sp, color = Color.White, fontFamily = FontFamily.Cursive) }

        Spacer(modifier = Modifier.height(22.dp))

        Button(onClick = {navController.navigate(ROUTE_FUNDI_REGISTRATION)},
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            shape= RoundedCornerShape(buttonCornerRadius),
            modifier = Modifier.fillMaxWidth()
                .shadow(8.dp,RoundedCornerShape(buttonCornerRadius)))
        {
            Text(text = "Register as Fundi", fontSize = 20.sp, color = Color.White, fontFamily = FontFamily.Cursive)
        }

    }}

}