import static javax.swing.JOptionPane.*;

public class Main {
    public static void main(String[] args) {
        MonthData monthData;
        Converter converter = new Converter();
        StepTracker stepTracker = new StepTracker();
        monthData = new MonthData(stepTracker);
        monthData.reader();
        while (true) {
            switch (printMenu()) {
                case 1:
                    stepTracker.stepDay();
                    break;
                case 2:
                    monthData.add();
                    break;
                case 3:
                    stepTracker.showMonth(monthData.getTreeDate());
                    break;
                case 4:
                    stepTracker.showTarget();
                    break;
                case 5:
                    converter.stepConversion();
                    break;
                case 6:
                    monthData.printAll();
                    break;
                case 7:
                    showMessageDialog(null, "Выход.");
                    monthData.writerFile();
                    return;
            }
        }
    }

    static int printMenu() {
        String[] menu = {"1 для: постановки цели по количества шагов в день.",
                        "2 для: ввода пройденного количества шагов за день.",
                        "3 для: просмотра статистики за определенный месяц.",
                        "4 для: просмотра цели на день.",
                        "5 для: перевода шагов в калории.",
                        "6 для: получения всей статистики"};
                String indexStr =(String) showInputDialog(null,null,
                        "Menu.", QUESTION_MESSAGE, null, menu, menu[0]);
                int index = -1;
                if(indexStr == null){
                    return 7;
                }
                for (int i = 0; i<menu.length; i++){
                    if(indexStr.equals(menu[i])){
                        return (i+1);
                    }
                }
                return index;
    }
}
