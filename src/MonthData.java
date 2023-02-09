import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class MonthData {
    private final TreeMap<LocalDate, Integer> treeDate = new TreeMap<>(Comparator.comparing(LocalDate::toEpochDay));
    private int step;
    private StepTracker stepTracker;
    MonthData(){}
    MonthData(StepTracker stepTracker){
        this.stepTracker =stepTracker;
    }

    public void add(){
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
                year = showInputDialog("Введите год:");
                if(year == null){
                    return;
                }
                parsIntYear = Integer.parseInt(year);
                if (parsIntYear > LocalDate.now().getYear()) {
                    showMessageDialog(null, "Вы из будующего.");
                }else if(parsIntYear < LocalDate.now().getYear()-112){
                    showMessageDialog(null,"Вы старее песка. Вам бы полежать.");
                }
                else {
                    break;
                }
            } catch (Exception e) {
                showMessageDialog(null,"Введено не целое число.");
            }
        }
        while (true) {
            try {
                month = showInputDialog("Введите месяц от 1 до 12:");
                if (month == null){
                    return;
                }
                parsIntMonth = Integer.parseInt(month);
                if (parsIntMonth > 12 || parsIntMonth < 1) {
                    showMessageDialog(null, "Месяцев всего от 1 до 12.");
                }
                else {
                    break;
                }
            } catch (Exception e) {
                showMessageDialog(null, "Введено не целое число.");
            }
        }
        while (true) {
            try {
                day = showInputDialog("Введите день:");
                if (day == null){
                    return;
                }
                parsIntDay = Integer.parseInt(day);
                if (parsIntDay > LocalDate.of(parsIntYear, parsIntMonth,1).
                        plusMonths(1).minusDays(1).getDayOfMonth()) {
                    String str = "В месяце дней: "+LocalDate.of(parsIntYear, parsIntMonth, 1).
                            plusMonths(1).minusDays(1).getDayOfMonth();
                    showMessageDialog(null, str);
                }else if(parsIntDay <= 0){
                    showMessageDialog(null,"Отрицательных дней не существует, " +
                            "хоть и в это день ваше настроение может быть плохим.");
                }
                else {
                    break;
                }
            } catch (Exception e) {
                showMessageDialog(null, "Введено не целое число.");
            }
        }
        while (true) {
            try {
                step = showInputDialog("Введите число шагов за день:");
                if (step == null){
                    return;
                }
                parsIntStep = Integer.parseInt(step);
                if (parsIntStep > 274_252) {
                    showMessageDialog(null, "Мировой рекорд 274_252 шагов в день. Вы попали в книгу рекордов гинеса.");
                }else if(parsIntStep < 0){
                    showMessageDialog(null, "Ходьба назад-это тоже шаги, укажите положительным числом.");
                }
                else {
                    this.step = parsIntStep;
                    break;
                }
            } catch (Exception e) {
                showMessageDialog(null, "Введено не целое число.");
            }
        }
        if (treeDate.get(LocalDate.of(parsIntYear, parsIntMonth, parsIntDay)) != null){
            treeDate.remove(LocalDate.of(parsIntYear, parsIntMonth, parsIntDay));
        }
        treeDate.put(LocalDate.of(parsIntYear, parsIntMonth, parsIntDay), this.step);
        writerFile();
    }

    public void writerFile(){
        while (true) {
            try (FileWriter writer = new FileWriter("date.txt", false)) {
                writer.write(stepTracker.getTarget());
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
        stepTracker.setTarget(strTokens.get(0));
        int year = 1;
        int month = 1;
        int day = 1;
        int steps;
        int flag = 1;
        for (int i = 1; i < strTokens.size(); i++) {
            if (flag%4 == 0){
                flag =0;
                steps = Integer.parseInt(strTokens.get(i));
                treeDate.put(LocalDate.of(year, month, day), steps);
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
            showMessageDialog(null, "Привет! Вы у нас первый раз ^-^\nДавайте походим вместе.");
        }
    }

    public void printAll(){
        String str = "";
        for(LocalDate date : treeDate.keySet()){
            str += "В этот день " + date + " вы прошли " + treeDate.get(date) +" шагов.\n";
        }
        showMessageDialog(null, str);
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
