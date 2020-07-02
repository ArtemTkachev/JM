package Calculator;

import java.util.Scanner;

public class InputOutput {
    private Scanner scanner = new Scanner(System.in);

    public String getInput() {
        System.out.println("Input: ");
        return scanner.nextLine();
    }

    public void getOutput(String outValue) {
        System.out.println("Output: \n" + outValue);
    }

}
