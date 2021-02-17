package com.sharmila.android.kotlin.sampleappkotlin

import HotelListAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.sharmila.android.kotlin.sampleappkotlin.adapaters.HotelListAdapterCallback
import com.sharmila.android.kotlin.sampleappkotlin.model.HotelList
import com.sharmila.android.kotlin.sampleappkotlin.viewmodel.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null
    lateinit var model: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_list)

        //view model
        model= ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        setUpFacebook()
    }

    private fun setUpFacebook(){

        val facebookLoginButton:LoginButton=findViewById(R.id.login_button)
        // Callback registration
        callbackManager = CallbackManager.Factory.create()
        facebookLoginButton.setReadPermissions(listOf("public_profile", "email"))
        facebookLoginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                facebookLoginSuccess()
            }

            override fun onCancel() {
                Toast.makeText(this@MainActivity, "Login Cancelled", Toast.LENGTH_LONG).show()
            }

            override fun onError(exception: FacebookException) {
                Toast.makeText(this@MainActivity, exception.message, Toast.LENGTH_LONG).show()
            }
        })


    }

    private fun facebookLoginSuccess() {
        //set up list
        var hotels= model.getHotels()
        hotels?.observe(this@MainActivity, Observer {
            setUpList(hotels)
        })

        //setUpEmailName(Profile.getCurrentProfile().firstName, Profile.getCurrentProfile().lastName)
    }

    private fun setUpEmailName(firstname:String,lastName:String) {
        val nameTextView: TextView = findViewById(R.id.txtview_name)
        val emailTextView: TextView = findViewById(R.id.txtview_email)
        nameTextView.text=firstname
        emailTextView.text=lastName

    }

    private fun setUpList(hotels:LiveData<HotelList>){
        val recyclerView: RecyclerView = findViewById(R.id.recycle_view_hotel_list)
        var adapter:HotelListAdapter = HotelListAdapter(applicationContext,hotels.value?.hotels!!)
        adapter!!.setHotelSelectedCallBack(object : HotelListAdapterCallback {
            override fun callback() {
                startActivity(Intent(applicationContext, DisplayDetailActivity::class.java))

            }
        })
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }


}