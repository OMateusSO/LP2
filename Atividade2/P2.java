import java.util.Scanner;

public class P2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a pressão do pneu (em psi): ");
        double pressao = scanner.nextDouble();

        System.out.print("Digite o volume do pneu (em litros): ");
        double volume = scanner.nextDouble();

        System.out.print("Digite a temperatura do pneu (em Celsius): ");
        double temperatura = scanner.nextDouble();

        double massaAr = (pressao * volume) / (0.37 * (temperatura + 273.15));

        System.out.println("A massa de ar do pneu é de: " + massaAr + " kg");
    
    }
}
