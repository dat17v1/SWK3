package streams.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class LambdaExerciseSetup {

    List<Student> students = new ArrayList<>();
    public static void main(String[] args) {
        new LambdaExerciseSetup();
    }


    public LambdaExerciseSetup(){
        students.add(new Student("Sofia", "Sofia@stud.dk", 2017, true));
        students.add(new Student("Alma", "Alma@stud.dk", 2016, true));
        students.add(new Student("Ida", "Ida@stud.dk", 2015, true));
        students.add(new Student("Freja", "Freja@stud.dk", 2017, false));
        students.add(new Student("Clara", "Clara@stud.dk", 2016, false));
        students.add(new Student("Noah", "Noah@stud.dk", 2014, true));
        students.add(new Student("Victor", "Victor@stud.dk", 2015, true));
        students.add(new Student("Oliver", "Oliver@stud.dk", 2016, true));
        students.add(new Student("Oscar", "Oscar@stud.dk", 2017, false));
        students.add(new Student("William", "William@stud.dk", 2016, true));

    }

}
