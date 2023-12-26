package com.example.petfinder.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.petfinder.Bean.Pet



@Database(entities = [Pet::class], version = 13)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CityDao(): Pets_dao?

    companion object {
        private var mInstance: AppDatabase? = null
        fun getAppDatabase(context: Context): AppDatabase? {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "Pets_data"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return mInstance
        }

        fun destroyInstance() {
            if (mInstance != null) {
                mInstance!!.close()
            }
            mInstance = null
        }
    }
}