package com.example.composeapiconsume.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeapiconsume.Greeting
import com.example.composeapiconsume.android.utils.services.room.MainBoxMixin
import com.example.composeapiconsume.android.utils.services.room.entity.UserLoginEntity
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltAndroidApp
class MainActivity : ComponentActivity() {
    private lateinit var mainBoxMixin: MainBoxMixin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBoxMixin = MainBoxMixin(this)

        // Example: Adding user data
        CoroutineScope(Dispatchers.IO).launch {
            val user = UserLoginEntity(
                token = "example_token",
                fcm = "example_fcm",
                language = "en",
                theme = "light",
                locale = "en_US",
                isLogin = true,
                tokenData = "example_token_data"
            )
            mainBoxMixin.addData(user)

            // Retrieve user data
            val retrievedUser = mainBoxMixin.getData()
            println("User token: ${retrievedUser?.token}")
        }
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingView(Greeting().greet())
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
