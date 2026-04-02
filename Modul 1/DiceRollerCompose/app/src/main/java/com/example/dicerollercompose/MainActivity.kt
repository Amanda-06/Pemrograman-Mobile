package com.example.dicerollercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerApp()
        }
    }
}

@Composable
fun DiceRollerApp() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var dice1Value by remember { mutableStateOf(0) }
    var dice2Value by remember { mutableStateOf(0) }

    var isRolling by remember { mutableStateOf(false) }

    val rotation1 = remember { Animatable(0f) }
    val rotation2 = remember { Animatable(0f) }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    containerColor = Color(0xFF1976D2),
                    contentColor = Color.White,
                    shape = RoundedCornerShape(8.dp),
                    snackbarData = data
                )
            }
        },
        containerColor = Color(0xFFE3F2FD)
    ) { contentPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                DiceImage(diceValue = dice1Value, rotation = rotation1.value)
                DiceImage(diceValue = dice2Value, rotation = rotation2.value)
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    if (isRolling) return@Button

                    scope.launch {
                        isRolling = true
                        snackbarHostState.currentSnackbarData?.dismiss()

                        val job1 = launch {
                            rotation1.animateTo(
                                targetValue = rotation1.value + 360f,
                                animationSpec = tween(durationMillis = 120)
                            )
                        }
                        val job2 = launch {
                            rotation2.animateTo(
                                targetValue = rotation2.value + 360f,
                                animationSpec = tween(durationMillis = 120)
                            )
                        }

                        job1.join()
                        job2.join()

                        dice1Value = (1..6).random()
                        dice2Value = (1..6).random()

                        val message = if (dice1Value == dice2Value) {
                            "Selamat, anda dapat dadu double!"
                        } else {
                            "Anda belum beruntung!"
                        }
                        snackbarHostState.showSnackbar(message)

                        isRolling = false
                    }
                },
                enabled = !isRolling,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1976D2),
                    disabledContainerColor = Color(0xFF90CAF9)
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Roll",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun DiceImage(diceValue: Int, rotation: Float) {
    val imageResource = when (diceValue) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_0
    }

    Image(
        painter = painterResource(id = imageResource),
        contentDescription = "Dadu $diceValue",
        modifier = Modifier
            .size(200.dp)
            .graphicsLayer { rotationZ = rotation }
    )
}

@Preview(showBackground = true)
@Composable
fun DiceRollerPreview() {
    DiceRollerApp()
}