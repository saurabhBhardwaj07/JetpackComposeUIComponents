package com.saurabhbhardwaj.jc_ui_component.snackbar
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.saurabhbhardwaj.jc_ui_component.ui.theme.JC_UI_ComponentTheme
import com.saurabhbhardwaj.jc_ui_component.ui.theme.colors
import com.saurabhbhardwaj.jc_ui_component.ui.theme.dimensions
import com.saurabhbhardwaj.jc_ui_component.ui.theme.shapes
import java.util.UUID


@Composable
fun CustomSnackbar(
    message: String,
    severity:
    SnackbarSeverity= SnackbarSeverity.INFO,
) {

    val color = when (severity) {
        SnackbarSeverity.INFO -> colors.onSurface
        SnackbarSeverity.ERROR -> colors.error
    }

    val icon = when(severity){
        SnackbarSeverity.INFO -> Icons.Outlined.Info
        SnackbarSeverity.ERROR -> Icons.Rounded.Warning
    }

    ElevatedCard(
        modifier = Modifier
            .padding(dimensions.gapS)
            .border(1.dp, color, shapes.small),
        shape = shapes.small
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(dimensions.gapL),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.padding(end = dimensions.gapL)
            )
            Text(text = message, color = color)
        }
    }
}



@Preview(widthDp = 360, heightDp = 120)
@Composable
private fun CustomSnackbarPreview(
    @PreviewParameter(SnackbarSeverityProvider::class) severity: SnackbarSeverity
) {
    JC_UI_ComponentTheme {
        Scaffold { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                CustomSnackbar(
                    message = "This is a snackbar with the severity level \"${severity.name}\"",
                    severity = severity
                )
            }
        }
    }
}