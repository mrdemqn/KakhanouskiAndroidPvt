package by.itacademy.pvt.dz6

class SingletonStudent private constructor(){

    private lateinit var studentsList: MutableList<Student>

    companion object Singleton {
        val instance = SingletonStudent
    }

    fun getStudents() : MutableList<Student> {
        if (studentsList.isEmpty()) {
                createStudentsList()
            }
        return studentsList
    }

    private fun createStudentsList() : MutableList<Student> {
        studentsList = mutableListOf(
            Student("Donatello", 19, "https://avatars.mds.yandex.net/get-pdb/1893235/3478a4b3-f41d-417a-bd7d-c77d0ce8837a/s1200"),
            Student("Leonardo 1", 24, "https://avatars.mds.yandex.net/get-pdb/1867782/846e6c2a-e022-40c6-a908-8621f8e796ca/s1200"),
            Student("Leonardo 2", 18, "https://avatars.mds.yandex.net/get-pdb/1732919/778c096a-1f4d-41e4-8d3f-87c4f8ac8095/s1200"),
            Student("Leonardo 3", 19, "https://avatars.mds.yandex.net/get-pdb/1938902/c9440445-f040-444a-b289-953e372de989/s1200"),
            Student("Michelangelo 1", 20, "https://avatars.mds.yandex.net/get-pdb/2080781/99ea178f-e3fe-40f7-8208-bc87568daf78/s1200"),
            Student("Michelangelo 2", 19, "https://avatars.mds.yandex.net/get-pdb/1600028/f135f52a-eb70-4ce7-b1df-169fcdf8bb5a/s1200"),
            Student("Raphael 1", 24, "https://avatars.mds.yandex.net/get-pdb/1586749/1977655b-9737-4958-8d77-751ae24a2f99/s1200"),
            Student("Raphael 2", 19, "https://avatars.mds.yandex.net/get-pdb/2006743/f6362e1e-3770-49ab-bfaf-f7341ea29c92/s1200")
        )
        return studentsList
    }

    fun addNewStudent(imageUrl: String, age: Int, name: String) {
        studentsList.add(Student(imageUrl, age, name))
    }

    fun findStudentById (id: Long, imageUrl: String, age: Int, name: String) {
        studentsList.find { it.id == id }?.apply {
            this.imageUrl
            this.age
            this.name
        }
    }

    fun deleteStudentByIdFromList (id: Long) {
        studentsList.find { it.id == id }?.apply {
            studentsList.remove(this)
        }
    }

}