package Calculator;

public class Operand {
    private String value;
    private static String[] ROMENUMS = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    public int getIntValue() {
        if (isNumeric(value)) {
            return Integer.parseInt(value);
        } else {
            return getArabNum(value);
        }
    }

    public boolean setValue(String value) {
        boolean isEq = false;
        if (isNumeric(value)) {
            if (Integer.parseInt(value) > 10 || Integer.parseInt(value) < 1) {
                return false;
            }
        } else {
            for (String romeNum :
                    ROMENUMS) {
                if (romeNum.equals(value)) {
                    isEq = true;
                    break;
                }
            }
            if (!isEq) {
                return false;
            }
        }
        this.value = value;
        return true;
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static int getArabNum(String romeNum) {
        for (int i = 0; i < ROMENUMS.length; i++) {
            if (ROMENUMS[i].equals(romeNum)) {
                return i;
            }
        }
        return -1;
    }

    public static String getRomeNum(int arabNum) {
        char[] strArabNum = String.valueOf(arabNum).toCharArray();
        String resultNum = "";
        int index = 0;
        int len = strArabNum.length;
        int curNum = 0;
        if (strArabNum[0] == '-') {
            resultNum += "-";
            index = 1;
            len -= 1;
        }
        if (len == 1) {
            resultNum += getRomanItoX(strArabNum, index);
        }
        if (len == 3) {
            resultNum += "C";
        }
        if (len == 2) {
            curNum = Character.getNumericValue(strArabNum[index]);
            if (curNum <= 3) {
                resultNum = getRomanX(resultNum, curNum);
            } else if (curNum == 4) {
                resultNum += "XL";
            } else if (curNum >= 4 && curNum <= 8) {
                resultNum += "L";
                resultNum = getRomanX(resultNum, curNum - 5);
            } else if (curNum == 9) {
                resultNum += "XC";
            }
            if (Character.getNumericValue(strArabNum[index + 1]) != 0) {
                resultNum += getRomanItoX(strArabNum, index + 1);
            }
        }
        return resultNum;
    }

    public static String getRomanX(String resultNum, int quantity) {
        for (int y = 0; y < quantity; y++) {
            resultNum += "X";
        }
        return resultNum;
    }

    public static String getRomanItoX(char[] strArabNum, int index) {
        return ROMENUMS[Character.getNumericValue(strArabNum[index])];
    }
}
