import java.util.Scanner;

public class P1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o primeiro número: ");
        int num1 = sc.nextInt();

        System.out.print("Digite o segundo número: ");
        int num2 = sc.nextInt();

        System.out.print("Digite o terceiro número: ");
        int num3 = sc.nextInt();

        System.out.println("Os números digitados, na ordem inversa, são: " + num3 + " " + num2 + " " + num1);

    }
}
