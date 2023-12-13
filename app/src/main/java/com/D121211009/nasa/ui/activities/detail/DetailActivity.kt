package com.D121211009.nasa.ui.activities.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import coil.request.ImageRequest
import com.D121211009.nasa.R
import com.D121211009.nasa.data.models.Photo
import com.D121211009.nasa.ui.theme.NASATheme

class DetailActivity : ComponentActivity() {
    private var selectedPhoto: Photo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedPhoto = intent.getParcelableExtra("PHOTO")
        setContent {
            NASATheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PhotoDetailScreen()
                }
            }
        }
    }

    @Composable
    fun PhotoDetailScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Photo Image
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(selectedPhoto?.img_src)
                    .crossfade(true)
                    .build(),
                contentDescription = selectedPhoto?.camera?.full_name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            // Photo Details
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = selectedPhoto?.camera?.full_name ?: "",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Earth Date: ${selectedPhoto?.earth_date}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Rover: ${selectedPhoto?.rover?.name}",
                style = MaterialTheme.typography.bodyMedium
            )

            // Other details like camera details, sol, etc. can be added here
        }
    }
}