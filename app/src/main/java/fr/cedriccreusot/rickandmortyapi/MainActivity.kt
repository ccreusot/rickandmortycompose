package fr.cedriccreusot.rickandmortyapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import fr.cedriccreusot.rickandmortyapi.domain.model.Character
import fr.cedriccreusot.rickandmortyapi.ui.theme.RickAndMortyComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RickAndMortyComposeTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    RickAndMortyComposeTheme {
        Scaffold(
            topBar = {
                TopAppBar {
                    Text(text = "Rick & Morty Compose")
                }
            }
        ) {

        }
    }
}

@Composable
fun CharacterCard(character: Character) {
    RickAndMortyComposeTheme {
        Card {
            Column {
                Image(
                    painter = rememberImagePainter(character.image),
                    contentDescription = "Image of ${character.name}",
                    modifier = Modifier.size(128.dp)
                )
            }
        }
    }
}