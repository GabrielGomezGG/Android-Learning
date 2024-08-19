package com.gg.newnavegationexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.gg.newnavegationexample.ui.theme.NewNavegationExampleTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            NewNavegationExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    SharedTransitionLayout {

                    }

                    NavHost(navController = navController, startDestination = Screen.Home) {

                        composable<Screen.Home> {
                            Home(
                                modifier = Modifier.padding(innerPadding),
                                navigateTo = {
                                    navController.navigate(Screen.Wea("Titititi"))
                                }
                            )
                        }

                        composable<Screen.Wea> {
                            val wea = it.toRoute<Screen.Wea>()
                            Wea(
                                modifier = Modifier.padding(innerPadding),
                                text = wea.id,
                                navigateTo = {
                                    navController.navigate(Screen.Home)
                                }
                            )
                        }

                    }
                }
            }
        }
    }
}

sealed interface Screen {
    @Serializable
    object Home : Screen

    @Serializable
    data class Wea(val id: String) : Screen
}

@Composable
fun Home(
    modifier: Modifier = Modifier,
    navigateTo: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Home",
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = navigateTo,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Text("Go to Wea")
        }
    }

}

@Composable
fun Wea(
    modifier: Modifier = Modifier,
    text: String,
    navigateTo: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Wea: $text",
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = navigateTo,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Text("Go to Home")
        }
    }
}
