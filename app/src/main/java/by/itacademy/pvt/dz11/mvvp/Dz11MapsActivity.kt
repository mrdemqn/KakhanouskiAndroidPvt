package by.itacademy.pvt.dz11.mvvm

import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import by.itacademy.pvt.R
import by.itacademy.pvt.dz9.entity.Poi
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.io.IOException

class Dz11MapsActivity : FragmentActivity(), OnMapReadyCallback {

    private lateinit var viewModel: ViewModel
    private lateinit var map: GoogleMap
    private lateinit var mapView: MapView

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private val observerExtremeItem = Observer<Poi> { exList ->
        if (exList != null) {
            onCarClick(exList)
        }
    }

    private val obsrverState = Observer<CarRepositoryState> { list ->
        when (list) {
            is CarRepositoryState.SuccessProgress -> {
                onSuccess(list.carList)
            }
            is CarRepositoryState.ProgressFailed -> {
                onError(list.throwable)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz9_maps)

        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)

        if (savedInstanceState == null) {
            val dz9Fragment = Dz11CarListFragment()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.dz9Container1, dz9Fragment)
            transaction.commit()
        }

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.dz9Container1))

        mapView = findViewById(R.id.map)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

    }

    private fun viewModelObserve() {
        viewModel.state.observeForever(obsrverState)
        viewModel.extremeSelectedPoi.observeForever(observerExtremeItem)
    }

    override fun onMapReady(googleMap: GoogleMap) {

        map = googleMap
        map.mapType = GoogleMap.MAP_TYPE_HYBRID
        map.uiSettings.isZoomControlsEnabled = true
        viewModelObserve()
    }

    private fun onCarClick(item: Poi) {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        map.clear()
            val taxiCar = LatLng(
                item.coordinate?.latitude!!,
                item.coordinate.longitude
            )
            placeMarkerOnMap(taxiCar)
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(taxiCar, 12f))
    }

    private fun onSuccess(poiList: List<Poi>) {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
        map.isMyLocationEnabled = true

        val builder = LatLngBounds.builder()
        poiList.forEach {
            val coord = LatLng(it.coordinate?.latitude!!, it.coordinate.longitude)
            map.addMarker(
                MarkerOptions().position(coord)
                    .rotation(it.heading!!.toFloat())
            )
            builder.include(coord)
        }

        val bounds = builder.build()
        map.moveCamera(
            CameraUpdateFactory.newLatLngBounds(
                bounds,
                resources.displayMetrics.widthPixels,
                resources.displayMetrics.heightPixels,
                resources.displayMetrics.widthPixels / 5
            )
        )
    }

    private fun onError(throwable: Throwable) {
        Toast.makeText(this, "Error. Pleae try again", Toast.LENGTH_SHORT).show()
    }

    private fun placeMarkerOnMap(location: LatLng) {
        val markerOptions = MarkerOptions().position(location)
        markerOptions.icon(
            BitmapDescriptorFactory.fromBitmap(
                BitmapFactory.decodeResource(resources, R.mipmap.ic_taxi_air)
            )
        )
        val titleStr = getAddress(location)
        markerOptions.title(titleStr)
        map.addMarker(markerOptions)
    }

    private fun getAddress(latLng: LatLng): String {
        val geocoder = Geocoder(this)
        val addressList: List<Address>?
        val address: Address?
        var addressText = ""

        try {
            addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            if (null != addressList && addressList.isNotEmpty()) {
                address = addressList[0]
                for (i in 0 until address.maxAddressLineIndex) {
                    addressText += if (i == 0) address.getAddressLine(i) else "\n" + address.getAddressLine(i)
                }
            }
        } catch (e: IOException) {
            Log.e("MapsActivity", e.localizedMessage)
        }

        return addressText
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
        viewModel.state.removeObserver(obsrverState)
        viewModel.extremeSelectedPoi.removeObserver(observerExtremeItem)
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
