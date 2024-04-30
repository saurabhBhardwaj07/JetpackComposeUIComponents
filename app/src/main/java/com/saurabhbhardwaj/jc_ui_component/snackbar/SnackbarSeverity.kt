package com.saurabhbhardwaj.jc_ui_component.snackbar
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import java.util.UUID

enum class SnackbarSeverity{
    INFO, ERROR
}

class SnackbarSeverityProvider: PreviewParameterProvider<SnackbarSeverity> {
    override val values: Sequence<SnackbarSeverity>
        get() = SnackbarSeverity.entries.asSequence()
}

data class SnackbarViewEvent(
    val message: String,
    val severity: SnackbarSeverity = SnackbarSeverity.INFO,
    val eventId: UUID = UUID.randomUUID(),
)