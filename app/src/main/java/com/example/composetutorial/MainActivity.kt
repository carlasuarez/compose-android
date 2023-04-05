package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeDisplay()
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun HomeDisplay() {
    Column {
        MessageCard(msg = Message("Jenny", "Jetpack Compose"))
        Spacer(modifier = Modifier.height(4.dp))
        SimpleTextField()
    }
}

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image (
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = msg.author)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = msg.body)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun SimpleTextField() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Row {
        OutlinedTextField(
            value = text,
            label = { Text(text = "Add your item") },
            onValueChange = { item ->
                text = item
            }
        )
        Button(onClick = {
            // agregar a la lista
        }) {
            Text(text = "Add item")
        }
    }
    LazyColumn{
        items(5) { index ->
            Text(text = "Item: $index")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeDisplay()
}