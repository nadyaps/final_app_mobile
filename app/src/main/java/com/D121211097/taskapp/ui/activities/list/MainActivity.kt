package com.D121211097.task_app.ui.activities.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.D121211097.task_app.ui.activities.detail.DetailActivity
import com.D121211097.task_app.ui.theme.Task_appTheme
import com.D121211097.taskapp.data.models.Food


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Task_appTheme {
                // A surface container using the 'background' color from the theme
                Surface (modifier=Modifier.fillMaxSize(),color=MaterialTheme.colorScheme.background){
                    Column {
                        CenterAlignedTopAppBar(
                            modifier = Modifier.background(Color.Red),
                            title = { Text(text="CATEGORY FOOD", fontWeight = FontWeight.SemiBold)  })

                        val listViewModel : ListViewModel = viewModel(factory = ListViewModel.Factory)
                        ListFoodScreen(listViewModel.mainUiState)
                    }
                }

            }
        }
    }
    @Composable
    private fun ListFoodScreen(mainUiState: MainUiState, modifier: Modifier = Modifier, ){
        when (mainUiState){
            is MainUiState.Loading -> Text(text = "Loading", fontSize = 16.sp)
            is MainUiState.Error -> Text(text = "Error", fontSize = 16.sp)
            is MainUiState.Success -> FoodList(mainUiState.Food)
        }
    }

    @Composable
    fun FoodList(mainUiState: List<Food>, modifier: Modifier = Modifier){
        LazyColumn(modifier = modifier){
            items(mainUiState){ Foods->
                FoodItem(food = Foods)

            }
        }
    }
    @Composable
    fun FoodItem(food: Food?){
        if (food != null) {
        Box(
            modifier = Modifier
                .padding(30.dp)
                .shadow(2.dp, shape = RoundedCornerShape(3.dp))
                .clickable {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("Food", food)
                    startActivity(intent)
                }
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ){

                //Food Photo
                FoodImage(url = food.strCategoryThumb)
                //Food Details
                Spacer(modifier =Modifier.height(8.dp))
                Text(
                    text = food.idCategory.toString(),
                    style = MaterialTheme.typography.headlineSmall,
                )
                Spacer(modifier =Modifier.height(8.dp))
                Text(
                    text = food.strCategory.toString(),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        }
    }
    @Composable
    fun FoodImage(url: String?) {
        val painter = rememberImagePainter(data = url)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop)
    }
}
