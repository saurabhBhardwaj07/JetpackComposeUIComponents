package com.saurabhbhardwaj.jc_ui_component.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.saurabhbhardwaj.jc_ui_component.components.AppTopBar
import com.saurabhbhardwaj.jc_ui_component.ui.theme.JC_UI_ComponentTheme


@Composable
fun SelectableUiComponentsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AppTopBar(centerTitle = "Selectable UI Picker", onBackClick = {
            navController.popBackStack()
        })
        Checkboxes()
        MySwitch()
        MyRadioButtons()

    }
}


@Composable
private fun MyRadioButtons() {
    val radioButton = remember {
        mutableStateListOf(
            ToggleableInfo(isChecked = false, text = "Photo"),
            ToggleableInfo(isChecked = false, text = "Videos"),
            ToggleableInfo(isChecked = false, text = "Audio")
        )
    }

    radioButton.forEachIndexed { index, value ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 22.dp)
                .clickable {
                    radioButton.replaceAll {
                        it.copy(
                            isChecked = it.text == value.text
                        )
                    }
                }
        ) {
            RadioButton(selected = value.isChecked, onClick = {
                radioButton.replaceAll {
                    it.copy(
                        isChecked = it.text == value.text
                    )
                }
            })
            Text(text = value.text)
        }

    }

}

@Composable
private fun MySwitch() {

    var switch by remember {
        mutableStateOf(ToggleableInfo(isChecked = true, text = "Dark Mode"))
    }

    Row(
        modifier = Modifier.padding(horizontal = 22.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = switch.text)
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = switch.isChecked,
            onCheckedChange = {
                switch = switch.copy(isChecked = it)
            },
            thumbContent = {
                Icon(
                    imageVector = if (switch.isChecked) {
                        Icons.Default.Check
                    } else {
                        Icons.Default.Close
                    }, contentDescription = null
                )
            }
        )
    }
}


data class ToggleableInfo(
    val isChecked: Boolean,
    val text: String
)

@Composable
private fun Checkboxes() {
    val checkboxes = remember {
        mutableStateListOf(
            ToggleableInfo(isChecked = false, text = "Photo"),
            ToggleableInfo(isChecked = false, text = "Videos"),
            ToggleableInfo(isChecked = false, text = "Audio")
        )
    }
    var triState by remember {
        mutableStateOf(ToggleableState.Indeterminate)
    }

    val toggleTriState = {
        triState = when (triState) {
            ToggleableState.Indeterminate -> ToggleableState.On
            ToggleableState.On -> ToggleableState.Off
            else -> ToggleableState.On
        }
        checkboxes.indices.forEach { index ->
            checkboxes[index] = checkboxes[index].copy(
                isChecked = triState == ToggleableState.On
            )
        }
    }

    Row(
        modifier = Modifier.clickable {
            toggleTriState()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        TriStateCheckbox(state = triState, onClick = toggleTriState)
        Text(text = "File Types")
    }
    checkboxes.forEachIndexed { index, info ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 20.dp)
                .clickable {
                    checkboxes[index] = info.copy(
                        isChecked = !info.isChecked
                    )
                }
        ) {
            Checkbox(
                checked = info.isChecked,
                onCheckedChange = {
                    checkboxes[index] = info.copy(
                        isChecked = it
                    )
                }
            )
            Text(text = info.text)
        }
    }
}


@Preview(showBackground = true, apiLevel = 33)
@Composable
fun SelectableUiComponentsPreview() {
    val context = LocalContext.current
    val navController = remember { NavController(context) }
    JC_UI_ComponentTheme() {
        SelectableUiComponentsScreen(
            navController = navController
        )
    }
}