import java.util.Scanner;
public class P5{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o valor do raio do círculo: ");
        double raio = scanner.nextDouble();
        double area = Math.PI * raio * raio;
        System.out.println("A área do círculo é: " + area);
    }
}