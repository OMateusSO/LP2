import java.util.Scanner;

public class P3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o primeiro termo da PA: ");
        double primeiroTermo = scanner.nextDouble();

        System.out.print("Digite a razão da PA: ");
        double razao = scanner.nextDouble();

        System.out.print("Digite o número do termo que deseja calcular: ");
        int n = scanner.nextInt();

        double nEsimoTermo = primeiroTermo + (n - 1) * razao;

        System.out.println("O " + n + "º termo da PA é: " + nEsimoTermo);
    }
}
