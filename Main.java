public class Main {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();

        // Create students
        System.out.println("Creating students...");
        Student student1 = studentService.createStudent("John Doe", 20);
        Student student2 = studentService.createStudent("Jane Smith", 22);
        System.out.println("Created students: ");
        studentService.getAllStudents().forEach(System.out::println);

        // Read student
        System.out.println("\nReading student with ID 1:");
        studentService.getStudentById(1).ifPresent(System.out::println);

        // Update student
        System.out.println("\nUpdating student with ID 1...");
        boolean updated = studentService.updateStudent(1, "John Updated", 21);
        System.out.println("Update successful: " + updated);
        System.out.println("Updated student:");
        studentService.getStudentById(1).ifPresent(System.out::println);

        // Delete student
        System.out.println("\nDeleting student with ID 2...");
        boolean deleted = studentService.deleteStudent(2);
        System.out.println("Delete successful: " + deleted);
        
        // Show remaining students
        System.out.println("\nRemaining students:");
        studentService.getAllStudents().forEach(System.out::println);
    }
} 