import java.util.Arrays;

public class Task2 {
    public static void main(String[] args) {
        System.out.println("Символы, которые есть в первой строке, но нет во второй: " +  findduplicateChars("HI", "hi"));
        System.out.println("Количество чисел, кратных 3: " + countdividedByThree(new int[]{3, 12, 7, 81, 52}));
        System.out.println(getInitials("Максимова Мария Алексеевна"));
        System.out.println(getInitials("Малицкий Алексей Семёнович"));
        System.out.println("Нормализованный массив: " + Arrays.toString(normalizator(new double[]{3.5, 7.0, 1.5, 9.0, 5.5})));
        System.out.println("Нормализованный массив: " + Arrays.toString(normalizator(new double[]{10.0, 10.0, 10.0, 10.0})));
        System.out.println("Сжатый массив: " + Arrays.toString(compressedNums(new double[]{1.6, 0, 212.3, 34.8, 0, 27.5})));
        System.out.println("Сжатый массив: " + Arrays.toString(compressedNums(new double[]{1, 27, 34, 212})));
        System.out.println(camelToSnake("HelloWorld"));
        System.out.println("Второй по величине элемент: " + secondBiggest(new int[]{3, 5, 8, 1, 2, 4}));
        System.out.println(localReverse("baobab", 'b'));
        System.out.println(localReverse("Hello, I’m under the water, please help me", 'e'));
        System.out.println(countEqualPairs(1, 1, 2));
        System.out.println(countEqualPairs(8, 1, 8));
        System.out.println(isAnagram("Eleven plus two?", "Twelve plus one!"));
        System.out.println(isAnagram("hello", "world"));
    }


//*1.	Создайте метод, который принимает 2 строки и возвращает строку,
// состоящую из символов, входящих в 1 строку, но не входящих во 2.

    public static String findduplicateChars(String str1, String str2) {
        StringBuilder result = new StringBuilder();
        for (char c : str1.toCharArray()) {
            if (!str2.contains(String.valueOf(c))) {
                result.append(c);
            }
        }
        return result.toString();
    }

//*2.	Создайте метод, который принимает целочисленный массив и возвращает количество нечетных чисел, кратных 3.

    public static int countdividedByThree(int[] arr) {
        int count = 0; // Объявляем count внутри метода
        for (int num : arr) {
            if (num % 3 == 0) { // Проверка на кратность 3
                count++;
            }
        }
        return count;
    }

//3.	Создайте метод, который принимает строку с ФИО и преобразует в библиографическое форматирование.

    public static String getInitials(String fullName) {
        String[] parts = fullName.split(" ");
        if(parts.length>=3) {
            return  parts[0] + " "+
                    parts[1].substring(0, 1).toUpperCase() +"." +
                    parts[2].substring(0, 1).toUpperCase() +".";

        } else {
            return fullName;
        }
    }

/*4.	Создайте функцию, которая принимает массив вещественных чисел и возвращает новый массив,
 в котором все элементы нормализованы. Нормализация массива — это процесс преобразования всех элементов
таким образом, чтобы их значения находились в диапазоне от 0 до 1, причём минимальное значение становится 0,
а максимальное — 1. Для нормализации используйте формулу: x’ = (x - min) / (max - min), где x – исходное значение элемента,
min – минимальное значение в массиве, max – максимальное значение в массиве.*/

    public static double[] normalizator(double[] arr) {
        if (arr.length == 0) {
            return new double[0];
        }

        double min = arr[0];
        double max = arr[0];

        for (double num : arr) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }

        double[] normalizedArray = new double[arr.length];
        if (max - min == 0) { // Check for equal elements
            Arrays.fill(normalizedArray, 0); // Set all to 0
        } else {
            for (int i = 0; i < arr.length; i++) {
                normalizedArray[i] = (arr[i] - min) / (max - min);
            }
        }
        return normalizedArray;
    }
/*5.	Создайте метод, который берет массив вещественных чисел и возвращает «сжатую» версию массива,
чьи элементы упорядочены по возрастанию и не содержат нулей.*/

    public static double[] compressedNums(double[] arr) {
        if (arr.length == 0) {
            return new double[0]; // Возвращаем пустой массив, если исходный пустой
        }

        // Удаляем нули из массива
        double[] nonZeroArray = Arrays.stream(arr)
                .filter(num -> num != 0)
                .toArray();

        Arrays.sort(nonZeroArray);

        return nonZeroArray;
    }

//6.	Создайте метод, который принимает строку в формате CamelCase и преобразует ее в формате SnakeCase.

    public static String camelToSnake (String camelCase) {
        StringBuilder snakeCase = new StringBuilder();
        for (int i=0;i<camelCase.length(); i++) {
            char c = camelCase.charAt(i);
            if (Character.isUpperCase(c)) {
                snakeCase.append('_');
                snakeCase.append(Character.toLowerCase(c));
            }else{
                snakeCase.append(c);
            }
        }
        return snakeCase.toString();
    }
//7.	Создайте метод, который принимает массив целых чисел и возвращает второй по величине элемент.


    public static int secondBiggest(int[] arr) {
        if (arr.length < 2) {
            throw new IllegalArgumentException("Массив должен содержать не менее двух элементов.");
        }

        Arrays.sort(arr);

        return arr[arr.length - 2];
    }

/*8.Создайте метод, принимающий строку и маркерный символ. Верните строку, в которой символы,
 находящиеся между парой маркерных символов, развернуты в обратном порядке.*/

    public static String localReverse(String str, char marker) {
        StringBuilder result = new StringBuilder();
        int startIndex = -1;
        int endIndex = -1;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == marker) {
                if (startIndex == -1) {
                    startIndex = i;
                } else {
                    endIndex = i;
                    result.append(str.substring(0, startIndex));
                    result.append(new StringBuilder(str.substring(startIndex + 1, endIndex)).reverse().toString());
                    result.append(str.substring(endIndex, str.length()));
                    startIndex = -1;
                }
            }
        }

        if (startIndex != -1) {
            result.append(str.substring(0, startIndex));
            result.append(new StringBuilder(str.substring(startIndex + 1)).reverse().toString());
        } else {
            result.append(str);
        }

        return result.toString();
    }

/*9.	Создайте метод, который принимает три целочисленных аргумента и возвращает количество целых чисел,
 имеющих одинаковое значение.*/

    public static int countEqualPairs(int a, int b, int c) {
        int count = 0;
        if (a == b && a == c) {
            count = 3;
        } else if (a == b || a == c || b == c) {
            count = 2;
        }
        return count;
    }
//10. Создайте функцию, которая принимает две строки и определяет, являются ли они анаграммами.

    public static boolean isAnagram(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        char[] charArray1 = str1.toCharArray();
        Arrays.sort(charArray1);
        char[] charArray2 = str2.toCharArray();
        Arrays.sort(charArray2);

        return Arrays.equals(charArray1, charArray2);
    }
}
