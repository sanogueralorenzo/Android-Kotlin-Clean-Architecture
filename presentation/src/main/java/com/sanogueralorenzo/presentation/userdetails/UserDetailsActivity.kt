package com.sanogueralorenzo.presentation.userdetails

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.sanogueralorenzo.presentation.R
import com.sanogueralorenzo.presentation.getAppInjector
import com.sanogueralorenzo.presentation.model.USER_ID_KEY
import com.sanogueralorenzo.presentation.model.UserItem
import com.sanogueralorenzo.presentation.observe
import com.sanogueralorenzo.presentation.withViewModel
import kotlinx.android.synthetic.main.activity_user_details.*
import javax.inject.Inject

class UserDetailsActivity : AppCompatActivity(), OnMapReadyCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        getAppInjector().inject(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json))

        withViewModel<UserDetailsViewModel>(viewModelFactory) {
            userId = intent.getStringExtra(USER_ID_KEY)
            observe(user, ::showUser)
        }
    }

    private fun showUser(user: UserItem?) {
        userName.text = getString(R.string.user_name, user!!.name)
        userUsername.text = getString(R.string.user_username, user.username)
        userEmail.text = getString(R.string.user_email, user.email)
        userPhone.text = getString(R.string.user_phone, user.phone)
        val address = user.addressItem
        userAddress.text = getString(R.string.user_address, address.street, address.city, address.zipcode)
        userWebsite.text = getString(R.string.user_website, user.website)
        userCompany.text = getString(R.string.user_company, user.companyItem.name)

        val position = user.addressItem.latLng
        mMap.addMarker(MarkerOptions().position(position).title("@${user.username}"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position))
    }
}
