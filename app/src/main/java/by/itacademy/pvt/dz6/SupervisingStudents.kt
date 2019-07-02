package by.itacademy.pvt.dz6

import java.util.*

object SupervisingStudents {

    private var studentsList: MutableList<Student> = mutableListOf()

    fun getStudents(): MutableList<Student> {
        if (studentsList.isEmpty()) {
            createStudentsList()
        }
        return studentsList
    }

    private fun createStudentsList(): MutableList<Student> {
        studentsList = mutableListOf(
            Student(
                UUID.randomUUID().toString(),
                "Donatello",
                19,
                "https://avatars.mds.yandex.net/get-pdb/1893235/3478a4b3-f41d-417a-bd7d-c77d0ce8837a/s1200"),
            Student(
                UUID.randomUUID().toString(),
                "Leonardo 1",
                24,
                "https://avatars.mds.yandex.net/get-pdb/1867782/846e6c2a-e022-40c6-a908-8621f8e796ca/s1200"),
            Student(
                UUID.randomUUID().toString(),
                "Leonardo 2",
                18,
                "https://avatars.mds.yandex.net/get-pdb/1732919/778c096a-1f4d-41e4-8d3f-87c4f8ac8095/s1200"),
            Student(
                UUID.randomUUID().toString(),
                "Leonardo 3",
                19,
                "https://avatars.mds.yandex.net/get-pdb/1938902/c9440445-f040-444a-b289-953e372de989/s1200"),
            Student(
                UUID.randomUUID().toString(),
                "Michelangelo 1",
                20,
                "https://avatars.mds.yandex.net/get-pdb/2080781/99ea178f-e3fe-40f7-8208-bc87568daf78/s1200"),
            Student(
                UUID.randomUUID().toString(),
                "Michelangelo 2",
                19,
                "https://avatars.mds.yandex.net/get-pdb/1600028/f135f52a-eb70-4ce7-b1df-169fcdf8bb5a/s1200"),
            Student(
                UUID.randomUUID().toString(),
                "Raphael 1",
                24,
                "https://avatars.mds.yandex.net/get-pdb/1586749/1977655b-9737-4958-8d77-751ae24a2f99/s1200"),
            Student(
                UUID.randomUUID().toString(),
                "Raphael 2",
                19,
                "https://avatars.mds.yandex.net/get-pdb/2006743/f6362e1e-3770-49ab-bfaf-f7341ea29c92/s1200")
        )
        return studentsList
    }

    fun addNewStudent(student: Student) {
        studentsList.add(student)
    }

    fun upgradeStudentById(student: Student) {
        val indexNumber = studentsList.indexOfFirst { it.id == student.id }
        if (indexNumber != -1) {
            studentsList[indexNumber] = student
        }
    }

    fun deleteStudentByIdFromList(id: String) {
        studentsList.find { it.id == id }
            ?.apply {
                studentsList
                    .remove(this)
            }
    }

    fun searchByName(query: String): List<Student> {
        return studentsList.filter { it.name.contains(query, true) }
    }

    fun findStudentById(id: String): Student? {
        return studentsList.find { it.id == id }
    }
}