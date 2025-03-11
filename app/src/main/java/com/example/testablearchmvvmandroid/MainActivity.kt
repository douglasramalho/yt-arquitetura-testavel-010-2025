package com.example.testablearchmvvmandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.testablearchmvvmandroid.ui.theme.TestableArchMvvmAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestableArchMvvmAndroidTheme {
                val viewModel = UserViewModel(applicationContext)
                val userName by viewModel.userName.collectAsStateWithLifecycle()

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    Greeting(
                        name = userName,
                        onNameChange = viewModel::saveUserName,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    onNameChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )

        var newName by remember { mutableStateOf("") }
        OutlinedTextField(
            value = newName,
            onValueChange = { newName = it },
        )

        Button(
            onClick = {
                onNameChange(newName)
            },
            modifier = Modifier
                .padding(top = 8.dp)
        ) {
            Text("Salvar Nome")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestableArchMvvmAndroidTheme {
        Greeting(
            name = "Android",
            onNameChange = {},
        )
    }
}