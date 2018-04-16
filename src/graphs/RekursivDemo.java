package graphs;

public class RekursivDemo {

    public static void main(String[] args) {
        // 4 * 3* 2 * 1 = ?
        System.out.println(factorial(4));
    }

    public static int factorial(int number){
        if(number == 1){ // base case:
            return 1;
        }else { // rekursiv case:
            return number * factorial(number - 1);
        }
    }
}
