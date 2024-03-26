package com.example.numberguessing

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.numberguessing.ui.theme.NumberGuessingTheme
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGuessingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NumberGuessingGame()
                }
            }
        }
    }
}

@Composable
fun NumberGuessingGame() {
    var secretNumber by remember { mutableStateOf(Random.nextInt(1, 101)) }
    var guessText by remember { mutableStateOf(TextFieldValue()) }
    var message by remember { mutableStateOf("") }
    var attempts by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Number Guessing Game!", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = guessText,
            onValueChange = {
                guessText = it
            },
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 20.sp),
            modifier = Modifier.width(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val guess = guessText.text.toIntOrNull()
                if (guess != null) {
                    attempts++
                    when {
                        guess < secretNumber -> message = "Too low! Try again."
                        guess > secretNumber -> message = "Too high! Try again."
                        else -> {
                            message = "Congratulations! You've guessed the number $secretNumber correctly in $attempts attempts!"
                            secretNumber = Random.nextInt(1, 101)
                            attempts = 0
                        }
                    }
                } else {
                    message = "Please enter a valid number."
                }
            }
        ) {
            Text("Guess")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(message, color = Color.Red, fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NumberGuessingTheme {
        NumberGuessingGame()
    }
}