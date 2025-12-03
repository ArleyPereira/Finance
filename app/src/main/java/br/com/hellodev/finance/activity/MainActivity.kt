package br.com.hellodev.finance.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.finance.navigation.AppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloTheme {
                AppNavHost(
                    navHostController = rememberNavController()
                )
            }
        }
    }
}