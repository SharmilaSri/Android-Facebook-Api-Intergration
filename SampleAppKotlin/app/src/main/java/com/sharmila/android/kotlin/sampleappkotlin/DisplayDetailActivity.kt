package com.sharmila.android.kotlin.sampleappkotlin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.facebook.login.widget.LoginButton
import com.sharmila.android.kotlin.sampleappkotlin.model.Hotel

class DisplayDetailActivity : AppCompatActivity() {

    val selectedHotel = Hotel.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_detail_display)

        val titleTxtView: TextView =findViewById(R.id.txtview_hotel_title)
        val descriptionTxtView: TextView =findViewById(R.id.txtview_hotel_description)
        val hotelImage:ImageView=findViewById(R.id.image_view_hotel_image)


        titleTxtView.text=selectedHotel?.title;
        descriptionTxtView.text=selectedHotel?.description
        Glide.with(this)
            .load(selectedHotel?.images!!.small)
            .into(hotelImage)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_hotel_detail_display, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.show_map) {
            // Create a Uri from an intent string. Use the result to create an Intent.
            val gmmIntentUri = Uri.parse("google.streetview:cbll=" + selectedHotel!!.latitute + "," + selectedHotel!!.longtitude + "")

            // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            // Make the Intent explicit by setting the Google Maps package
            mapIntent.setPackage("com.google.android.apps.maps")

            // Attempt to start an activity that can handle the Intent
            startActivity(mapIntent)
        }
        return true
    }
}