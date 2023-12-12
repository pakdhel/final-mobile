package com.D121211009.nasa.ui.activities.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.D121211009.nasa.data.models.Photo
import com.D121211009.nasa.ui.theme.NASATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NASATheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val mainViewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
                    ListPhotosScreen(mainViewModel.mainUiState)
                }
            }
        }
    }

    @Composable
    private fun ListPhotosScreen(photoUiState: MainUiState, modifier: Modifier = Modifier) {
        when (photoUiState) {
            is MainUiState.Loading -> Text(text = "Loading Photos", fontSize = 16.sp)
            is MainUiState.Error -> Text(text = "Error Occurred", fontSize = 16.sp)
            is MainUiState.Success -> PhotoList(photoUiState.photos)
        }
    }

    @Composable
    fun PhotoList(photos: List<Photo>, modifier: Modifier = Modifier) {
        LazyColumn(modifier = modifier) {
            items(photos) { photo ->
                PhotoItem(photo = photo)
            }
        }
    }

    @Composable
    fun PhotoItem(photo: Photo) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .clickable {
                    // Handle photo item click here
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                // Photo Image
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(photo.img_src.toString())
                        .crossfade(true)
                        .build(),
                    contentDescription = photo.camera.full_name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )

                // Photo Details
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = photo.camera.full_name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Earth Date: ${photo.earth_date}",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Rover: ${photo.rover.name}",
                    style = MaterialTheme.typography.bodySmall
                )

                // Other details like camera details, sol, etc. can be added here
            }
        }
    }

}
