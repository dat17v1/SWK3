package streams.exercises;

import java.util.stream.Collectors;

public class Student implements Comparable<Student> {
    String name;
    String email;
    int yearStarted;
    boolean isActive;
    boolean hasHandedInAssignments = false;
    public Student(String name, String email, int yearStarted, boolean isActive) {
        this.name = name;
        this.email = email;
        this.yearStarted = yearStarted;
        this.isActive = isActive;
    }

    @Override
    public int compareTo(Student other) {  // -1 , 0 , 1
        if(other.name.compareTo(name) == 0) {
            return 0;
        }
        else {
            return other.name.compareTo(name);
        }
    }

    public String toString(){
        return name;
    }
}
