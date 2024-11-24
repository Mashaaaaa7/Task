import java.util.ArrayList;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {
        System.out.println(isStrangePair("ratio", "orator"));
        System.out.println(isStrangePair("bush", "hubris"));
        System.out.println(isStrangePair("", ""));
        List<String[]> items = new ArrayList<>();
        items.add(new String[]{"Laptop", "124200"});
        items.add(new String[]{"Phone", "51450"});
        items.add(new String[]{"Headphones", "13800"});
        List<String[]> discountedItems = applyDiscount(items, 25);
        for (String[] item : discountedItems) {
            System.out.println("Товар: " + item[0] + ", Цена: " + item[1]);
        }

        boolean isHit = isShoot(-2, -3, 4, 5, 7);
        if (isHit) {
            System.out.println("Попадание в мишень!");
        } else {
            System.out.println("Промах!");
        }

        System.out.println(checkParity(12));

        System.out.println(rps("камень", "ножницы"));
        System.out.println(rps("бумага", "бумага"));
        System.out.println(rps("ножницы", "камень"));

        System.out.println(bugger(39));

        System.out.println("Самый дорогой объект: " + mostExpensive("Скакалка", 550, 8, "Шлем", 3750, 4, "Мяч", 2900, 10));

        System.out.println("Самая длинная подстрока с уникальными символами: " + longestUnique("bbb"));

        System.out.println("isPrefix(" + "arachnophobia" + "," + "arachno" + "):" + isPrefix("arachnophobia", "arachno"));
        System.out.println("isSuffix(" + "arachnophobia" + "," + "phobia" + "):" + isSuffix("arachnophobia", "phobia"));
        System.out.println(doesBrickFit(1, 2, 1, 1, 1));
        System.out.println(doesBrickFit(1, 1, 1, 1, 1));
    }

/*1.  Пара строк образует странную пару, если оба из следующих условий истинны:
– Первая буква 1-й строки = последняя буква 2-й строки.
– Последняя буква 1-й строки = первая буква 2-й строки.
– Создайте функцию, которая возвращает true, если пара строк представляет собой странную пару, и false в противном случае.*/

    public static boolean isStrangePair(String str1, String str2) {
        if (str1.isEmpty() && str2.isEmpty()) {
            return true;
        }
        if (str1.isEmpty() || str2.isEmpty()) {
            return false;
        } else {
            return str1.charAt(0) == str2.charAt(str2.length() - 1) &&
                    str1.charAt(str1.length() - 1) == str2.charAt(0);
        }
    }

