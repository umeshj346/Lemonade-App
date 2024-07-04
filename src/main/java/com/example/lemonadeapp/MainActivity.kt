package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeAppTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    LemonadeApp()
                }
            }
        }
    }
}


@Preview
@Composable
fun LemonadeApp(){
    LemonadeWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}


@Composable
fun LemonadeWithButtonAndImage(modifier : Modifier = Modifier){
    var arrayIndex by remember {
        mutableIntStateOf(0)
    }
    var clicks by remember {
        mutableIntStateOf(0)
    }
    var tap by remember {
        mutableIntStateOf((2..4).random())
    }
    val imageArray = arrayOf(painterResource(id = R.drawable.lemon_tree),
        painterResource(id = R.drawable.lemon_squeeze),
        painterResource(id = R.drawable.lemon_drink),
        painterResource(id = R.drawable.lemon_restart))

    val descryptionArray = arrayOf(
        stringResource(id = R.string.lemon_tree),
        stringResource(id = R.string.lemon),
        stringResource(id = R.string.glass_of_lemonade),
        stringResource(id = R.string.empty_glass))
    
    val textArray = arrayOf(
        stringResource(R.string.tap_the_lemon_tree_to_select_a_lemon),
        stringResource(id = R.string.Lemon_Tree),
        stringResource(id = R.string.Drink_Lemonade),
        stringResource(id = R.string.Drink_Again)
    )
    val requiredTapping = arrayOf(1, tap, 1, 1)
    Column(modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Button(onClick = {
            clicks += 1
            if(clicks == requiredTapping[arrayIndex]){
                clicks = 0
                arrayIndex+=1
            }
            if(arrayIndex == 2){
                tap = (2..4).random()
            }
            if(arrayIndex > 3){
                arrayIndex = 0
            }
            },
        ) {
            Image(painter = imageArray[arrayIndex],
                contentDescription = descryptionArray[arrayIndex])
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = textArray[arrayIndex], fontSize = 20.sp)
    }

}

