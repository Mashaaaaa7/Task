public class Task1{

    public static void main(String[] args) {
        System.out.println(galToLit(8));
        System.out.println(calculateCaloriesBurned(9,2));
        System.out.println(calculateTotalItems(3,4,2));
        System.out.println(determineTriangleType(6, 6, 6));
        System.out.println("Максимальное число: " + getMax(5,10));
        System.out.println(numberCovers(22, 1.4,2.0));
        System.out.println("Factorial of " + 4 + " is " + factorial(4));
        System.out.println("Наибольший общий делитель чисел равен " + gcd(15, 36));
        System.out.println("Общая выручка от продажи билетов равна " + calculateRevenue(70, 1500));
        System.out.println(calculateSeats(5, 2));   // 1
    }


/* 1
Создайте функцию, которая принимает целое число галлонов и преобразует его в литры.
Присвойте файлу с исходным кодом имя GalToLit
*/

    public static double galToLit(double gallons) {
        return gallons * 3.7854;
    }

/* 2. Вы пишете программу для квази-фитнес-приложения и хотите создать функцию для расчета калорий,
сожженных пользователем во время тренировки. Функция должна принимать время тренировки в минутах
и интенсивность, где 1 – низкая интенсивность, 2 – средняя, 3 – высокая, а затем вычислять
количество сожженных калорий на основе этой информации.
*/

    public static double calculateCaloriesBurned(int minutes, int intensity) {
        double caloriesPerMinute;
        if (intensity == 1) {
            caloriesPerMinute = 15.0;
        } else if (intensity == 2) {
            caloriesPerMinute = 24.0;
        } else if (intensity == 3) {
            caloriesPerMinute = 41.0;
        } else {
            return 0.0;
        }
        double totalCalories = caloriesPerMinute * minutes;
        return totalCalories;
    }


/* 3. В этой задаче вы управляете складом, где хранятся товары трех типов:

- Коробки содержат по 20 товаров в каждой.
- Мешки содержат по 50 товаров в каждом.
- Бочки содержат по 100 товаров в каждой.

Вам предоставили информацию о количестве каждого типа емкостей на складе, и вам нужно создать функцию, которая вернет общее количество товаров на складе, учитывая объекты хранения разных типов*\
 */

    public static double calculateTotalItems(int boxes, int bags, int barrels) {

        double totalItems = 0;

        double itemsInBoxes = boxes * 20;
        totalItems += itemsInBoxes;

        double itemsInBags = bags * 50;
        totalItems += itemsInBags;

        double itemsInBarrels = barrels * 100;
        totalItems += itemsInBarrels;

        return totalItems;
    }

/*4.    Создайте функцию, которая принимает 3 числа: X, Y и Z. Эти числа представляют длины сторон треугольника.
Функция должна вернуть тип треугольника на основе данных сторон: "равносторонний" (если все стороны равны),
 "равнобедренный" (если две стороны равны), "разносторонний" (если все стороны разные) или "не является
треугольником" (если невозможно построить треугольник с заданными сторонами).*/

    public static String determineTriangleType(double x, double y, double z) {
        if (x <= 0 || y <= 0 || z <= 0 || (x + y <= z) || (x + z <= y) || (y + z <= x)) {
            return "не является треугольником";
        }
        if (x == y && y == z) {
            return "равносторонний";
        }
        if (x == y || y == z || x == z) {
            return "равнобедренный";
        }
        return "разносторонний";
    }

/*5.   В Java есть вариация условного оператора – тернарный оператор "? :",
принимающий три операнда и возвращающий один из них на основе значения условия.
Он имеет следующую структуру:условие ? выражение1 : выражение2
Ваша задача создать функцию, которая принимает два числа a и b, а затем с помощью тернарного
оператора определяет,какое из чисел больше, и возвращает большее число.*/


    public static int getMax(int a, int b) {
        return a > b ? a : b;
    }

/* 6.   У меня есть ограниченное количество ткани определенной длины, и я хочу сшить как можно больше
пододеяльников. Создайте функцию, которая будет принимать длину ткани (в метрах) и размер одной детали
(ширина и длина в метрах), а затем возвращать количество пододеяльников, которые я смогу сшить, прежде
чем кончится рулон.
n * 2 – это количество квадратных метров имеющейся ткани,
w и h – это длина и ширина одной детали в метрах
*/


    public static int numberCovers(double cloth, double w, double h) {
        double SquareDetail = h * w;
        double Squarecloth = cloth / 2;
        int number = (int) (Squarecloth / SquareDetail);
        return number;
    }

/* 7. Напишите функцию, вычисляющую факториал выбранного числа.*/


    static int factorial(int n) {
        int res = 1, i;
        for (i = 2; i <= n; i++)
            res *= i;
        return res;
    }

/* 8.    Создайте функцию, которая находит наибольший общий делитель двух чисел.*/

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

/* 9.   Создайте функцию, которая принимает количество билетов на концерт, проданных через веб-сервис,
 и стоимость одного билета с учетом фиксированной комиссии. Функция должна вернуть общую выручку от продажи билетов.
 */

    public static int calculateRevenue(int ticketsQuantity, int ticketPrice) {
        return (int) (ticketsQuantity * ticketPrice * 0.72);
    }

/*10.  Создайте функцию, которая принимает целое число студентов и количество парт в аудитории.
Функция должна определить, сколько столов не хватает для размещения всех студентов, если за одним столом
 помещается два студента.*/

    public static int calculateSeats(int students, int desks) {

        int maxSeats = desks * 2;

        if (students > maxSeats) {
            return students - maxSeats;
        } else {
            return 0;

        }
    }
}