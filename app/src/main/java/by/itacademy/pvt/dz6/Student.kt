package by.itacademy.pvt.dz6

class Student(
    val name: String,
    var age: Int,
    var imageUrl: String
) {
    val id = System.currentTimeMillis()
}