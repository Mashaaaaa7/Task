import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task4 {
    public static void main(String[] args) {

        System.out.println(nonRepeat("abracadabra"));
        System.out.println(nonRepeat("abababcac"));

        char[] l = {'0', '1', '2', '3', '4'}; // Алфавит
        bruteForce(1, 5, l, "", 0); // Вызов функции

        VernamCipher vernamCipher = new VernamCipher();

        int[] numbers = {0, 31, 28, 10, 29};
        System.out.println("Зашифрованное сообщение: " + vernamCipher.encode(numbers, "MKIIT"));
        System.out.println("Дешифрованное сообщение: " + Arrays.toString(vernamCipher.decode(vernamCipher.encode(numbers, "MKIIT"), "MKIIT")));

        System.out.println(split("((()))"));
        System.out.println(split("((()))(())()()(()())"));

        System.out.println(toStarShorthand("abbccc"));

        System.out.println(toRoman(1));
        System.out.println(toRoman(5));

        System.out.println(uniqueSubstring("31312131"));
        System.out.println(uniqueSubstring("1234567890"));

        int[][] maze = {
                {1, 3, 1},
                {1, -1, 1},
                {4, 2, 1}
        };

        PathResult result1 = findPath(maze);
        System.out.println("Путь: " + result1.path + ", Стоимость: " + result1.cost);

        System.out.println(Order("t3o the5m 1One all6 r4ule ri2ng."));

        System.out.println("aaabbbccc" + ": " + (isGfib("aaabbbccc")));
        System.out.println("ababa" + ": " + (isGfib("ababa")));
    }

// 1. Функция, удаляющая повторяющиеся символы

    public static String nonRepeat(String text) {
        if (text.length() <= 3) {
            return text;
        } else {
            char charToCount = Character.toLowerCase(text.charAt(0));
            int count = countOccurrences(text.toLowerCase(), charToCount);
            if (count > 3) {
                return nonRepeat(text.substring(1));
            } else {
                return charToCount + nonRepeat(text.substring(1));
            }
        }
    }

    private static int countOccurrences(String text, char charToCount) {
        int count = 0;
        for (char c : text.toCharArray()) {
            if (c == charToCount) {
                count++;
            }
        }
        return count;
    }

// 2. Функция, генерирующая комбинации слов

    public static void bruteForce(int n, int k, char[] l, String m, int o) {
        if (o == n) {
            System.out.println(m);
            return;
        }

        for (int i = 0; i < k; i++) {
            boolean used = false;
            for (int j = 0; j < o; j++) {
                if (m.charAt(j) == l[i]) {
                    used = true;
                    break;
                }
            }
            if (!used) {
                String newWord = m + Character.toString(l[i]);
                bruteForce(n, k, l, newWord, o + 1);
            }
        }
    }

// 3. функция Decode и Encode реализующий алгоритм шифрования Вернама.

    static class VernamCipher {

        public String encode(int[] numbers, String key) {
            StringBuilder encodedWord = new StringBuilder();
            for (int i = 0; i < numbers.length; i++) {
                int encodedChar = (numbers[i] + key.charAt(i % key.length())) % 256;
                encodedWord.append((char) encodedChar);
            }
            return encodedWord.toString();
        }

        public int[] decode(String word, String key) {
            int[] numbers = new int[word.length()];
            for (int i = 0; i < word.length(); i++) {
                numbers[i] = (word.charAt(i) - key.charAt(i % key.length())) % 256;
            }
            return numbers;
        }
    }
//4. функция, которая группирует строку в кластер скобок. Каждый кластер должен быть сбалансирован.

    public static List<String> split(String text) {
        List<String> clusters = new ArrayList<>();
        String currentCluster = "";
        int openBrackets = 0;
        for (char c : text.toCharArray()) {
            if (c == '(') {
                openBrackets++;
                currentCluster += c;
            } else if (c == ')') {
                openBrackets--;
                currentCluster += c;
                if (openBrackets == 0) {
                    clusters.add(currentCluster);
                    currentCluster = "";
                }
            }
        }
        return clusters;
    }

//5.	Напишите функцию, которая преобразует строку в звездную стенографию. Если символ повторяется n раз,
// преобразуйте его в символ*n.

    public static String toStarShorthand(String text) {
        StringBuilder result = new StringBuilder();
        char prevChar = '\0';
        int count = 1;

        for (char c : text.toCharArray()) {
            if (c == prevChar) {
                count++;
            } else {
                if (prevChar != '\0') {
                    result.append(prevChar);
                    if (count > 1) {
                        result.append('*').append(count);
                    }
                }
                prevChar = c;
                count = 1;
            }
        }

        if (prevChar != '\0') {
            result.append(prevChar);
            if (count > 1) {
                result.append('*').append(count);
            }
        }

        return result.toString();
    }

//6.	Напишите функцию, принимающую положительное целое число, не превышающее 1502, и возвращающую
// его представление в римской системе счисления.

    public static String toRoman(int num) {
        if (num < 1 || num > 1502) {
            throw new IllegalArgumentException("Число должно быть в диапазоне от 1 до 1502");
        }

        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanLiterals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman.toString();
    }

//7.	Напишите функцию, принимающую строку цифр, подсчитывающую количество повторяющихся элементов.
// И возвращающую “чет” – если самое большое количество повторений на четном индексе
// и “нечет” – если на нечетном.
//Если количество одинаковое, то возвращается первое вхождение.

    public static String uniqueSubstring(String digits) {
        if (digits.isEmpty()) {
            return ""; // Обработка пустой строки
        }

        int[] counts = new int[10]; // Массив для подсчета повторений цифр
        int maxCount = 0;
        int maxIndex = -1; // Индекс с максимальным количеством повторений

        for (int i = 0; i < digits.length(); i++) {
            int digit = digits.charAt(i) - '0';
            counts[digit]++;

            if (counts[digit] > maxCount) {
                maxCount = counts[digit];
                maxIndex = i;
            }
        }

        if (maxIndex % 2 == 0) {
            return "чет";
        } else {
            return "нечет";
        }
    }

//8.	Напишите функцию прохождения матричного лабиринта, с минимальным количеством затрат. На вход подается матрица n x n, в которой находятся отрицательные и положительные числа. Отрицательные числа – это стенки лабиринта, проходить через которые категорически нельзя. Положительные – стоимость вхождения в эту ячейку. Вход лабиринта правое нижнее значение матрицы, выход – левая верхняя. Вход и Выход не могут иметь отрицательное значение.
//Передвигаться можно только вверх или влево.
//Функция должна возвращать:
//А) Если путь существует, то сам путь, а также стоимость затрат.
//Б) Если пути нет, то сообщение: “Прохода нет”

    public static class PathResult {
        public int cost;
        public String path;

        public PathResult(int cost, String path) {
            this.cost = cost;
            this.path = path;
        }
    }

    public static PathResult findPath(int[][] maze) {
        int n = maze.length;
        if (maze[n - 1][n - 1] < 0 || maze[0][0] < 0) {
            return new PathResult(0, "Прохода нет");
        }
        int[][] costs = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(costs[i], Integer.MAX_VALUE);
        }
        costs[n - 1][n - 1] = maze[n - 1][n - 1];
        String[][] paths = new String[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(paths[i], "");
        }
        paths[n - 1][n - 1] = "E"; // E - выход
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (maze[i][j] >= 0) {
                    if (i > 0 && maze[i - 1][j] >= 0 && costs[i][j] + maze[i - 1][j] < costs[i - 1][j]) {
                        costs[i - 1][j] = costs[i][j] + maze[i - 1][j];
                        paths[i - 1][j] = paths[i][j] + "U"; // U - вверх
                    }
                    if (j > 0 && maze[i][j - 1] >= 0 && costs[i][j] + maze[i][j - 1] < costs[i][j - 1]) {
                        costs[i][j - 1] = costs[i][j] + maze[i][j - 1];
                        paths[i][j - 1] = paths[i][j] + "L"; // L - влево
                    }
                }
            }
        }
        if (costs[0][0] != Integer.MAX_VALUE) {
            return new PathResult(costs[0][0], paths[0][0]);
        } else {
            return new PathResult(0, "Прохода нет");
        }
    }


