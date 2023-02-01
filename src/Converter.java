import java.util.Scanner;

public class Converter {
    public void stepConversion(Scanner scanner){
        while (true) {
            System.out.print("Введите количества шагов: ");
            String flag = "0";
            double parsFlag;
            try {
                flag = scanner.next();
                parsFlag = Integer.parseInt(flag);
                if (parsFlag < 0) {
                    System.out.println("Количество шагов не может быть отрицательным.");
                } else{
                    System.out.printf("За %S шагов: потратите %S килокалорий и пройдете дистанцию %S км.\n"
                            , flag, getKilocalories(parsFlag), getKm(parsFlag));
                    break;
                }
            } catch (Exception e) {
                System.out.printf("Это не целое число %S.\n", flag);
            }
        }
    }
    public double getKilocalories(double step){
        return step*0.05;
    }
    public double getKm(double step){
        return step*75/100_000;
    }
}
