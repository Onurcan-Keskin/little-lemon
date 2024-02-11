package com.onurcan.little_lemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.onurcan.little_lemon.ui.screens.NavigationComposable
import com.onurcan.little_lemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    val sharedPreferences by lazy { getSharedPreferences("Little Lemon", MODE_PRIVATE) }
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationComposable(
                        context = applicationContext,
                        navController = navController
                    )
                }
            }
        }
    }
}