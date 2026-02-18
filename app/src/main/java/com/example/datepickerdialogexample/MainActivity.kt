package com.example.datepickerdialogexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.datepickerdialogexample.ui.theme.DatePickerDialogExampleTheme
import java.text.DateFormat

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DatePickerDialogExampleTheme {
                MainScreen()
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun MainScreen() {
    var isDatePickerDialogOpen by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    val dateFormatterLocal = DateFormat.getDateInstance()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Button(
            onClick = { isDatePickerDialogOpen = true },
            modifier = Modifier.padding(innerPadding).padding(16.dp)
        ) {
            Text(
                if (selectedDate != null)
                    dateFormatterLocal.format(selectedDate)
                else "Show Date Picker Dialog"
            )
        }
        if (isDatePickerDialogOpen) {
            val datePickerState = rememberDatePickerState()
            DatePickerDialog(
                onDismissRequest = { /* no reaction */ },
                confirmButton = {
                    TextButton(onClick = {
                        isDatePickerDialogOpen = false
                        selectedDate = datePickerState.selectedDateMillis
                    })
                    { Text("OK") }
                },
                dismissButton = {
                    TextButton(onClick = { isDatePickerDialogOpen = false })
                    { Text("Cancel") }
                }) {
                DatePicker(state = datePickerState)
            }
        }
    }
}