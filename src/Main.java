import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static Search dfs;
    private static Scanner input;
    private static String[] orderStr;

    public static void main(String[] args) {
         //String[] orderStr = {"A", "B", "C", "D", "E", "F", "G", "H"}; // hardcode order
        // String[] orderStr = {"F", "H", "C", "D", "G", "E", "A", "B"}; // hardcode order

        // BONUS: with any order
        System.out.println("Please enter the variables in order: ");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        orderStr = input.nextLine().split(",");

        // initialize new dfs search and print the result
        dfs = new Search(Arrays.asList(orderStr));
        System.out.println("Number of failing branches is " + dfs.getFailingBranches());
        System.out.println("Solutions: ");
        printSolutions(dfs.getSolutions());
    }

    // Print the solution paths in DFS with Prunning's solution list
    private static void printSolutions(List<String> solutions) {
        int length = solutions.size();
        for (int i = 0; i < length; i++) {
            System.out.println("\t" + solutions.get(i));
        }
    }

}
