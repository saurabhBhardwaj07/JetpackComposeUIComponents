package com.saurabhbhardwaj.jc_ui_component.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.saurabhbhardwaj.jc_ui_component.components.AppTopBar
import com.saurabhbhardwaj.jc_ui_component.navigation.Screens
import com.saurabhbhardwaj.jc_ui_component.ui.theme.JC_UI_ComponentTheme


@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, content = {
        AppTopBar(centerTitle = "Home Screen")
        ListItem(
            modifier = Modifier.clickable {
                navController.navigate(Screens.AlertScreenRoute.route)
            },
            headlineContent = { Text("Compose Alert Box") },
            trailingContent = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = null
                )
            }
        )

        ListItem(
            modifier = Modifier.clickable {
                navController.navigate(Screens.ChipsScreenRoute.route)
            },
            headlineContent = { Text("Compose Chips") },
            trailingContent = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = null
                )
            }
        )

        ListItem(
            modifier = Modifier.clickable {
                navController.navigate(Screens.DatePickerScreenRoute.route)
            },
            headlineContent = { Text("Compose Date Picker") },
            trailingContent = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = null
                )
            }
        )
    })
}


@Preview(showBackground = true, apiLevel = 33)
//@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OnBoardingPagePreview() {
    val context = LocalContext.current
    val navController = remember { NavController(context) }
    JC_UI_ComponentTheme {
        HomeScreen(
            navController = navController
        )
    }
}