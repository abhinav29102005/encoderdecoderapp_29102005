package com.example.encoderdecoderapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EncoderDecoderApp() {
    var inputEncode by remember { mutableStateOf("") }
    var shiftValue by remember { mutableStateOf("") }
    var encodedResult by remember { mutableStateOf("") }
    var inputDecode by remember { mutableStateOf("") }
    var decodedResult by remember { mutableStateOf("") }

    // Make the Column scrollable
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Enables scrolling
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            "ENCODER / DECODER APP",
            fontSize = 24.sp,
            color = Color.Blue,
            modifier = Modifier.padding(top = 60.dp, bottom = 16.dp)
        )

        // Section: Encoding
        Text("Encode a Message", style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(
            value = inputEncode,
            onValueChange = { inputEncode = it },
            label = { Text("Enter your message to encode") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = shiftValue,
            onValueChange = { shiftValue = it },
            label = { Text("Enter shift value") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                val shift = shiftValue.toIntOrNull() ?: 0
                encodedResult = encodeco(inputEncode, shift)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Encode")
        }
        if (encodedResult.isNotEmpty()) {
            Text(
                "Encoded Result: $encodedResult",
                fontSize = 16.sp,
                color = Color.Blue,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Divider
        Divider(Modifier.padding(vertical = 16.dp))

        // Section: Decoding
        Text("Decode a Message", style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(
            value = inputDecode,
            onValueChange = { inputDecode = it },
            label = { Text("Enter your message to decode") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = shiftValue,
            onValueChange = { shiftValue = it },
            label = { Text("Enter shift value") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                val shift = shiftValue.toIntOrNull() ?: 0
                decodedResult = encodeco(inputDecode, -shift)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Decode")
        }
        if (decodedResult.isNotEmpty()) {
            Text(
                "Decoded Result: $decodedResult",
                fontSize = 16.sp,
                color = Color.Blue,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

// Caesar Cipher Logic (Shared for Encoding and Decoding)
fun encodeco(text: String, shift: Int): String {
    val adjustedShift = shift % 26
    return text.map { char ->
        when {
            char.isUpperCase() -> {
                val shiftBase = 'A'.code
                ((char.code - shiftBase + adjustedShift + 26) % 26 + shiftBase).toChar()
            }
            char.isLowerCase() -> {
                val shiftBase = 'a'.code
                ((char.code - shiftBase + adjustedShift + 26) % 26 + shiftBase).toChar()
            }
            else -> char
        }
    }.joinToString("")
}


