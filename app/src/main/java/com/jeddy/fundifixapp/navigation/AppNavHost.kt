package com.jeddy.fundifixapp.navigation

import FundiDetailsScreen
import FundiHomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jeddy.fundifixapp.ui.FundiRegistrationScreen
import com.jeddy.fundifixapp.ui.screens.FundiListScreen
import com.jeddy.fundifixapp.ui.screens.LoginScreen
import com.jeddy.fundifixapp.ui.theme.screens.homescreen.HomeScreen

import com.jeddy.fundifixapp.ui.theme.screens.register.RegisterScreen
import com.jeddy.fundifixapp.ui.theme.screens.register.Userregistration

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_HOME
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        composable(ROUTE_LOGIN) {
            LoginScreen(navController)
        }

        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }

        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }

        composable(ROUTE_FUNDI_REGISTRATION) {
            FundiRegistrationScreen(navController)
        }

        composable(ROUTE_USER_REGISTRATION) {
            Userregistration(navController)
        }
        composable(ROUTE_FUNDI_HOMESCREEN) {
            Userregistration(navController)
        }




        composable("fundi_details/{fundiId}") { backStackEntry ->
            val fundiId = backStackEntry.arguments?.getString("fundiId") ?: ""
            FundiDetailsScreen(navController, fundiId)
        }

        composable("fundi_home/{fundiId}/{fundiName}") { backStackEntry ->
            val fundiId = backStackEntry.arguments?.getString("fundiId") ?: ""
            val fundiName = backStackEntry.arguments?.getString("fundiName") ?: ""
            FundiHomeScreen(navController, fundiId, fundiName)
        }
        composable(ROUTE_FINDFUNDI) {
            FundiListScreen(navController)
        }





    }

        
    }

