import java.time.LocalDate;
import java.util.*;

public class StepTracker {
    private String target = "10000";
    public void stepDay(Scanner scanner){
        int parsIntTarget;
        while (true) {
            System.out.print("Введите количества шагов в день: ");
            try {
                target = scanner.next();
                parsIntTarget = Integer.parseInt(target);
                if (parsIntTarget <= 0) {
                    System.out.println("Количество шагов не может быть отрицательным или равно 0.");
                } else if (parsIntTarget > 0) {
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

    public void showMonth(Scanner scanner, TreeMap<LocalDate, Integer> monthData){
        if(monthData.isEmpty()){
            System.out.println("Вы не прошли ни шага.");
            return;
        }
        MonthData objMonthData = new MonthData();//новый объект для методов
        int seriesMax = 0;
        long day = 0;
        Converter converter = new Converter();
        String yearNumber;
        int parsIntYear;
        String monthNumber;
        int parsIntMonth;
        int numberTarget = 0;
        int allStepMonth = 0;
        while (true) {
            try {
                System.out.println("Введите год: ");
                yearNumber = scanner.next();
                parsIntYear = Integer.parseInt(yearNumber);
                break;
            } catch (Exception e) {
                System.out.println("Введено не целое число.");
            }
        }
        while (true) {
            try {
                System.out.println("Введите месяц от 1 до 12: ");
                monthNumber = scanner.next();
                parsIntMonth = Integer.parseInt(monthNumber);
                if (parsIntMonth > 12 || parsIntMonth < 1) {
                    System.out.println("Месяцев всего от 1 до 12.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Введено не целое число.");
            }
        }

        for (LocalDate date : monthData.keySet()) {
            if (objMonthData.getYear(date) == parsIntYear &&
                    objMonthData.getMonth(date) == parsIntMonth) {
                int step = monthData.get(date) != null ? monthData.get(date) : 0;
                System.out.println(date + ": прошли шагов " + step +
                        ", потратели килокалорий " + converter.getKilocalories(step) +
                        " и прошли дистанцию " + converter.getKm(step) + " км.");
                allStepMonth += step;
                int intTarget = Integer.parseInt(target);
                if (step >= intTarget && day == objMonthData.getStartTime(date)) {
                    numberTarget++;
                    day++;
                } else if (step >= intTarget) {
                    day = objMonthData.getStartTime(date)+1;
                    numberTarget = 1;
                } else{
                    day = objMonthData.getStartTime(date)+1;
                    numberTarget = 0;
                }
                if(seriesMax < numberTarget){
                    seriesMax = numberTarget;
                }
            }
        }
        System.out.println("В этот месяце: " + numberTarget +
                " раз(последняя серия достижения цели) и самая большая серия " + seriesMax +
                " раз, потратели килокалорий " + converter.getKilocalories(allStepMonth) +
                ", пройдена дистанция " + converter.getKm(allStepMonth) + " км.");
    }
}
