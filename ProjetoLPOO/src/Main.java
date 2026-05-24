import Interface.CarroMenu;
import Interface.ClienteMenu;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int opcao;

        do {

            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("2 - Menu Clientes");
            System.out.println("1 - Menu Carros");
            System.out.println("0 - Sair");

            System.out.print("Escolha: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 2:
                    ClienteMenu clienteMenu = new ClienteMenu();
                    clienteMenu.menu();
                    break;
                case 1:
                    Interface.CarroMenu.exibir();
                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}