package com.example.hackohio
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hackohio.ui.theme.HackOhioTheme
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import androidx.compose.runtime.*
import kotlinx.coroutines.*
import com.google.android.gms.maps.model.Polyline
import android.util.Log
import androidx.compose.ui.graphics.Color


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HackOhioTheme {
                val cameraPositionState = rememberCameraPositionState {
                    position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(
                        LatLng(40.0, -83.0), // Example: Latitude and Longitude for Ohio
                        10f
                    )
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var polylinePoints by remember { mutableStateOf<List<LatLng>>(emptyList()) }

                    //get directions
                    LaunchedEffect(Unit) {
                        polylinePoints = getDirections(
                            startLat = 40.0, startLng = -83.0, //example start
                            endLat = 40.1, endLng = -83.1, //example end
                            apiKey = "AIzaSyAqphgsGDdsPjz9_4DTAUWoWtg8irMsqRo" //API key

                        )
                        Log.d("PolylinePoints", polylinePoints.toString())
                    }

                    MapScreen(
                        cameraPositionState = cameraPositionState,
                        polylinePoints = polylinePoints,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MapScreen(cameraPositionState: CameraPositionState,
              polylinePoints: List<LatLng>,
              modifier: Modifier = Modifier) {
    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        if (polylinePoints.isNotEmpty()) {
            com.google.maps.android.compose.Polyline(
                points = polylinePoints,
                color = Color.Blue,
                width = 5f
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    HackOhioTheme {
        MapScreen(rememberCameraPositionState {
            position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(
                LatLng(40.0, -83.0), 10f)
        },
            polylinePoints = listOf(
                LatLng(40.0, -83.0), //example point
                LatLng(40.1, -83.1) //example point
            )
        )
    }
}
