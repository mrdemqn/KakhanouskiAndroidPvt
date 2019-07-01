package by.itacademy.pvt.dz6

import java.util.UUID

class Student(
    var name: String,
    var age: Int,
    var imageUrl: String
) {
    val id = UUID.randomUUID().toString()
}