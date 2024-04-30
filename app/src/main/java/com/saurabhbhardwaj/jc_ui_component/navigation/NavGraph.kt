package com.saurabhbhardwaj.jc_ui_component.navigation
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.saurabhbhardwaj.jc_ui_component.screens.AlertboxScreen
import com.saurabhbhardwaj.jc_ui_component.screens.ComposeFormScreen
import com.saurabhbhardwaj.jc_ui_component.screens.DatePickerScreen
import com.saurabhbhardwaj.jc_ui_component.screens.HomeScreen
import com.saurabhbhardwaj.jc_ui_component.screens.MaterialButtonsScreen
import com.saurabhbhardwaj.jc_ui_component.screens.MaterialChipScreen
import com.saurabhbhardwaj.jc_ui_component.screens.SelectableUiComponentsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.HomeScreenRoute.route) {
        composable(route = Screens.HomeScreenRoute.route) {
            HomeScreen(navController)
        }

        composable(route = Screens.AlertScreenRoute.route) {
            AlertboxScreen(navController)
        }

        composable(route = Screens.ChipsScreenRoute.route) {
            MaterialChipScreen(navController)
        }

        composable(route = Screens.DatePickerScreenRoute.route) {
            DatePickerScreen(navController)
        }

        composable(route = Screens.selectableUICompScreenRoute.route) {
            SelectableUiComponentsScreen(navController)
        }

        composable(route = Screens.materialButtonScreenRoute.route) {
            MaterialButtonsScreen(navController)
        }

        composable(route = Screens.composeFormScreenRoute.route) {
            ComposeFormScreen(navController)
        }
    }
}