package com.saurabhbhardwaj.jc_ui_component.screens

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.sharp.Accessibility
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.saurabhbhardwaj.jc_ui_component.components.AppTopBar
import com.saurabhbhardwaj.jc_ui_component.ui.theme.JC_UI_ComponentTheme


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MaterialChipScreen(navController: NavController) {
    var selectedElevatedChip by remember { mutableStateOf(true) }
    var elevatedFilterChip by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        AppTopBar(centerTitle = "Material Chip Screen" , onBackClick = {
            navController.popBackStack()
        })
        Spacer(modifier = Modifier.height(20.dp))
        AssistChip(
            onClick = { /*TODO*/ },
            enabled = true,
//            elevation = AssistChipDefaults.assistChipElevation(
//                elevation = 10.dp
//            ),
            label = { Text(text = "Default Assist chip") },
//            colors = AssistChipDefaults.assistChipColors(
//                containerColor = Color.Magenta,
//                leadingIconContentColor = Color.Red, trailingIconContentColor = Color.Green
//            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Sharp.Accessibility,
                    contentDescription = null,
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Sharp.Accessibility,
                    contentDescription = null,
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            }
        )
        ElevatedAssistChip(
            enabled = true,
            onClick = {
                Log.d("MyScreen", "Previewing MyScreen")
                selectedElevatedChip = !selectedElevatedChip
            },
            label = { Text(text = "Elevated Assist Chip") },
        )
        ElevatedFilterChip(
            selected = elevatedFilterChip,
            onClick = { elevatedFilterChip = !elevatedFilterChip },
//            border = FilterChipDefaults.filterChipBorder(
//            ),
            label = { Text(text = "Elevated Filter Chip") },
            leadingIcon = if (elevatedFilterChip) {
                {
                    Icon(imageVector = Icons.Filled.Done, contentDescription = null)
                }
            } else {
                null
            }
        )
        ElevatedSuggestionChip(
            onClick = { /*TODO*/ }, label = { Text(text = "Elevated Suggestion Chip") })


        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(vertical = 10.dp),
            text = "Scrollable Row Items",
            style = TextStyle( color = Color.Blue , fontSize = 16.sp)
        )

        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            repeat(9) { index ->
                AssistChip(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    onClick = { /* do something*/ },
                    label = { Text("Chip $index") }
                )
            }
        }


        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(vertical = 10.dp),
            text = "Row Items with a Wrap Property",
            style = TextStyle( color = Color.Blue , fontSize = 16.sp)
        )

        FlowRow(
            Modifier
                .fillMaxWidth(1f)
                .wrapContentHeight(align = Alignment.Top),
            horizontalArrangement = Arrangement.Start,
        ) {
            repeat(9) { index ->
                AssistChip(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .align(alignment = Alignment.CenterVertically),
                    onClick = { /* do something*/ },
                    label = { Text("Chip $index") }
                )
            }
        }
        var selected by remember { mutableStateOf(false) }
        InputChip(
            selected = selected,
            onClick = { selected = !selected },
            label = { Text("Input Chip") },
            avatar = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Localized description",
                    Modifier.size(InputChipDefaults.AvatarSize)
                )
            },
            trailingIcon = {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "")
            }
        )



    }
}


@Preview(showBackground = true)
//@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MaterialChipScreenPreview() {
    val context = LocalContext.current
    val navController = remember { NavController(context) }
    JC_UI_ComponentTheme() {
        MaterialChipScreen(
            navController = navController
        )
    }
}