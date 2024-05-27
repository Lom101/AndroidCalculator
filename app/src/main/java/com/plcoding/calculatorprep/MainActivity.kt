package com.plcoding.calculatorprep

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.plcoding.calculatorprep.ui.theme.CalculatorPrepTheme
import com.plcoding.calculatorprep.ui.theme.DarkBackground
import com.plcoding.calculatorprep.ui.theme.DarkSurface
import com.plcoding.calculatorprep.ui.theme.LightBackground
import com.plcoding.calculatorprep.ui.theme.LightSurface


@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorPrepTheme {
                MyApp()
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyApp(
) {
    // Состояние для хранения двух цветовых палитр
    val buttonColorsPalette1 = remember {
        listOf(
            LightBackground,
            LightSurface,
            Color.Black
        )
    }
    val buttonColorsPalette2 = remember {
        listOf(
            DarkBackground,
            DarkSurface,
            Color.White
        )
    }
    var isPalette1 by rememberSaveable { mutableStateOf(true) }
    // Выбираем текущую палитру в зависимости от значения isPalette1
    val currentButtonColors = if (isPalette1) buttonColorsPalette2 else buttonColorsPalette1

    val viewModel = viewModel<CalculatorViewModel>()
    val state = viewModel.state
    val buttonSpacing = 8.dp

//    val config = LocalConfiguration.current
//    val portraitMode = remember { mutableStateOf(config.orientation)}
//    if(portraitMode.value == Configuration.ORIENTATION_PORTRAIT)
    val config = LocalConfiguration.current
    val portraitMode = config.orientation == Configuration.ORIENTATION_PORTRAIT

    if (portraitMode)
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(currentButtonColors[0])
                .padding(16.dp)
        )
        {
            // Кнопка смены цветовой палитры
            Button(
                onClick = { isPalette1 = !isPalette1 },
                colors = ButtonDefaults.buttonColors(currentButtonColors[1], currentButtonColors[2])
            ) {
                Text(text = "Сменить тему")
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.spacedBy(buttonSpacing),
            )
            {
                Text(
                    text = state.number1 + (state.operation?.symbol ?: "") + state.number2,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    fontWeight = FontWeight.Light,
                    fontSize = 80.sp,
                    color = currentButtonColors[2],
                    maxLines = 2
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "AC",
                        color1 = currentButtonColors[1],
                        color2 = currentButtonColors[2],
                        modifier = Modifier
                            .aspectRatio(2f)
                            .weight(2f)
                    ) {
                        viewModel.onAction(CalculatorAction.Clear)
                    }
                    CalculatorButton(
                        symbol = "Del",
                        color1 = currentButtonColors[1],
                        color2 = currentButtonColors[2],
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    ) {
                        viewModel.onAction(CalculatorAction.Delete)
                    }
                    CalculatorButton(
                        symbol = "/",
                        color1 = currentButtonColors[1],
                        color2 = currentButtonColors[2],
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    ) {
                        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "7",
                        color1 = currentButtonColors[1],
                        color2 = currentButtonColors[2],
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    ) {
                        viewModel.onAction(CalculatorAction.Number(7))
                    }
                    CalculatorButton(
                        symbol = "8",
                        color1 = currentButtonColors[1],
                        color2 = currentButtonColors[2],
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    ) {
                        viewModel.onAction(CalculatorAction.Number(8))
                    }
                    CalculatorButton(
                        symbol = "9",
                        color1 = currentButtonColors[1],
                        color2 = currentButtonColors[2],
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    ) {
                        viewModel.onAction(CalculatorAction.Number(9))
                    }
                    CalculatorButton(
                        symbol = "x",
                        color1 = currentButtonColors[1],
                        color2 = currentButtonColors[2],
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    ) {
                        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "4",
                        color1 = currentButtonColors[1],
                        color2 = currentButtonColors[2],
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    ) {
                        viewModel.onAction(CalculatorAction.Number(4))
                    }
                    CalculatorButton(
                        symbol = "5",
                        color1 = currentButtonColors[1],
                        color2 = currentButtonColors[2],
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    ) {
                        viewModel.onAction(CalculatorAction.Number(5))
                    }
                    CalculatorButton(
                        symbol = "6",
                        color1 = currentButtonColors[1],
                        color2 = currentButtonColors[2],
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    ) {
                        viewModel.onAction(CalculatorAction.Number(6))
                    }
                    CalculatorButton(
                        symbol = "-",
                        color1 = currentButtonColors[1],
                        color2 = currentButtonColors[2],
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    ) {
                        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "1",
                        color1 = currentButtonColors[1],
                        color2 = currentButtonColors[2],
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    ) {
                        viewModel.onAction(CalculatorAction.Number(1))
                    }
                    CalculatorButton(
                        symbol = "2",
                        color1 = currentButtonColors[1],
                        color2 = currentButtonColors[2],
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    ) {
                        viewModel.onAction(CalculatorAction.Number(2))
                    }
                    CalculatorButton(
                        symbol = "3",
                        color1 = currentButtonColors[1],
                        color2 = currentButtonColors[2],
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    ) {
                        viewModel.onAction(CalculatorAction.Number(3))
                    }
                    CalculatorButton(
                        symbol = "+",
                        color1 = currentButtonColors[1],
                        color2 = currentButtonColors[2],
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    ) {
                        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "0",
                        color1 = currentButtonColors[1],
                        color2 = currentButtonColors[2],
                        modifier = Modifier
                            .aspectRatio(2f)
                            .weight(2f)
                    ) {
                        viewModel.onAction(CalculatorAction.Number(0))
                    }
                    CalculatorButton(
                        symbol = ".",
                        color1 = currentButtonColors[1],
                        color2 = currentButtonColors[2],
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    ) {
                        viewModel.onAction(CalculatorAction.Decimal)
                    }
                    CalculatorButton(
                        symbol = "=",
                        color1 = currentButtonColors[1],
                        color2 = currentButtonColors[2],
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    ) {
                        viewModel.onAction(CalculatorAction.Calculate)
                    }
                }
            }
        }
    }
    else{
        Row(modifier = Modifier
            .fillMaxSize()
            .background(currentButtonColors[0])
        )
        {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(currentButtonColors[0])
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = state.number1 + (state.operation?.symbol ?: "") + state.number2,
                        modifier = Modifier.weight(10f),
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Light,
                        fontSize = 80.sp,
                        color = currentButtonColors[2],
                        maxLines = 2
                    )
                    // Кнопка смены цветовой палитры
                    Button(
                        onClick = { isPalette1 = !isPalette1 },
                        modifier = Modifier
                            .weight(2f),
                        colors = ButtonDefaults.buttonColors(currentButtonColors[1], currentButtonColors[2])
                    ) {
                        Text(text = "Сменить тему")
                    }
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(currentButtonColors[0])
                    .padding(16.dp)
            ) {
                Column() {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                    ) {
                        CalculatorButton(
                            symbol = "AC",
                            color1 = currentButtonColors[1],
                            color2 = currentButtonColors[2],
                            modifier = Modifier
                                .aspectRatio(2f)
                                .weight(2f)
                        ) {
                            viewModel.onAction(CalculatorAction.Clear)
                        }
                        CalculatorButton(
                            symbol = "Del",
                            color1 = currentButtonColors[1],
                            color2 = currentButtonColors[2],
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                        ) {
                            viewModel.onAction(CalculatorAction.Delete)
                        }
                        CalculatorButton(
                            symbol = "/",
                            color1 = currentButtonColors[1],
                            color2 = currentButtonColors[2],
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                        ) {
                            viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                    ) {
                        CalculatorButton(
                            symbol = "7",
                            color1 = currentButtonColors[1],
                            color2 = currentButtonColors[2],
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                        ) {
                            viewModel.onAction(CalculatorAction.Number(7))
                        }
                        CalculatorButton(
                            symbol = "8",
                            color1 = currentButtonColors[1],
                            color2 = currentButtonColors[2],
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                        ) {
                            viewModel.onAction(CalculatorAction.Number(8))
                        }
                        CalculatorButton(
                            symbol = "9",
                            color1 = currentButtonColors[1],
                            color2 = currentButtonColors[2],
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                        ) {
                            viewModel.onAction(CalculatorAction.Number(9))
                        }
                        CalculatorButton(
                            symbol = "x",
                            color1 = currentButtonColors[1],
                            color2 = currentButtonColors[2],
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                        ) {
                            viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                    ) {
                        CalculatorButton(
                            symbol = "4",
                            color1 = currentButtonColors[1],
                            color2 = currentButtonColors[2],
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                        ) {
                            viewModel.onAction(CalculatorAction.Number(4))
                        }
                        CalculatorButton(
                            symbol = "5",
                            color1 = currentButtonColors[1],
                            color2 = currentButtonColors[2],
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                        ) {
                            viewModel.onAction(CalculatorAction.Number(5))
                        }
                        CalculatorButton(
                            symbol = "6",
                            color1 = currentButtonColors[1],
                            color2 = currentButtonColors[2],
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                        ) {
                            viewModel.onAction(CalculatorAction.Number(6))
                        }
                        CalculatorButton(
                            symbol = "-",
                            color1 = currentButtonColors[1],
                            color2 = currentButtonColors[2],
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                        ) {
                            viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                    ) {
                        CalculatorButton(
                            symbol = "1",
                            color1 = currentButtonColors[1],
                            color2 = currentButtonColors[2],
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                        ) {
                            viewModel.onAction(CalculatorAction.Number(1))
                        }
                        CalculatorButton(
                            symbol = "2",
                            color1 = currentButtonColors[1],
                            color2 = currentButtonColors[2],
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                        ) {
                            viewModel.onAction(CalculatorAction.Number(2))
                        }
                        CalculatorButton(
                            symbol = "3",
                            color1 = currentButtonColors[1],
                            color2 = currentButtonColors[2],
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                        ) {
                            viewModel.onAction(CalculatorAction.Number(3))
                        }
                        CalculatorButton(
                            symbol = "+",
                            color1 = currentButtonColors[1],
                            color2 = currentButtonColors[2],
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                        ) {
                            viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                    ) {
                        CalculatorButton(
                            symbol = "0",
                            color1 = currentButtonColors[1],
                            color2 = currentButtonColors[2],
                            modifier = Modifier
                                .aspectRatio(2f)
                                .weight(2f)
                        ) {
                            viewModel.onAction(CalculatorAction.Number(0))
                        }
                        CalculatorButton(
                            symbol = ".",
                            color1 = currentButtonColors[1],
                            color2 = currentButtonColors[2],
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                        ) {
                            viewModel.onAction(CalculatorAction.Decimal)
                        }
                        CalculatorButton(
                            symbol = "=",
                            color1 = currentButtonColors[1],
                            color2 = currentButtonColors[2],
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                        ) {
                            viewModel.onAction(CalculatorAction.Calculate)
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewMyColumn() {
    MyApp()
}





//modifier = Modifier
//.fillMaxWidth(),








//Column(
//modifier = Modifier
//.fillMaxWidth(),
//verticalArrangement = Arrangement.spacedBy(8.dp),
//horizontalAlignment = Alignment.CenterHorizontally
//) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(currentButtonColors[0])
//            .padding(16.dp)
//    ){
//        Text(
//            text = state.number1 + (state.operation?.symbol ?: "") + state.number2,
//            textAlign = TextAlign.End,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 32.dp),
//            fontWeight = FontWeight.Light,
//            fontSize = 80.sp,
//            color = currentButtonColors[2],
//            maxLines = 2
//        )
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
//        ) {
//            CalculatorButton(
//                symbol = "AC",
//                color1 = currentButtonColors[1],
//                color2 = currentButtonColors[2],
//                modifier = Modifier
//                    .aspectRatio(2f)
//                    .weight(2f)
//            ) {
//                viewModel.onAction(CalculatorAction.Clear)
//            }
//            CalculatorButton(
//                symbol = "Del",
//                color1 = currentButtonColors[1],
//                color2 = currentButtonColors[2],
//                modifier = Modifier
//                    .aspectRatio(1f)
//                    .weight(1f)
//            ) {
//                viewModel.onAction(CalculatorAction.Delete)
//            }
//            CalculatorButton(
//                symbol = "/",
//                color1 = currentButtonColors[1],
//                color2 = currentButtonColors[2],
//                modifier = Modifier
//                    .aspectRatio(1f)
//                    .weight(1f)
//            ) {
//                viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
//            }
//        }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
//        ) {
//            CalculatorButton(
//                symbol = "7",
//                color1 = currentButtonColors[1],
//                color2 = currentButtonColors[2],
//                modifier = Modifier
//                    .aspectRatio(1f)
//                    .weight(1f)
//            ) {
//                viewModel.onAction(CalculatorAction.Number(7))
//            }
//            CalculatorButton(
//                symbol = "8",
//                color1 = currentButtonColors[1],
//                color2 = currentButtonColors[2],
//                modifier = Modifier
//                    .aspectRatio(1f)
//                    .weight(1f)
//            ) {
//                viewModel.onAction(CalculatorAction.Number(8))
//            }
//            CalculatorButton(
//                symbol = "9",
//                color1 = currentButtonColors[1],
//                color2 = currentButtonColors[2],
//                modifier = Modifier
//                    .aspectRatio(1f)
//                    .weight(1f)
//            ) {
//                viewModel.onAction(CalculatorAction.Number(9))
//            }
//            CalculatorButton(
//                symbol = "x",
//                color1 = currentButtonColors[1],
//                color2 = currentButtonColors[2],
//                modifier = Modifier
//                    .aspectRatio(1f)
//                    .weight(1f)
//            ) {
//                viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
//            }
//        }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
//        ) {
//            CalculatorButton(
//                symbol = "4",
//                color1 = currentButtonColors[1],
//                color2 = currentButtonColors[2],
//                modifier = Modifier
//                    .aspectRatio(1f)
//                    .weight(1f)
//            ) {
//                viewModel.onAction(CalculatorAction.Number(4))
//            }
//            CalculatorButton(
//                symbol = "5",
//                color1 = currentButtonColors[1],
//                color2 = currentButtonColors[2],
//                modifier = Modifier
//                    .aspectRatio(1f)
//                    .weight(1f)
//            ) {
//                viewModel.onAction(CalculatorAction.Number(5))
//            }
//            CalculatorButton(
//                symbol = "6",
//                color1 = currentButtonColors[1],
//                color2 = currentButtonColors[2],
//                modifier = Modifier
//                    .aspectRatio(1f)
//                    .weight(1f)
//            ) {
//                viewModel.onAction(CalculatorAction.Number(6))
//            }
//            CalculatorButton(
//                symbol = "-",
//                color1 = currentButtonColors[1],
//                color2 = currentButtonColors[2],
//                modifier = Modifier
//                    .aspectRatio(1f)
//                    .weight(1f)
//            ) {
//                viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
//            }
//        }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
//        ) {
//            CalculatorButton(
//                symbol = "1",
//                color1 = currentButtonColors[1],
//                color2 = currentButtonColors[2],
//                modifier = Modifier
//                    .aspectRatio(1f)
//                    .weight(1f)
//            ) {
//                viewModel.onAction(CalculatorAction.Number(1))
//            }
//            CalculatorButton(
//                symbol = "2",
//                color1 = currentButtonColors[1],
//                color2 = currentButtonColors[2],
//                modifier = Modifier
//                    .aspectRatio(1f)
//                    .weight(1f)
//            ) {
//                viewModel.onAction(CalculatorAction.Number(2))
//            }
//            CalculatorButton(
//                symbol = "3",
//                color1 = currentButtonColors[1],
//                color2 = currentButtonColors[2],
//                modifier = Modifier
//                    .aspectRatio(1f)
//                    .weight(1f)
//            ) {
//                viewModel.onAction(CalculatorAction.Number(3))
//            }
//            CalculatorButton(
//                symbol = "+",
//                color1 = currentButtonColors[1],
//                color2 = currentButtonColors[2],
//                modifier = Modifier
//                    .aspectRatio(1f)
//                    .weight(1f)
//            ) {
//                viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Add))
//            }
//        }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
//        ) {
//            CalculatorButton(
//                symbol = "0",
//                color1 = currentButtonColors[1],
//                color2 = currentButtonColors[2],
//                modifier = Modifier
//                    .aspectRatio(2f)
//                    .weight(2f)
//            ) {
//                viewModel.onAction(CalculatorAction.Number(0))
//            }
//            CalculatorButton(
//                symbol = ".",
//                color1 = currentButtonColors[1],
//                color2 = currentButtonColors[2],
//                modifier = Modifier
//                    .aspectRatio(1f)
//                    .weight(1f)
//            ) {
//                viewModel.onAction(CalculatorAction.Decimal)
//            }
//            CalculatorButton(
//                symbol = "=",
//                color1 = currentButtonColors[1],
//                color2 = currentButtonColors[2],
//                modifier = Modifier
//                    .aspectRatio(1f)
//                    .weight(1f)
//            ) {
//                viewModel.onAction(CalculatorAction.Calculate)
//            }
//        }
//    }
//}