package com.saurabhbhardwaj.jc_ui_component.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
    val homeItem = listOf<HomeScreenItem>(
        HomeScreenItem(text = "Compose Alert Box", onClick = {
            navController.navigate(Screens.AlertScreenRoute.route)
        }),
        HomeScreenItem(text = "Compose Chips", onClick = {
            navController.navigate(Screens.ChipsScreenRoute.route)
        }),
        HomeScreenItem(text = "Compose Date Picker", onClick = {
            navController.navigate(Screens.DatePickerScreenRoute.route)
        }),
        HomeScreenItem(text = "Selectable Ui Components", onClick = {
            navController.navigate(Screens.selectableUICompScreenRoute.route)
        }),
        HomeScreenItem(text = "Material Compose Buttons", onClick = {
            navController.navigate(Screens.materialButtonScreenRoute.route)
        }),
        HomeScreenItem(text = "Compose Form Field", onClick = {
            navController.navigate(Screens.composeFormScreenRoute.route)
        })
    )
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, content = {
        AppTopBar(centerTitle = "Home Screen")
        LazyColumn {
            items(homeItem) {
                HomeListItem(title = it.text, onClick = it.onClick)
            }
        }
    })
}

data class HomeScreenItem(
    val text: String,
    val onClick: () -> Unit
)


@Composable
fun HomeListItem(
    title: String,
    onClick: () -> Unit
) {
    ListItem(
        modifier = Modifier.clickable(onClick = onClick),
        headlineContent = { Text(title) },
        trailingContent = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = null
            )
        }
    )
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