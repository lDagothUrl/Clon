import java.time.LocalDate;
import java.util.*;

public class MonthData {
    private final TreeMap<LocalDate, Integer> treeDate = new TreeMap<>(Comparator.comparing(LocalDate::toEpochDay));
    private int step = 0;

    public void add(Scanner scanner){
        String year;
        String month;
        String day;
        String step;
        while (true) {
            try {
                System.out.println("Введите год: ");
                year = scanner.next();
                if (Integer.parseInt(year) > LocalDate.now().getYear()) {
                    System.out.println("Вы из будующего.");
                }else if(Integer.parseInt(year) < LocalDate.now().getYear()-112){
                    System.out.println("Вы старее песка. Вам бы полежать.");
                }
                else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Введено не целое число.");
            }
        }
        while (true) {
            try {
                System.out.println("Введите месяц от 1 до 12: ");
                month = scanner.next();
                if (Integer.parseInt(month) > 12 || Integer.parseInt(month) < 1) {
                    System.out.println("Месяцев всего от 1 до 12.");
                }
                else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Введено не целое число.");
            }
        }
        while (true) {
            try {
                System.out.println("Введите день: ");
                day = scanner.next();
                if (Integer.parseInt(day) > LocalDate.of(Integer.parseInt(year), Integer.parseInt(month),
                        1).plusMonths(1).minusDays(1).getDayOfMonth()) {
                    System.out.println("В месяце дней: " +
                            LocalDate.of(Integer.parseInt(year),
                                    Integer.parseInt(month), 1).plusMonths(1)
                                    .minusDays(1).getDayOfMonth());
                }else if(Integer.parseInt(day) <= 0){
                    System.out.println("Отрицательных дней не существует, " +
                            "хоть и в это день ваше настроение может быть плохим.");
                }
                else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Введено не целое число.");
            }
        }
        while (true) {
            try {
                System.out.println("Введите число шагов за день: ");
                step = scanner.next();
                if (Integer.parseInt(step) > 274_252) {
                    System.out.println("Мировой рекорд 274_252 шагов в день. Вы попали в книгу рекордов гинеса.");
                }else if(Integer.parseInt(step) < 0){
                    System.out.println("Ходьба назад-это тоже шаги, укажите положительным числом.");
                }
                else {
                    this.step = Integer.parseInt(step);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Введено не целое число.");
            }
        }
        if (treeDate.get(LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day))) != null){
            treeDate.remove(LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day)));
        }
        treeDate.put(LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day)), this.step);
    }

    public long getStartTime(LocalDate date){return date.toEpochDay();}
    public int getMonth(LocalDate date){
        return date.getMonthValue();
    }
    public int getYear(LocalDate date){
        return date.getYear();
    }
    public TreeMap<LocalDate, Integer> getTreeDate() {
        return treeDate;
    }
}
