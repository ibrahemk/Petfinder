package com.example.petfinder.Room

import androidx.room.*
import com.example.petfinder.Bean.Pet


@Dao
interface Pets_dao {
    @get:Query("SELECT * FROM Petlist")
    val getallpet: List<Pet>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pets: List<Pet?>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPet(vararg pet: Pet)

    @Delete
    fun deletePet(vararg pet: Pet)

    @Query("DELETE FROM Petlist")
    fun deletePets()
}