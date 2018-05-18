package streams.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Lambdas {


    public static void main(String[] args) {


        Predicate<String> stringLength = s -> {
            return s.length() > 3;
        };
        System.out.println("String is longer than 3: " + stringLength.test("abcd"));
        List<String> navne = new ArrayList<>();
        navne.add("anna");
        navne.add("jens");
        navne.add("arild");
        navne.add("poul");
        navne.add("ib");
        navne.add("bo");
        navne.stream()
                .filter(stringLength)// returnerer en ny stream
                .forEach(System.out::println);





        //UnaryOperator
        UnaryOperator<String> unaryOperator = (string) -> {
            StringBuilder stringBuilder = new StringBuilder(string);
            return stringBuilder.reverse().toString();
        };
        System.out.println(unaryOperator.apply("hello"));

        // Predicate
        Predicate<Student> predicate = student -> student.isActive;





        Function<Integer, String> convertToString = (number) -> Integer.toString(number);
        System.out.println("Convert " + 5 + " to String: " + convertToString.apply(5));

        BinaryOperator<Double> doubleAdder = (denenedouble, denandendouble) -> denenedouble + denandendouble;
        System.out.println("3.0 + 5.1 = " + doubleAdder.apply(3.0, 5.1));

        UnaryOperator<Double> squared = (number) -> Math.pow(number,2);
        System.out.println("5.1 squared = " + squared.apply( 5.1));

    }

    // Example on how to create a Functional Interface in Java
    @FunctionalInterface
    interface GiveGrade{
        public boolean grade(int grade);

    }
}
