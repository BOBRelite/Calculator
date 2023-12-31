import java.util.Scanner;


    public class Calculator {

        public static void main(String[] args) {

            int[] arabicNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            String[] romanNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                    "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                    "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                    "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                    "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                    "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                    "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

            Scanner scanner = new Scanner(System.in);

            System.out.println("Введите операцию (пример: 1 + 1 или I + I):");
            String input = scanner.nextLine();

            try {
                String res = calc(input, arabicNumbers, romanNumbers);
                if (res.equals("0")) {
                    throw new Exception("Error");
                }
                System.out.println("Ответ: " + res);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            scanner.close();
        }

        public static String calc(String input, int[] arabicNumbers, String[] romanNumbers) throws Exception {

            String[] tokens = input.split(" ");
            if (tokens.length != 3) {
                throw new Exception("Некорректное выражение");
            }

            boolean isArabic = isArabicNumber(tokens[0]);
            boolean isRoman = isRomanNumber(tokens[0]);

            if (!isArabic && !isRoman) {
                throw new Exception("Неверные числа");
            }
            if (isArabic && (Integer.parseInt(tokens[0]) < 1 || Integer.parseInt(tokens[0]) > 10 ||
                    Integer.parseInt(tokens[2]) < 1 || Integer.parseInt(tokens[2]) > 10)) {
                throw new Exception("Неверные числа");
            }
            if (isRoman && (!isValidRomanNumber(tokens[0]) || !isValidRomanNumber(tokens[2]))) {
                throw new Exception("Неверные числа");
            }

            int num1, num2;
            if (isRoman) {
                num1 = romanToArabic(tokens[0], romanNumbers);
                num2 = romanToArabic(tokens[2], romanNumbers);
            } else {
                num1 = Integer.parseInt(tokens[0]);
                num2 = Integer.parseInt(tokens[2]);
            }


            int result;
            switch (tokens[1]) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
                default:
                    throw new Exception("Некорректная операция");
            }

            boolean isResultRoman = false;
            if (isRoman) {
                if (result > 0) {
                    isResultRoman = true;
                }
            }
            if (isRoman) {
                if (result <= 0) {
                    throw new Exception("Error");
                }
            }
            String finalResult;
            if (isResultRoman) {
                finalResult = arabicToRoman(result, romanNumbers);
            } else {
                finalResult = String.valueOf(result);
            }

            return finalResult;
        }

        public static boolean isArabicNumber(String str) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        public static boolean isRomanNumber(String str) {
            String[] romanNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
            for (String romanNumber : romanNumbers) {
                if (str.equals(romanNumber)) {
                    return true;
                }
            }
            return false;
        }

        public static boolean isValidRomanNumber(String str) {
            String romanNumbersPattern = "^(?:I{1,3}|IV|VI{0,3}|IX|X)$";
            return str.matches(romanNumbersPattern);
        }

        public static int romanToArabic(String romanNumber, String[] romanNumbers) {
            int arabicNumber = 0;
            for (int i = 0; i < romanNumbers.length; i++) {
                if (romanNumber.equals(romanNumbers[i])) {
                    arabicNumber = i + 1;
                    break;
                }
            }
            return arabicNumber;
        }

        public static String arabicToRoman(int arabicNumber, String[] romanNumbers) {
            return romanNumbers[arabicNumber - 1];
        }
    }