package Calculator;

public class Calculation {
    private InputOutput inputOutput = new InputOutput();
    private Operand firstOperand = new Operand();
    private Operand secondOperand = new Operand();
    private boolean isArab;
    private char mathSymbol;

    public boolean setMathSymbol(char mathSymbol) {
        char[] symbols = {'*', '/', '+', '-'};
        for (Character symbol :
                symbols) {
            if (mathSymbol == symbol) {
                this.mathSymbol = mathSymbol;
                return true;
            }
        }
        return false;
    }

    public void startCalc() {
        String[] inputResult = resOfInputInArray(inputOutput.getInput());
        checkMathOper(inputResult[1].charAt(0));
        checkOperands(inputResult[0], inputResult[2]);
        String strMainResult = "";
        int mainResult = calc();
        if (!isArab) {
            strMainResult = Operand.getRomeNum(mainResult);
        } else {
            strMainResult = String.valueOf(mainResult);
        }
        inputOutput.getOutput(strMainResult);
    }

    public String[] resOfInputInArray(String inputStr) {
        String[] splitRes = inputStr.split(" ");
        try {
            if (splitRes.length != 3) {
                throw new Exception();
            }
        } catch (Exception ex) {
            System.out.println("Not valid math expression. For example: 9 * 2.");
        }
        return splitRes;
    }

    public void checkMathOper(char mathOper) {
        try {
            if (!setMathSymbol(mathOper)) {
                throw new Exception();
            }
        } catch (Exception ex) {
            System.out.println("Invalid sign of the mathematical operation. For example: / * - +");
        }

    }

    public void checkOperands(String firstNum, String secondNum) {
        try {
            if (Operand.isNumeric(firstNum) && Operand.isNumeric(secondNum)) {
                isArab = true;
            } else if (!Operand.isNumeric(firstNum) && !Operand.isNumeric(secondNum)) {
                isArab = false;
            } else {
                throw new Exception();
            }
            if (!firstOperand.setValue(firstNum) || !secondOperand.setValue(secondNum)) {
                throw new Exception();
            }
        } catch (Exception ex) {
            System.out.println("Invalid operands of the mathematical operation. Only the similar and correct operands. " +
                    "For example: 1..10 || I..X");
        }
    }

    public int calc() {
        int mainResult = 0;
        switch (mathSymbol) {
            case '+':
                mainResult = firstOperand.getIntValue() + secondOperand.getIntValue();
                break;
            case '-':
                mainResult = firstOperand.getIntValue() - secondOperand.getIntValue();
                break;
            case '*':
                mainResult = firstOperand.getIntValue() * secondOperand.getIntValue();
                break;
            case '/':
                mainResult = firstOperand.getIntValue() / secondOperand.getIntValue();
                break;
        }
        return mainResult;
    }
}