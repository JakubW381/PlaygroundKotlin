package com.example.playground

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playground.ui.theme.PlaygroundTheme
import java.util.UUID

class MainActivity : ComponentActivity() {
    val buttonColors = ButtonColors(
        Color.hsv(178.0f,1.0f,0.30f),
        Color.White,
        Color.Transparent,
        Color.Transparent)


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaygroundTheme {
                Scaffold(
//                    floatingActionButton = {
//                        FloatingActionButton(
//                            onClick = {
//
//                            },
//                            containerColor = Color.hsv(178.0f,1.0f,0.30f),
//                            contentColor = Color.White,
//                            elevation = FloatingActionButtonDefaults.elevation(5.dp)
//                        )
//                        {
//                            Text("+")
//                        }
//                    },
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarColors(
                                Color.hsv(180.0f,0.96f,0.21f),
                                Color.Transparent,
                                Color.Transparent,
                                Color.White,
                                Color.Transparent),
                            title = { Text("Top App Bar", fontFamily = FontFamily.Serif)}
                        )
                    },
                    bottomBar = {
                        BottomAppBar(containerColor = Color.hsv(180.0f,0.96f,0.21f)) {
                            Row (
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ){
                                Button(
                                    onClick = {},
                                    colors = buttonColors
                                ) { Text("BUTTON",fontFamily = FontFamily.Serif , fontWeight = FontWeight.Bold) }
                                Button(
                                    onClick = {},colors = buttonColors
                                ) { Text("BUTTON",fontFamily = FontFamily.Serif,fontWeight = FontWeight.Bold) }
                                Button(
                                    onClick = {  },colors = buttonColors
                                ) { Text("BUTTON",fontFamily = FontFamily.Serif,fontWeight = FontWeight.Bold) }
                            }
                        }
                    }

                ) { innerPadding ->
                    Column (
                        modifier = Modifier.fillMaxSize().background(Color.hsv(179.0f,0.99f,0.35f)).padding(innerPadding)
                    ){
                        Contents()
                            }
                        }
                    }
                }
            }
        }




@Composable
fun Contents(){

    var listOfNotes by rememberSaveable {mutableStateOf(listOf<Notes>())}
    val cardColors = CardColors(Color.hsv(178.0f,1.0f,0.25f), Color.White, Color.Transparent, Color.Transparent)

    var noteTitle by remember { mutableStateOf("") }
    var noteContent by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog){
        AlertDialog(
            containerColor = Color.hsv(180.0f,0.96f,0.21f),
            textContentColor = Color.White,
            titleContentColor = Color.White,
            onDismissRequest = {showDialog = false},
            confirmButton = {
                Button(
                    onClick = {
                        if (noteTitle == ""){
                            noteTitle = "New Note"
                        }
                        val note : Notes = Notes(
                            title = noteTitle,
                            content = noteContent,
                            index = (listOfNotes.size+1)
                        )
                        listOfNotes = listOfNotes + note
                        showDialog = false
                        noteTitle = ""
                        noteContent = ""
                    },
                    colors = ButtonColors(
                        Color.hsv(178.0f,1.0f,0.30f),
                        Color.White,
                        Color.Transparent,
                        Color.Transparent)
                ) {
                    Text("ADD")
                }
            },
            title = { Text("Write down your thoughts") },
            text = {
                Column {
                    Text("Title")
                    TextField(value = noteTitle, onValueChange = {noteTitle = it},
                        maxLines = 1,
                        colors = TextFieldDefaults.colors(
                        cursorColor = Color.White,
                        focusedContainerColor = Color.hsv(178.0f,1.0f,0.50f),
                        unfocusedContainerColor = Color.hsv(178.0f,1.0f,0.30f),
                            focusedTextColor= Color.White,
                            unfocusedTextColor= Color.White,
                    ))
                    Text("Content")
                    TextField(value = noteContent,
                        onValueChange = {noteContent= it},
                        minLines = 3, maxLines = 3,
                        colors = TextFieldDefaults.colors(
                            cursorColor = Color.White,
                            focusedContainerColor = Color.hsv(178.0f,1.0f,0.50f),
                            unfocusedContainerColor = Color.hsv(178.0f,1.0f,0.30f),
                            focusedTextColor= Color.White,
                            unfocusedTextColor= Color.White,
                        )

                    )
                }
            }
        )
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = Modifier.fillMaxSize().padding(16.dp),
        ) {
        items(listOfNotes) { Note ->
            Card(
                colors = cardColors,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .defaultMinSize(0.dp,0.dp),
                elevation = CardDefaults.cardElevation(5.dp),
            ) {
                Text(
                    text = "${Note.index} : ${Note.title}",
                    modifier = Modifier.padding(16.dp),
                    fontFamily = FontFamily.Serif,
                    fontSize = 25.sp
                )
                Text(
                    text = Note.content,
                    modifier = Modifier.padding(16.dp)
                )
                HorizontalDivider(
                    color = Color.White,
                    modifier = Modifier.absolutePadding(0.dp,30.dp)
                )
                IconButton(
                    onClick = {listOfNotes = listOfNotes.filter { it.index != Note.index }},
                    modifier = Modifier.align(Alignment.CenterHorizontally).absolutePadding(0.dp,0.dp,0.dp,0.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color.White)
                }
            }
        }
        item {
            Card(
                colors = cardColors,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable {
                        showDialog = true
                    }
                ,
                elevation = CardDefaults.cardElevation(5.dp),
            ){
                Text("ADD CARD",
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(0.dp,80.dp))
            }
        }
    }
}