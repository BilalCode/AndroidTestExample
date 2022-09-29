package com.example.testingandroid

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var MILES : Int?= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val findRides = FindRides()
        val availableRide = findRides.fakeAvailableRide()
        val nearByRides = findRides.getNearByRides(5,20,1,"Saver",availableRide)
        val nearestRide = findRides.findNearestRider(nearByRides)
        val rideSummary = findViewById<TextView>(R.id.rideSummary)
        val tvTimeToReach = findViewById<TextView>(R.id.tvTimeToReach)
        val tvDistance = findViewById<TextView>(R.id.tvDistance)
        val tvAvailableSeats = findViewById<TextView>(R.id.tvAvailableSeats)

        if(nearestRide!=null) {
            rideSummary.text =
                "You rider ${nearestRide?.riderName}. is on the way on your ${nearestRide?.carType} car ${nearestRide.carName}"

            tvTimeToReach.text = "${nearestRide.time} min(s) to reach"

            tvDistance.text = "${nearestRide.distance} miles away"
            tvAvailableSeats.text = "${nearestRide.availableSeats} seat(s) are available"
        }else {
            rideSummary.text = "No rider found"
            tvTimeToReach.visibility = View.GONE
            tvDistance.visibility = View.GONE
            tvAvailableSeats.visibility = View.GONE
        }

        Log.d("Ride near by","Total ${nearByRides.size}")

    }




}