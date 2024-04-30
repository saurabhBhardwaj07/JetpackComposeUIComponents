package com.saurabhbhardwaj.jc_ui_component.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.saurabhbhardwaj.jc_ui_component.components.AppTopBar
import com.saurabhbhardwaj.jc_ui_component.ui.theme.JC_UI_ComponentTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialButtonsScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        AppTopBar(centerTitle = "MaterialButton", onBackClick = {
            navController.popBackStack();
        })

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp),

            shape = RoundedCornerShape(topEnd = 15.dp, bottomStart = 15.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Create Account")
        }

        OutlinedButton(
            shape = CutCornerShape(10.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 6.dp
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xfffedbd0),
                contentColor = Color.DarkGray
            ),
            border = BorderStroke(1.dp, Color.Gray),
            onClick = { /*TODO*/ }) {
            Text(text = "Register User Account")
        }

        ElevatedButton(

            onClick = { /*TODO*/ }) {
            Text(text = "This is Elevated Button")
        }
        FilledTonalButton(
//            shape = CircleShape,
            onClick = { /*TODO*/ }) {
            Text(text = "Filled Tonal Button")
        }
        TextButton(
            modifier = Modifier.padding(0.dp),
            onClick = { /*TODO*/ }) {
            Text(text = "Read More Text Button")
        }
        IconButton(
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.Green
            ),
            onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Image, contentDescription = null)
        }
    }
}

@Preview(showBackground = true, apiLevel = 33)
@Composable
fun MaterialButtonsScreenPreview() {
    val context = LocalContext.current
    val navController = remember { NavController(context) }
    JC_UI_ComponentTheme() {
        MaterialButtonsScreen(
            navController = navController
        )
    }
}