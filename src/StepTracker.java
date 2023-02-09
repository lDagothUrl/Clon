import javax.swing.*;
import java.time.LocalDate;
import java.util.*;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class StepTracker {
    private String target = "10000";
    public void stepDay(){
        int parsIntTarget;
        while (true) {
            try {
                target = showInputDialog("Введите количества шагов в день:");
                if (target == null){
                    break;
                }
                parsIntTarget = Integer.parseInt(target);
                if (parsIntTarget <= 0) {
                    showMessageDialog(null,
                            "Количество шагов не может быть отрицательным или равно 0.");
                } else if (parsIntTarget > 0) {
                    break;
                }
            } catch (Exception e) {
                showMessageDialog(null, "Это не целое число " + target + ".");
            }
        }
    }
    public String getTarget(){return target;}
    public void setTarget(String target){
        this.target = target;
    }
    public void showTarget() {
        if (target == null) {
            showMessageDialog(null,"Вы еще не задали цель на день.");
        } else {
            showMessageDialog(null, "Ваша цель на день: "+target+".");
        }
    }

    public void showMonth(TreeMap<LocalDate, Integer> monthData){
        if(monthData.isEmpty()){
            showMessageDialog(null,"Вы не прошли ни шага.");
            return;
        }
        MonthData objMonthData = new MonthData();//новый объект для методов
        int seriesMax = 0;
        long day = 0;
        Converter converter = new Converter();
        String yearNumber;
        int parsIntYear;
        String monthNumber;
        int parsIntMonth = 0;
        int numberTarget = 0;
        int allStepMonth = 0;
        try {
            String[] sizeYear = new String[monthData.size()];
            int i = 0;
            for (LocalDate years : monthData.keySet()) {
                if (sizeYear[i] == null) {
                    sizeYear[i] = String.valueOf(years.getYear());
                } else if (Integer.parseInt(sizeYear[i]) != years.getYear()) {
                    i++;
                    sizeYear[i] = String.valueOf(years.getYear());
                }
            }
            String[] year = new String[i + 1];
            i = 0;
            for (String years : sizeYear) {
                if (years != null) {
                    year[i] = years;
                    i++;
                } else {
                    break;
                }
            }
            yearNumber = (String) showInputDialog(null, null, "Выберите год:",
                    JOptionPane.QUESTION_MESSAGE, null, year, year[0]);
            if (yearNumber == null) {
                return;
            }
            parsIntYear = Integer.parseInt(yearNumber);
        } catch (Exception e) {
            return;
        }

        try {
            String[] sizeMonth = new String[monthData.size()];
            int i = 0;
            for (LocalDate months : monthData.keySet()) {
                if (parsIntYear == months.getYear() && sizeMonth[i] == null) {
                    sizeMonth[i] = String.valueOf(months.getMonthValue());
                } else if (parsIntYear == months.getYear() && Integer.parseInt(sizeMonth[i]) != months.getMonthValue()) {
                    i++;
                    sizeMonth[i] = String.valueOf(months.getMonthValue());
                }
            }
            String[] month = new String[i + 1];
            i = 0;
            for (String months : sizeMonth) {
                if (months != null) {
                    month[i] = months;
                    i++;
                } else{
                    break;
                }
            }
            monthNumber = (String) showInputDialog(null, null, "Выберите месяц:",
                    JOptionPane.QUESTION_MESSAGE, null, month, month[0]);
            if (monthNumber == null) {
                return;
            }
            parsIntMonth = Integer.parseInt(monthNumber);
        } catch (Exception e) {
            showMessageDialog(null, "Введено не целое число.");
        }

        String strMessage = "";
        for (LocalDate date : monthData.keySet()) {
            if (objMonthData.getYear(date) == parsIntYear &&
                    objMonthData.getMonth(date) == parsIntMonth) {
                int step = monthData.get(date) != null ? monthData.get(date) : 0;
                strMessage += date + ": прошли шагов " + step +", потратели килокалорий " +
                        converter.getKilocalories(step) + " и прошли дистанцию " + converter.getKm(step) + " км.\n";
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
        strMessage +="\nВ этот месяце: " + numberTarget +
                " раз(последняя серия достижения цели) и самая большая серия " + seriesMax +
                " раз, потратели килокалорий " + converter.getKilocalories(allStepMonth) +
                ", пройдена дистанция " + converter.getKm(allStepMonth) + " км.";
        showMessageDialog(null, strMessage);
    }
}