/*2.  Создайте метод, принимающий на вход список товаров, где каждый элемент списка – массив, содержащий название товара
и его цену, и значение скидки, применяемой ко всем товарам.Верните список с товарами и новыми ценами на них.
Цена товара должна быть округлена до ближайшего целого числа, если после применения скидки цена товара становится
меньше 1, то ее следует округлить до 1.*/

    public static List<String[]> applyDiscount(List<String[]> items, double discount) {
        List<String[]> discountedItems = new ArrayList<>();

        for (String[] item : items) {
            String name = item[0];
            double originalPrice = Double.parseDouble(item[1]);
            double discountedPrice = originalPrice - (originalPrice * discount / 100);
            discountedPrice = Math.round(discountedPrice);
            discountedPrice = Math.max(discountedPrice, 1);
            discountedItems.add(new String[]{name, String.valueOf(discountedPrice)});
        }
        return discountedItems;
    }

    static class Sale {

        private String name;
        private double price;

        public Sale(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public double getDiscountedPrice(double discount) {
            double discountedPrice = price - (price * discount / 100);
            discountedPrice = Math.round(discountedPrice);
            discountedPrice = Math.max(discountedPrice, 1);
            return discountedPrice;
        }

        @Override
        public String toString() {
            return "Товар: " + name + ", Цена: " + price;
        }
    }

/*3.  Создайте метод, определяющий факт попадания в круглую мишень на координатной плоскости.
В качестве входных данных выступают координаты центра круглой мишени (первые два входных параметра x, y),
радиус мишени (третий входной параметр r) и координаты попадания (четвертый и пятый входные параметры m, n).
Чтобы узнать, принадлежит ли точка кругу, необходимо узнать превышает ли расстояние до центра величину радиуса.*/

    public static boolean isShoot(double x, double y, double r, double m, double n) {
        double distance = Math.sqrt(Math.pow(m - x, 2) + Math.pow(n - y, 2));
        return distance <= r;
    }

/*4.    Создайте функцию, которая принимает число в качестве входных данных и возвращает true, если сумма его цифр имеет ту же четность,
что и все число. В противном случае верните false.*/

    public static boolean checkParity(int number) {
        int sumOfDigits = sumDigits(number);
        return (number % 2 == 0) == (sumOfDigits % 2 == 0);
    }

    private static int sumDigits(int number) {
        int sum = 0;
        number = Math.abs(number);
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

/*5.	Создайте функцию, имитирующую игру "камень, ножницы, бумага". Функция принимает входные данные обоих игроков (камень, ножницы или бумага),
первый параметр от первого игрока, второй от второго игрока. Функция возвращает результат в следующем формате:
"Игрок 1 выигрывает", "Игрок 2 выигрывает", "Ничья" (если оба входа одинаковы)*/

    public static String rps(String player1, String player2) {
        if (player1.equals(player2)) {
            return "Ничья";
        } else if ((player1.equals("камень") && player2.equals("ножницы")) ||
                (player1.equals("ножницы") && player2.equals("бумага")) ||
                (player1.equals("бумага") && player2.equals("камень"))) {
            return "Игрок 1 выигрывает";
        } else {
            return "Игрок 2 выигрывает";
        }
    }

/*6.	Создайте метод, который принимает число и возвращает его мультипликативное постоянство,
которое представляет собой количество раз, которое вы должны умножать цифры введенного аргументаы,
пока не достигнете одной цифры.*/

    public static int bugger(int number) {
        if (number < 10) {
            return 0;
        }

        int count = 0;
        while (number >= 10) {
            count++;
            number = multiplyDigits(number);
        }
        return count;
    }

    private static int multiplyDigits(int number) {
        int product = 1;
        while (number > 0) {
            product *= number % 10;
            number /= 10;
        }
        return product;
    }

/*7.	Создайте метод, принимающий массив объектов, представляющих инвентарь,
где каждый предмет инвентаря представлен в виде массива строк и чисел (предмет, цена, количество),
и вычисляющий предмет инвентаря с наибольшей общей стоимостью (цена × количество).*/

    public static String mostExpensive(String object1, int price1, int quantity1,
                                       String object2, int price2, int quantity2,
                                       String object3, int price3, int quantity3) {
        int totalCost1 = price1 * quantity1;
        int totalCost2 = price2 * quantity2;
        int totalCost3 = price3 * quantity3;

        if (totalCost1 > totalCost2 && totalCost1 > totalCost3) {
            return object1;
        } else if (totalCost2 > totalCost1 && totalCost2 > totalCost3) {
            return object2;
        } else if (totalCost3 > totalCost1 && totalCost3 > totalCost2) {
            return object3;
        } else {
            return ("Цены одинаковые");
        }
    }

//8.	Напишите метод, находящий в строке подстроку из уникальных символов наибольшей длины.

    public static String longestUnique(String str) {
        if (str.isEmpty()) {
            return "";
        }
        String longestSubstring = "";
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                String substring = str.substring(i, j);
                if (isUnique(substring) && substring.length() > longestSubstring.length()) {
                    longestSubstring = substring;
                }
            }
        }
        return longestSubstring;
    }

    private static boolean isUnique(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

/*9.Создайте две функции: isPrefix(word, prefix-) и isSuffix (word, -suffix).
– isPrefix должен возвращать true, если он начинается с префиксного аргумента.
– isSuffix должен возвращать true, если он заканчивается аргументом суффикса.
– В противном случае верните false.*/

    public static boolean isPrefix(String word, String prefix) {
        if (word.length() < prefix.length()) {
            return false;
        }
        return word.substring(0, prefix.length()).equals(prefix); // Сравниваем начальную часть слова с префиксом
    }

    public static boolean isSuffix(String word, String Suffix) {
        if (word.length() < Suffix.length()) {
            return false;
        }
        return word.substring(0, Suffix.length()).equals(Suffix); // Сравниваем начальную часть слова с суффиксом
    }

/*10.	Напишите метод, который принимает следующие параметры (a, b, c, w, h):
три измерения кирпича: высоту (a), ширину (b) и глубину (c) и возвращает true,
если этот кирпич может поместиться в отверстие с шириной (w) и высотой (h).

Примечание:
        - Вы можете повернуть кирпич любой стороной к отверстию.
        - Если размеры кирпича равны размерам отверстия, то он поместится в отверстие.
        - Можно помещать кирпич только под ортогональным углом.*/

    public static boolean doesBrickFit(int a, int b, int c, int w, int h) {
        return (a <= w && b <= h) || (a <= h && b <= w) || (a <= w && c <= h) ||
                (a <= h && c <= w) || (b <= w && c <= h) || (b <= h && c <= w);
    }
}