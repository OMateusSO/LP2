import java.util.Scanner;

public class P6{
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

        System.out.print("Informe a largura da parede em metros: ");
        double largura = scanner.nextDouble();

        System.out.print("Informe a altura da parede em metros: ");
        double altura = scanner.nextDouble();

        double area = largura * altura;
        double consumoTinta = area * 0.3;
        double qtdLatas = Math.ceil(consumoTinta / 2);

        System.out.printf("São necessárias %.2f latas de tinta! ",qtdLatas);

    }
}