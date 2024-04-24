package com.saurabhbhardwaj.jc_ui_component.navigation

sealed class Screens(val route: String){
    object HomeScreenRoute : Screens(route = "Home")
    object AlertScreenRoute : Screens(route = "Alert")
    object ChipsScreenRoute : Screens(route = "Chips")
    object DatePickerScreenRoute : Screens(route = "DatePicker")
}