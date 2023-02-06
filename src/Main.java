import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MonthData monthData;
        Converter converter = new Converter();
        StepTracker stepTracker = new StepTracker();
        try (Scanner scanner = new Scanner(System.in)) {
            monthData = new MonthData();
            String count;
            while (true) {
                printMenu();
                count = scanner.next();
                switch (count) {
                    case "1":
                        stepTracker.stepDay(scanner);
                        break;
                    case "2":
                        monthData.add(scanner);
                        break;
                    case "3":
                        stepTracker.showMonth(scanner, monthData.getTreeDate());
                        break;
                    case "4":
                        stepTracker.showTarget();
                        break;
                    case "5":
                        converter.stepConversion(scanner);
                        break;
                    case "0":
                        System.out.println("Выход.");
                        return;
                    default:
                        System.out.println("Вы ввели не число из меню.");
                        break;
                }
            }
        }
    }

    static void printMenu() {
        System.out.print("\nВведите 1 для: постановки цели по количества шагов в день.\n" +
                "Введите 2 для: ввода пройденного количества шагов за день.\n" +
                "Введите 3 для: просмотра статистики за определенный месяц.\n" +
                "Введите 4 для: просмотра цели на день.\n" +
                "Введите 5 для: перевода шагов в калории.\n" +
                "Введите 0 для: выхода из программы.\n");
    }
}
