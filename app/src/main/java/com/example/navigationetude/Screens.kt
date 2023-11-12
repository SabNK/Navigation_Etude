package com.example.navigationetude

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination

@BottomNavGraph(start = true)
@Destination
@Composable
fun Screen1() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Screen # 1", fontSize = 20.sp)
        }
    }
}

@BottomNavGraph
@Destination
@Composable
fun Screen2() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Screen # 2 from from", fontSize = 20.sp)
        }
    }
}

@BottomNavGraph
@Destination
@Composable
fun Screen3() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Screen # 3", fontSize = 20.sp)
        }
    }
}

@Destination
@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Settings Screen", fontSize = 20.sp)
        }
    }
}