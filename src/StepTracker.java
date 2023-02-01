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
        if(monthData.size() > 0) {
            long time = monthData.get(0).getStartTime();
            ArrayList<MonthData> monthDataCopy = new ArrayList<>(monthData);
            for (int j = 0; j< monthDataCopy.size()-1; j++) {
                for (int i = 0; i < monthDataCopy.size(); i++) {
                    if (time > monthDataCopy.get(i).getStartTime()) {
                        monthData.set(i, monthDataCopy.get(i - 1));
                        monthData.set(i - 1, monthDataCopy.get(i));
                        time = monthDataCopy.get(i).getStartTime();
                    }
                }
            }
        }else {
            System.out.println("Вами не пройдено не одного дня.");
            return;
        }
        Converter converter = new Converter();
        String yearNumber;
        String monthNumber;
        int numberTarget = 0;
        long day = monthData.get(0).getStartTime();
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
                        ", потратели килокалорий " + converter.getKilocalories(date.getStep()) +
                        " и прошли дистанцию " + converter.getKm(date.getStep()) + " км.");
                allStepMonth += date.getStep();
                if (date.getStep() >= Integer.parseInt(target) && day == date.getStartTime()) {
                    numberTarget++;
                    day++;
                } else if (date.getStep() >= Integer.parseInt(target)) {
                    day = date.getStartTime()+1;
                    numberTarget = 1;
                } else{
                    day = date.getStartTime()+1;
                    numberTarget = 0;
                }
            }
        }
        System.out.println("В этот месяц вы достигли цели: " + numberTarget +
                " раз подряд и потратели килокалорий " + converter.getKilocalories(allStepMonth) +
                ", пройдена дистанция " + converter.getKm(allStepMonth) + " км.");
    }
}
