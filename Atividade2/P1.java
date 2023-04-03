import java.util.Scanner;

public class P1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a temperatura em graus Fahrenheit: ");
        double fahrenheit = scanner.nextDouble();

        double celsius = (5.0 / 9.0) * (fahrenheit - 32.0);

        System.out.println("A temperatura em graus Celsius é: " + celsius);
    }
}