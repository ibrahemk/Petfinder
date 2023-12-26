package com.example.petfinder.Room

import androidx.room.*
import com.example.petfinder.Bean.Pet


@Dao
interface Pets_dao {
    @get:Query("SELECT * FROM Petlist")
    val getallcity: List<Pet>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pets: List<Pet?>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(vararg pet: Pet)

    @Delete
    fun deleteCity(vararg pet: Pet)

    @Query("DELETE FROM Petlist")
    fun deletecities()
}