package by.itacademy.pvt.dz9

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import by.itacademy.pvt.R
import by.itacademy.pvt.dz9.entity.Coordinate
import by.itacademy.pvt.dz9.entity.CoordinateParams
import by.itacademy.pvt.dz9.entity.Poi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.io.IOException

class Dz9MapsActivity : FragmentActivity(), Dz9CarListFragment.ClickListener, CarRepositoryResult, OnMapReadyCallback{

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val carRepository: CarRepository = provideCarRepository()
    private val poiList: MutableList<Poi> = mutableListOf()

    private lateinit var manager: FragmentManager
    private lateinit var transaction: FragmentTransaction

    private lateinit var lastLocation: Location
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    private lateinit var iconCarBitmap: Bitmap
//    private var flagMapReady = false
//    private var flagListReady = false

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz9_maps)



        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        //fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val dz9Fragment = Dz9CarListFragment()
        manager = supportFragmentManager

        carRepository.getCarByCoordinate(CoordinateParams(Coordinate(2500.0, 300.2), Coordinate(3500.0, 250.0)), this)

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.dz9Container1))
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        //iconCarBitmap = AppCompatResources.getDrawable(this, R.drawable.ic_user_location)
            transaction = manager.beginTransaction()
            transaction.replace(R.id.dz9Container1, dz9Fragment)
            transaction.commit()

    }
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        //flagMapReady = true
        map.uiSettings.isZoomControlsEnabled = true
            setUpMap()
    }

    override fun onCarClick(item: Poi) {

        map.clear()
        val taxiCar = LatLng(
            item.coordinate?.latitude!!,
            item.coordinate.longitude
        )
        placeMarkerOnMap(taxiCar)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(taxiCar, 12f))
    }

    override fun onSuccess(list: List<Poi>) {
        poiList.addAll(list)

    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(this, "Error. Pleae try again", Toast.LENGTH_SHORT).show()
    }

    private fun placeMarkerOnMap(location: LatLng) {
        val markerOptions = MarkerOptions().position(location)

        markerOptions.icon(
            BitmapDescriptorFactory.fromBitmap(
                BitmapFactory.decodeResource(resources, R.mipmap.ic_user_location)
            )
        )
        val titleStr = getAddress(location)
        markerOptions.title(titleStr)
        map.addMarker(markerOptions)
    }

    private fun setUpMap() {
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

        poiList.forEach {itPoi ->
            val coords = LatLng(itPoi.coordinate!!.latitude, itPoi.coordinate.longitude)
            builder.include(coords)
            map.addMarker(MarkerOptions()
                .position(coords)
                .icon(BitmapDescriptorFactory.fromBitmap(iconCarBitmap))
                .rotation(itPoi.heading!!.toFloat()))
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
}