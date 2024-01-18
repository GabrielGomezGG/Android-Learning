package com.titi.datetimeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.titi.datetimeexample.ui.theme.DateTimeExampleTheme
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DateTimeExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val state = rememberDatePickerState()

    var showDatePicker by remember { mutableStateOf(false) }

    var wea by remember {
        mutableStateOf("")
    }

    Column {

        Button(onClick = {
            showDatePicker = true
        }) {
            Text(text = "Show Date Picker")
        }

        Text(text = wea)

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = {
                    showDatePicker = false
                },
                confirmButton = {
                    TextButton(onClick = {
                        showDatePicker = false
                        state.selectedDateMillis?.let {
                            wea = Instant.ofEpochMilli(it)
                                .atZone(ZoneId.of("UTC"))
                                .toLocalDate().format(
                                    DateTimeFormatter.ofPattern("dd-MM-yyyy")
                                )
                        }
                    }) {
                        Text(text = "Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDatePicker = false
                    }) {
                        Text(text = "Cancel")
                    }
                },
            ) {
                DatePicker(
                    state = state,
//                    dateValidator = { timeInMillis ->
//                        val endCalenderDate = Calendar.getInstance()
//                        endCalenderDate.timeInMillis = timeInMillis
//                        endCalenderDate.set(Calendar.DATE, Calendar.DATE + 27)
//                        timeInMillis > Calendar.getInstance().timeInMillis - 172799999 && timeInMillis < endCalenderDate.timeInMillis
//                    },
                )
            }
        }
    }


}