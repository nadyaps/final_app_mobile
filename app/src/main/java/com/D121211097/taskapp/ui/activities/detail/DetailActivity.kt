package com.D121211097.task_app.ui.activities.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.D121211097.task_app.ui.theme.Task_appTheme
import com.D121211097.taskapp.data.models.Food

class DetailActivity:ComponentActivity() {
    private var selectedFood: Food? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedFood = intent.getParcelableExtra("Food")
        setContent {
            Task_appTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    DetailScreen(selectedFood)
                }
            }
        }
    }

    @Composable
    fun DetailScreen(food: Food?){
        if (food != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
                .verticalScroll(rememberScrollState())
        ) {
            //Food Photo
            FoodImage(url = food.strCategoryThumb)
            //Food Details
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = selectedFood?.idCategory.toString(),style=MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = selectedFood?.strCategory.toString(),style=MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = selectedFood?.strCategoryDescription.toString(),style=MaterialTheme.typography.bodyLarge)
            }
        }
        else {
            // Handle case when food data is null
            Text("Food details not available.")
        }
    }
    @Composable
    fun FoodImage(url: String?) {
        if (url != null && url.isNotEmpty()) {
            Image(
                painter = rememberImagePainter(data = url),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.background)
            )
        } else {
            // Placeholder or error image when URL is not available
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                // Handle error or placeholder UI
            }
        }
    }
}