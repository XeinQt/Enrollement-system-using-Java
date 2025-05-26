import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentService {
    private List<Student> students;
    private int nextId;

    public StudentService() {
        this.students = new ArrayList<>();
        this.nextId = 1;
    }

    // Create
    public Student createStudent(String name, int age) {
        Student student = new Student(nextId++, name, age);
        students.add(student);
        return student;
    }

    // Read
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Optional<Student> getStudentById(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst();
    }

    // Update
    public boolean updateStudent(int id, String name, int age) {
        Optional<Student> studentOpt = getStudentById(id);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.setName(name);
            student.setAge(age);
            return true;
        }
        return false;
    }

    // Delete
    public boolean deleteStudent(int id) {
        return students.removeIf(student -> student.getId() == id);
    }
} 