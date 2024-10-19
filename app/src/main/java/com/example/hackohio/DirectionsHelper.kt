package com.example.hackohio

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

suspend fun getDirections(startLat: Double, startLng: Double, endLat: Double, endLng: Double, apiKey: String): List<LatLng> {
    val client = OkHttpClient()

    val url = "https://maps.googleapis.com/maps/api/directions/json?origin=$startLat,$startLng&destination=$endLat,$endLng&key=$apiKey"
    val request = Request.Builder().url(url).build()

    return withContext(Dispatchers.IO) {
        val response = client.newCall(request).execute()
        val jsonString = response.body?.string() ?: return@withContext emptyList()
        val json = JSONObject(jsonString)
        val routes = json.getJSONArray("routes")

        if (routes.length() == 0) return@withContext emptyList()

        val overviewPolyline = routes.getJSONObject(0).getJSONObject("overview_polyline").getString("points")
        return@withContext decodePolyline(overviewPolyline)
    }
}

// Function to decode the polyline points into LatLng coordinates
fun decodePolyline(encoded: String): List<LatLng> {
    val poly = ArrayList<LatLng>()
    var index = 0
    val len = encoded.length
    var lat = 0
    var lng = 0

    while (index < len) {
        var b: Int
        var shift = 0
        var result = 0
        do {
            b = encoded[index++].code - 63
            result = result or (b and 0x1f shl shift)
            shift += 5
        } while (b >= 0x20)
        val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
        lat += dlat

        shift = 0
        result = 0
        do {
            b = encoded[index++].code - 63
            result = result or (b and 0x1f shl shift)
            shift += 5
        } while (b >= 0x20)
        val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
        lng += dlng

        val latLng = LatLng(lat / 1E5, lng / 1E5)
        poly.add(latLng)
    }
    return poly
}
