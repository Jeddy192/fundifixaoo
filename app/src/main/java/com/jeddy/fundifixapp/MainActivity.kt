package com.jeddy.fundifixapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.jeddy.fundifixapp.navigation.AppNavHost
import com.jeddy.fundifixapp.ui.theme.FundiFixAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FundiFixAppTheme {
                AppNavHost()
            }


                }
            }
        }