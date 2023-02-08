import java.io.*;
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
        int parsIntDay;
        int parsIntMonth;
        int parsIntYear;
        int parsIntStep;
        while (true) {
            try {
                System.out.println("Введите год: ");
                year = scanner.next();
                parsIntYear = Integer.parseInt(year);
                if (parsIntYear > LocalDate.now().getYear()) {
                    System.out.println("Вы из будующего.");
                }else if(parsIntYear < LocalDate.now().getYear()-112){
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
                parsIntMonth = Integer.parseInt(month);
                if (parsIntMonth > 12 || parsIntMonth < 1) {
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
                parsIntDay = Integer.parseInt(day);
                if (parsIntDay > LocalDate.of(parsIntYear, parsIntMonth,1).
                        plusMonths(1).minusDays(1).getDayOfMonth()) {
                    System.out.println("В месяце дней: " +
                            LocalDate.of(parsIntYear, parsIntMonth, 1).plusMonths(1)
                                    .minusDays(1).getDayOfMonth());
                }else if(parsIntDay <= 0){
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
                parsIntStep = Integer.parseInt(step);
                if (parsIntStep > 274_252) {
                    System.out.println("Мировой рекорд 274_252 шагов в день. Вы попали в книгу рекордов гинеса.");
                }else if(parsIntStep < 0){
                    System.out.println("Ходьба назад-это тоже шаги, укажите положительным числом.");
                }
                else {
                    this.step = parsIntStep;
                    break;
                }
            } catch (Exception e) {
                System.out.println("Введено не целое число.");
            }
        }
        if (treeDate.get(LocalDate.of(parsIntYear, parsIntMonth, parsIntDay)) != null){
            treeDate.remove(LocalDate.of(parsIntYear, parsIntMonth, parsIntDay));
        }
        treeDate.put(LocalDate.of(parsIntYear, parsIntMonth, parsIntDay), this.step);
    }

    public void writerFile(){
        while (true) {
            try (FileWriter writer = new FileWriter("date.txt", false)) {
                writer.write(treeDate.toString());
                break;
            } catch (Exception e) {
                File file;
                try{
                    file = new File("date.txt");
                    file.createNewFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
    public void reader(){
        ArrayList<Character> chars;
        String str = "";
        try(FileReader reader = new FileReader("date.txt")){
            chars = new ArrayList<>();
            while (reader.ready()) {
                chars.add((char) reader.read());
            }
        for(int i=0; i< chars.size(); i++) {
            str += chars.get(i).toString();
        }
        StringTokenizer tokenizer = new StringTokenizer(str,"{=,}- ");
        ArrayList<String> strTokens = new ArrayList<>();
        while(tokenizer.hasMoreTokens()){
            strTokens.add(tokenizer.nextToken());
        }
        int year = 1;
        int month = 1;
        int day = 1;
        Integer step;
        int flag = 1;
        for (int i = 0; i < strTokens.size(); i++) {
            if (flag%4 == 0){
                flag =0;
                step = Integer.parseInt(strTokens.get(i));
                treeDate.put(LocalDate.of(year, month, day), step);
            } else if (flag % 3 == 0) {
                day = Integer.parseInt(strTokens.get(i));
            } else if (flag % 2 == 0) {
                month = Integer.parseInt(strTokens.get(i));
            }else {
                year = Integer.parseInt(strTokens.get(i));
            }
            flag++;
        }
        } catch (Exception e) {
            System.out.println("Привет! Вы у нас первый раз ^-^\nДавайте походим вместе.");
        }
    }

    public void printAll(){
        for(LocalDate date : treeDate.keySet()){
            System.out.printf("В этот день %s вы прошли %s шагов.\n", date, treeDate.get(date));
        }
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
