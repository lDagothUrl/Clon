import java.util.ArrayList;
import java.util.Scanner;

public class StepTracker {
    private String target = "0";
    public void stepDay(Scanner scanner){
        while (true) {
            System.out.print("Введите количества шагов в день: ");
            try {
                target = scanner.next();
                if (Integer.parseInt(target) <= 0) {
                    System.out.println("Количество шагов не может быть отрицательным или равно 0.");
                } else if (Integer.parseInt(target) > 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.printf("Это не целое число %S.\n", target);
            }
        }
    }
    public void showTarget() {
        if (target.equals("")) {
            System.out.println("Вы еще не задали цель на день.");
        } else {
            System.out.printf("Ваша цель на день: %S.\n", target);
        }
    }

    public void showMonth(Scanner scanner, ArrayList<MonthData> monthData){
        String yearNumber;
        String monthNumber;
        int numberTarget = 0;
        double allStepMonth = 0;
        while (true) {
            try {
                System.out.println("Введите год: ");
                yearNumber = scanner.next();
                break;
            } catch (Exception e) {
                System.out.println("Введено не целое число.");
            }
        }
        while (true) {
            try {
                System.out.println("Введите месяц от 1 до 12: ");
                monthNumber = scanner.next();
                if (Integer.parseInt(monthNumber) > 12 || Integer.parseInt(monthNumber) < 1) {
                    System.out.println("Месяцев всего от 1 до 12.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Введено не целое число.");
            }
        }
        for (MonthData date : monthData) {
            if (date.getYear() == Integer.parseInt(yearNumber) && date.getMonth() == Integer.parseInt(monthNumber)) {
                System.out.println(date.getLocalDate() + ": прошли шагов " + date.getStep() +
                        ", потратели килокалорий " + ((double) date.getStep()) * 0.05 +
                        " и прошли дистанцию " + ((double) date.getStep()) * 75 / 100_000 + " км.");
                allStepMonth += date.getStep();
                if (date.getStep() >= Integer.parseInt(target)) {
                    numberTarget++;
                }
            }
        }
        System.out.println("В этот месяц вы достигли цели: " + numberTarget +
                " раз и потратели килокалорий " + allStepMonth * 0.05 +
                ", пройдена дистанция " + allStepMonth * 75 / 100_000 + " км.");
    }
}
