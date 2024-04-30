
package com.saurabhbhardwaj.jc_ui_component.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun LaunchCustomSnackbar(
    key: Any?,
    snackbarHostState: SnackbarHostState,
    message: String,
    severity: SnackbarSeverity,
) {
    LaunchedEffect(key){
        snackbarHostState.showSnackbar(
            visuals = CustomSnackbarVisuals(
                actionLabel = null,
                duration = SnackbarDuration.Short,
                message = message,
                withDismissAction = false,
                severity = severity,
            )
        )
    }

}