//9.	Создайте функцию, принимающую строку, содержащую числа внутри слов. Эти числа представляют
// расположение слова для новой строящейся строки.

    public static String Order(String input) {
        List<String> words = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                words.add(currentWord.toString());
                currentWord = new StringBuilder();
            } else {
                currentWord.append(c);
            }
        }
        words.add(currentWord.toString());

        List<String> sortedWords = new ArrayList<>(words.size());
        for (int i = 0; i < words.size(); i++) {
            sortedWords.add(null);
        }

        for (String word : words) {
            if (word.length() > 0 && Character.isDigit(word.charAt(0))) {
                int position = Integer.parseInt(word.substring(0, 1));
                sortedWords.set(position - 1, word.substring(1));
            }
        }

        StringBuilder result = new StringBuilder();
        for (String word : sortedWords) {
            result.append(word).append(" ");
        }

        return result.toString().trim();
    }


//10.	Напишите функцию, принимающую строку на вход, и возвращает принадлежность строки к золотому
// сечению. Строка принадлежит золотому сечению, если количество повторяющих элементов соответствует
// числам Фибоначчи.

    public static boolean isGfib(String str) {
        if (str.isEmpty()) {
            return false;
        }

        int[] counts = new int[256];
        for (char c : str.toCharArray()) {
            counts[c]++;
        }

        int maxCount1 = 0, maxCount2 = 0;
        for (int count : counts) {
            if (count > maxCount1) {
                maxCount2 = maxCount1;
                maxCount1 = count;
            } else if (count > maxCount2) {
                maxCount2 = count;
            }
        }

        return isFibonacci(maxCount1) && isFibonacci(maxCount2);
    }

    private static boolean isFibonacci(int num) {
        if (num <= 1) {
            return true;
        }
        int a = 0, b = 1;
        while (b < num) {
            int temp = b;
            b = a + b;
            a = temp;
        }
        return b == num;
    }
}