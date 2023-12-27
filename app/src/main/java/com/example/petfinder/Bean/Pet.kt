package com.example.petfinder.Bean

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Petlist")
class Pet: Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
     var id:Int=0
    @ColumnInfo(name = "Name")
     var Name:String=""
    @ColumnInfo(name = "Gender")
     var Gender:String=""
    @ColumnInfo(name = "image")
     var image:String=""
    @ColumnInfo(name = "smallimage")
     var smallimage:String=""
    @ColumnInfo(name = "Color")
     var Color:String=""
    @ColumnInfo(name = "address")
     var address:String=""
    @ColumnInfo(name = "type")
     var type:String=""
    @ColumnInfo(name = "Size")
     var Size:String=""
    @ColumnInfo(name = "url")
     var url:String=""
}