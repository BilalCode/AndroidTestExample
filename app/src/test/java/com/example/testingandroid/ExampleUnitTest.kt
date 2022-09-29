package com.example.testingandroid

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun getRidesByMiles() {
        val findRides = FindRides()
        val allRides = findRides.fakeAvailableRide()
        val filterByMiles = findRides.getRidesByMiles(20,allRides)
        filterByMiles.forEach{ride->
            assertTrue((ride.distance!! <= 20))
        }

    }

    @Test
    fun getRidesByTime() {
        val findRides = FindRides()
        val allRides = findRides.fakeAvailableRide()
        val filterByTime = findRides.getRidesByTime(15,allRides)
        filterByTime.forEach{ride->
            assertTrue((ride.time <= 15))
        }
    }

    @Test
    fun getRidesBySeatAvailable() {
        val findRides = FindRides()
        val allRides = findRides.fakeAvailableRide()
        val filterByTime = findRides.getRidesBySeatAvailable(3,allRides)
        filterByTime.forEach{ride->
            assertTrue((ride.availableSeats!! <= 3))
        }
    }

    //Integration test
    @Test
    fun getRidesByCarType() {
        val findRides = FindRides()
        val allRides = findRides.fakeAvailableRide()
        val filterByTime = findRides.getRidesByCarType("Saver",allRides)
        filterByTime.forEach{ride->
            assertTrue((ride.carType == "Saver"))
        }
    }

    @Test
    fun findNearByRides(){
        val findRides = FindRides()
        var allRides = findRides.fakeAvailableRide()
        allRides = findRides.getRidesByMiles(15,allRides)
        allRides =  findRides.getRidesByTime(10,allRides)
        allRides =  findRides.getRidesBySeatAvailable(2,allRides)
        allRides =  findRides.getRidesByCarType("Saver",allRides)
        allRides.forEach{ride->
            assertTrue((ride.distance!! <= 15))
            assertTrue((ride.time <= 10))
            assertTrue((ride.availableSeats!! >= 2))
            assertTrue((ride.carType == "Saver"))
        }
    }


}