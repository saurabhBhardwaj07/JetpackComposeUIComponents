package com.saurabhbhardwaj.jc_ui_component.screens
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter.*
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerScreen(navController: NavController) {


    var showDatePicker = remember { mutableStateOf(false) }
    val openDialog = remember { mutableStateOf(false) }
    val showDateRangePicker = remember { mutableStateOf(false) }
    val snackbarState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    SnackbarHost(hostState = snackbarState, Modifier)

    val dateRangePickerState = rememberDateRangePickerState()

    val dateDialogState = rememberMaterialDialogState()
    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }

    val formattedDate by remember {
        derivedStateOf {
            ofPattern("MMM dd, yyyy")
                .format(pickedDate)
        }
    }

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

        Button(
            onClick = { showDateRangePicker.value = !showDateRangePicker.value },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(text = "Show Date Range Picker")
        }


        Button(onClick = {
            dateDialogState.show()
        }, colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
            Text(text = "Material Date Picker")
        }

        Text(
            modifier = Modifier.padding(vertical = 20.dp),
            text = formattedDate, style = TextStyle(color = Color.Magenta, fontSize = 24.sp)
        )

        MaterialDialog(
            dialogState = dateDialogState,
            buttons = {
                positiveButton(text = "Ok") {

                }
                negativeButton(text = "Cancel")
            }
        ) {
            datepicker(
                initialDate = LocalDate.now(),
                title = "Pick a date",
                yearRange = LocalDate.now().year - 50..LocalDate.now().year - 13,
                allowedDateValidator = {
//                    it.dayOfMonth % 2 == 1
                    val dayOfWeek = it.dayOfWeek
                    dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY
                }
            ) {
                pickedDate = it
            }
        }


        if (showDatePicker.value)
            Column {
                DatePicker(state = datePickerState)
                Text(
                    "Selected date timestamp: ${datePickerState.selectedDateMillis ?: "no selection"}",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                )
            }

        if (showDateRangePicker.value)
            DateRangePicker(
                state = dateRangePickerState,
                showModeToggle = true,
                title = {
                    Text(
                        text = "Select date range to assign the chart", modifier = Modifier
                            .padding(16.dp)
                    )
                },
                colors = DatePickerDefaults.colors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.Black,
                    headlineContentColor = Color.Black,
                    weekdayContentColor = Color.Black,
                    subheadContentColor = Color.Black,
                    yearContentColor = Color.Green,
                    currentYearContentColor = Color.Red,
                    selectedYearContainerColor = Color.Red,
                    disabledDayContentColor = Color.Gray,
                    todayDateBorderColor = Color.Blue,
                    dayInSelectionRangeContainerColor = Color.LightGray,
                    dayInSelectionRangeContentColor = Color.White,
                    selectedDayContainerColor = Color.Black
                )
            )

        if (openDialog.value)
            DatePickerDialog(onDismissRequest = { openDialog.value = false }, confirmButton = {
                TextButton(
                    onClick = {
                        if (datePickerState.selectedDateMillis != null) {
                            val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                            val calendar = Calendar.getInstance()
                            calendar.timeInMillis = datePickerState.selectedDateMillis!!;

                            openDialog.value = false
                            snackScope.launch {
                                snackbarState.showSnackbar(formatter.format(calendar.time))
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

fun dateValidator(): (Long) -> Boolean {
    return { timeInMillis ->
        val endCalenderDate = Calendar.getInstance()
        endCalenderDate.timeInMillis = timeInMillis
        endCalenderDate.set(Calendar.DATE, Calendar.DATE + 20)
        timeInMillis > Calendar.getInstance().timeInMillis && timeInMillis < endCalenderDate.timeInMillis
    }
}


@RequiresApi(Build.VERSION_CODES.O)
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