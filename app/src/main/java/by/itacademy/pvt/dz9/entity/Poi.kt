package by.itacademy.pvt.dz9.entity

import by.itacademy.pvt.dz9.entity.Coordinate
import by.itacademy.pvt.dz9.entity.FleetType
import com.google.gson.annotations.SerializedName

data class Poi(

    @SerializedName("id")
    val id: String,

    @SerializedName("coordinate")
    val coordinate: Coordinate?,

    @SerializedName("fleetType") //Если прислали переменную fleet_Type с подчёркиванием и мы хотим использовать другую то по таким именем ищи переменную
    val fleetType: FleetType?,

    @SerializedName("heading") //Указывать переменную которая в JSON пришла
    val heading: Double?
)