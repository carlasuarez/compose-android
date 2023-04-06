package com.example.composetutorial

import android.content.Context
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items

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
    val todoList = remember { mutableStateListOf<String>() }
    var context = LocalContext.current

    Row {
        OutlinedTextField(
            value = text,
            label = { Text(text = "Add your item") },
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
            onValueChange = { item ->
                text = item
            }
        )

        Button(
            modifier = Modifier.padding(top = 10.dp),
            onClick = {
                todoList.add(text.text)
        }) {
            Text(text = "Add item")
        }
    }

    Spacer(modifier = Modifier.padding(8.dp))

    LazyColumn(modifier = Modifier.padding(10.dp)){
        items(todoList) { item -> Text(text = item)
        }
    }
}

private fun mToast(context: Context, text: String){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeDisplay()
}