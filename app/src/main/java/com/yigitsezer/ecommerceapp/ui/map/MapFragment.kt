package com.yigitsezer.ecommerceapp.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.yigitsezer.ecommerceapp.R
import com.yigitsezer.ecommerceapp.databinding.FragmentMapBinding
import com.yigitsezer.ecommerceapp.ui.product.SharedProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    //private val REQUEST_PERMISSION_CODE = 10000

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private val sharedProductViewModel: SharedProductViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = this.childFragmentManager.findFragmentById(R.id.fcv_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        sharedProductViewModel.product.observe(viewLifecycleOwner, {
            map = googleMap
            sharedProductViewModel.product.value?.latitude?.let {
                val latitude = it.toDouble()
                val longitude = sharedProductViewModel.product.value?.longitude!!.toDouble()
                val zoomLevel = 15f

                val latLng = LatLng(latitude, longitude)
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))
                map.addMarker(MarkerOptions().position(latLng).title("Ürünün Bulunduğu Mağaza"))
                //enableMyLocation()
                map.uiSettings.isZoomControlsEnabled = true
            }
        })
    }

    /*
    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            //izin alındıysa
            getLastKnownLocation()
        } else {
            //izin alınmadıysa
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_PERMISSION_CODE
            )
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation() {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                val latLong = LatLng(it.latitude, it.longitude)
                val markerOptions = MarkerOptions()
                markerOptions.position(latLong)
                markerOptions.title("Current Position")
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                map.addMarker(markerOptions)
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLong, 15f))
                map.isMyLocationEnabled = true
                map.uiSettings.isCompassEnabled
            }?: run {
                //showToast("Location is null!")
            }
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                enableMyLocation()
            }
        }
    }
     */
}