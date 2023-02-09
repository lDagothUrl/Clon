import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class Converter {
    public void stepConversion(){
        while (true) {
            String flag = "0";
            int parsFlag;
            try {
                flag = showInputDialog("Введите количества шагов:");
                if (flag == null){
                    break;
                }
                parsFlag = Integer.parseInt(flag);
                if (parsFlag < 0) {
                    showMessageDialog(null, "Количество шагов не может быть отрицательным.");
                } else{
                    String str = String.format("За %S шагов: потратите %S килокалорий и пройдете дистанцию %S км."
                            , flag, getKilocalories(parsFlag), getKm(parsFlag));
                    showMessageDialog(null, str);
                    break;
                }
            } catch (Exception e) {
                showMessageDialog(null, "Это не целое число "+flag+".");
            }
        }
    }
    public String getKilocalories(int step){
        return String.format("%.3f", (double)step*0.05);
    }
    public String getKm(int step){
        return String.format("%.3f", (double)step*75/100_000);
    }
}
