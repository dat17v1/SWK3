package network.roede;

public class AlternativRoedOpgave3 {


    public static void main(String[] args) {
        String[] row1 = new String[]{"x","x","o"};
        String[] row2 = new String[]{"o","x","x"};
        String[] row3 = new String[]{"o","x","o"};

        for(int i=0; i<row1.length; i++){
            System.out.print(row1[i] + " ");
        }
        System.out.println();
        for(int i=0; i<row2.length; i++){
            System.out.print(row2[i] + " ");
        }
        System.out.println();
        for(int i=0; i<row3.length; i++){
            System.out.print(row3[i] + " ");
        }
    }
}
