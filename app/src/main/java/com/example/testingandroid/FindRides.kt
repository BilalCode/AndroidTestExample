package com.example.testingandroid

import kotlin.random.Random

class FindRides {

    fun getNearByRides(inMiles : Int? = null, inTime : Int? = null,bySeats : Int? = null,carType : String? = null,rides : ArrayList<Ride>): ArrayList<Ride> {
        var availableRide = rides
        availableRide = getRidesByMiles(inMiles,availableRide)
        availableRide = getRidesByTime(inTime,availableRide)
        availableRide = getRidesBySeatAvailable(bySeats,availableRide)
        availableRide = getRidesByCarType(carType,availableRide)
        return availableRide
    }

    fun getRidesByMiles(inMiles : Int? = null, rides : ArrayList<Ride>): ArrayList<Ride> {
        return if (inMiles != null) {
            ArrayList<Ride>( rides.filter {
                it.distance!! <= inMiles
            })
        } else{
            rides
        }
    }

    fun getRidesByTime(inTime : Int? = null, rides : ArrayList<Ride>): ArrayList<Ride> {
        return if (inTime != null) {
            ArrayList<Ride>(
                rides.filter {
                    it.time <= inTime
                })
        } else{
            rides
        }
    }

    fun getRidesBySeatAvailable(bySeats : Int? = null, rides : ArrayList<Ride>): ArrayList<Ride> {
        return if (bySeats != null) {
            ArrayList<Ride>(rides.filter {
                (it.availableSeats?:0) >= bySeats
            })
        } else{
            rides
        }
    }

    fun getRidesByCarType(carType : String? = null, rides : ArrayList<Ride>): ArrayList<Ride> {
        return if (carType != null) {
            ArrayList<Ride>( rides.filter {
                it.carType.toString() == carType
            })
        }else{
            rides
        }
    }

    fun fakeAvailableRide(): ArrayList<Ride> {
        val availableRide = arrayListOf<Ride>()

        val types = arrayListOf<String>()
        types.add("Luxury")
        types.add("Economy")
        types.add("Saver")
        types.add("Business")

        repeat(Random.nextInt(10,20)) {
            val ride = Ride(
                types[Random.nextInt(0,3)],
                "Car $it",
                (Random.nextInt(1,30)),
                Random.nextInt(1,30),
                "Driver " + Random.nextInt(10,99),
                Random.nextInt(1,4)
            )
            availableRide.add(ride)
        }
        return availableRide
    }

    fun findNearestRider(rides : ArrayList<Ride>): Ride? {
        return  rides.minByOrNull { (it.time)   }
    }

}