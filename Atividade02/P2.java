public class P2 {
    public static void main(String[] args) {
        int a = 1, b = 2, c = 3;

        int temp = a;
        a = c;
        c = b;
        b = temp;

        System.out.println("O conteúdo de A é " + a);
        System.out.println("O conteúdo de B é " + b);
        System.out.println("O conteúdo de C é " + c);

    }
}
