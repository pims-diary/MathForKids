package com.example.math_for_kids.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OptionsGrid(options: List<Int>, onOptionSelected: (Int) -> Unit, enabled: Boolean = true) {
    Column {
        for (i in options.indices step 2) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                OptionButton(option = options[i], onOptionSelected = onOptionSelected, enabled = enabled)
                OptionButton(option = options[i + 1], onOptionSelected = onOptionSelected, enabled = enabled)
            }
        }
    }
}

@Composable
fun OptionButton(option: Int, onOptionSelected: (Int) -> Unit,  enabled: Boolean = true) {
    Button(onClick = { onOptionSelected(option) }, enabled = enabled) {
        Text(text = option.toString())
    }
}