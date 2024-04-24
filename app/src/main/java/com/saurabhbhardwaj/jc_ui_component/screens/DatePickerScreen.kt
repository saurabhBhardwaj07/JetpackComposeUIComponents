package com.saurabhbhardwaj.jc_ui_component.screens

import android.os.Build
import android.provider.CalendarContract.Colors
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.saurabhbhardwaj.jc_ui_component.components.AppTopBar
import com.saurabhbhardwaj.jc_ui_component.ui.theme.JC_UI_ComponentTheme
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneId
import java.util.Calendar
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerScreen(navController: NavController) {

    var showDatePicker = remember { mutableStateOf(false) }
    val openDialog = remember { mutableStateOf(false) }
    val snackbarState = remember { SnackbarHostState()}
    val snackScope = rememberCoroutineScope()
    SnackbarHost(hostState = snackbarState , Modifier)

    //* assign datetime now
//                val initialDateMillis = Calendar.getInstance().timeInMillis

    //* handle the initialDateMillis
//                val calendar = Calendar.getInstance()
//                calendar.add(Calendar.YEAR, -1)
//                calendar.add(Calendar.MONTH, 3)
//                val initialDateMillis = calendar.timeInMillis
//
//                val datePickerState =
//                    rememberDatePickerState(initialSelectedDateMillis = initialDateMillis)

    val datePickerState = rememberDatePickerState(
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val dayOfWeek =
                        Instant.ofEpochMilli(utcTimeMillis).atZone(ZoneId.of("UTC"))
                            .toLocalDate().dayOfWeek
                    dayOfWeek != DayOfWeek.SUNDAY && dayOfWeek != DayOfWeek.SATURDAY
                } else {
                    val calender = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                    calender.timeInMillis = utcTimeMillis
                    calender[Calendar.DAY_OF_WEEK] != Calendar.SATURDAY && calender[Calendar.DAY_OF_WEEK] != Calendar.SUNDAY
                }
            }

            override fun isSelectableYear(year: Int): Boolean {
                return year > 2022
            }
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        AppTopBar(centerTitle = "Date Picker", onBackClick = {
            navController.popBackStack()
        })

        Button(
            onClick = { showDatePicker.value = !showDatePicker.value },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(text = showDatePicker.let {
                if (true) "Hide Date Picker" else "Show Data Picker"
            })
        }

        Button(
            onClick = { openDialog.value = !openDialog.value },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(text = "Show Date Dialog Picker")
        }
        if (showDatePicker.value)
            Column {
                DatePicker(state = datePickerState)
                Text(
                    "Selected date timestamp: ${datePickerState.selectedDateMillis ?: "no selection"}",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                )
            }

        if (openDialog.value)
            DatePickerDialog(onDismissRequest = { openDialog.value = false }, confirmButton = {
                TextButton(
                    onClick = {
                        if(datePickerState.selectedDateMillis != null){
                            openDialog.value = false
                            snackScope.launch {
                                snackbarState.showSnackbar( "${datePickerState.selectedDateMillis}")
                            }
                        }
                    }
                ) {
                    Text(text = "Select Date")
                }
            }, dismissButton = {
                TextButton(onClick = { openDialog.value = false }) {
                    Text(text = "Cancel")
                }
            }) {
                DatePicker(state = datePickerState)
            }
    }
}


@Preview(showBackground = true, apiLevel = 33)
//@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DatePickerPagePreview() {
    val context = LocalContext.current
    val navController = remember { NavController(context) }
    JC_UI_ComponentTheme() {
        DatePickerScreen(
            navController = navController
        )
    }
}