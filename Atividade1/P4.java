import java.util.Scanner;

public class P4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Qual é o seu nome? ");
        String nome = scanner.nextLine();

        System.out.print("Quantos anos você tem? ");
        int idade = scanner.nextInt();

        int diasDeVida = idade * 365;

        System.out.println(nome + ", você viveu aproximadamente " + diasDeVida + " dias.");
    }
}
