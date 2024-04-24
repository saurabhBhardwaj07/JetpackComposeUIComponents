package com.saurabhbhardwaj.jc_ui_component.screens

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import com.saurabhbhardwaj.jc_ui_component.components.AppTopBar
import com.saurabhbhardwaj.jc_ui_component.ui.theme.JC_UI_ComponentTheme


@Composable
fun AlertboxScreen(navController: NavController) {
    val openDialog = remember { mutableStateOf(false) }
    val openCustomDialog = remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        AppTopBar(centerTitle = "Alert Box Screen", onBackClick = {
            navController.popBackStack()
        })
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                openDialog.value = true

            }) {
            Icon(
                Icons.Filled.AddAlert,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Tap For Default Alert")
        }
        Button(
            onClick = {
                openCustomDialog.value = true
            }) {
            Icon(
                Icons.Filled.AddAlert,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Tap For Custom Alert Box")
        }
        if (openDialog.value)
            AlertDefaultDialog {
                openDialog.value = false
            }
        if (openCustomDialog.value)
            CustomeAlertDialog {
                openCustomDialog.value = false
            }
    }
}


@Composable
fun AlertDefaultDialog(onDissmiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = {
        },
        title = {
            Text(text = "Dialog with the Favourite icon", textAlign = TextAlign.Center)
        },
        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
        text = {
            Text(
                text = "This area typically contains the supportive text " +
                        "which presents the details regarding the Dialog's purpose.",
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            TextButton(
                onClick = onDissmiss
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDissmiss
            ) {
                Text("Dismiss")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomeAlertDialog(onDissmiss: () -> Unit) {
    AlertDialog(onDismissRequest = { }) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            tonalElevation = AlertDialogDefaults.TonalElevation,
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Text(
                    text = "Confirmation",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Are you sure want to perform this action?",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = onDissmiss,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(8.dp),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        border = BorderStroke(1.dp, Color.Black)

                    ) {
                        Text(text = "Cancel")
                    }
                    Button(
                        onClick = onDissmiss,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(8.dp),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(text = "Confirm")
                    }
                }
            }


        }
    }
}


@Preview(showBackground = true , apiLevel = 33)
//@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AlertboxScreenPreview() {
    val context = LocalContext.current
    val navController = remember { NavController(context) }
    JC_UI_ComponentTheme() {
        AlertboxScreen(
            navController = navController
        )
    }
}