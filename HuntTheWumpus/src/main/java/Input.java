import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static int lerOpcao() {
        System.out.print("Escolha sua opção: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Opção inválida. Escolha novamente: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
