package Interface;

import Modelo.Carro;
import Repositorio.CarroRepositorio;

import java.util.List;
import java.util.Scanner;

public class CarroMenu {

    private static final Scanner scanner =
            new Scanner(System.in);

    private static final CarroRepositorio repositorio =
            CarroRepositorio.getInstancia();

    public static void exibir() {

        int opcao;

        do {

            System.out.println("\n===== MENU CARROS =====");
            System.out.println("1 - Adicionar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar");
            System.out.println("4 - Remover");
            System.out.println("5 - Atualizar");
            System.out.println("0 - Voltar");

            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    adicionar();
                    break;

                case 2:
                    listar();
                    break;

                case 3:
                    buscar();
                    break;

                case 4:
                    remover();
                    break;

                case 5:
                    atualizar();
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    // ADICIONAR
    private static void adicionar() {

        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        System.out.print("Disponível (true/false): ");
        boolean disponivel = scanner.nextBoolean();

        Carro carro =
                new Carro(id, modelo, placa, disponivel);

        boolean adicionou =
                repositorio.adicionar_Carros(carro);

        if (adicionou) {
            System.out.println("Carro adicionado.");
        } else {
            System.out.println("Placa já cadastrada.");
        }
    }

    // LISTAR
    private static void listar() {

        List<Carro> carros =
                repositorio.listar_Carros();

        if (carros.isEmpty()) {

            System.out.println("Nenhum carro cadastrado.");

        } else {

            for (Carro carro : carros) {
                System.out.println(carro);
            }
        }
    }

    // BUSCAR
    private static void buscar() {

        System.out.print("Digite a placa: ");
        String placa = scanner.nextLine();

        Carro carro =
                repositorio.buscarPorPlaca(placa);

        if (carro != null) {
            System.out.println(carro);
        } else {
            System.out.println("Carro não encontrado.");
        }
    }

    // REMOVER
    private static void remover() {

        System.out.print("Digite a placa: ");
        String placa = scanner.nextLine();

        boolean removeu =
                repositorio.remover_Carros(placa);

        if (removeu) {
            System.out.println("Carro removido.");
        } else {
            System.out.println("Carro não encontrado.");
        }
    }

    // ATUALIZAR
    private static void atualizar() {

        System.out.print("Digite a placa do carro: ");
        String placa = scanner.nextLine();

        Carro carroExistente =
                repositorio.buscarPorPlaca(placa);

        if (carroExistente == null) {

            System.out.println("Carro não encontrado.");
            return;
        }

        System.out.print("Novo ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Novo modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Nova placa: ");
        String novaPlaca = scanner.nextLine();

        System.out.print("Disponível (true/false): ");
        boolean disponivel = scanner.nextBoolean();

        Carro novoCarro =
                new Carro(id, modelo, novaPlaca, disponivel);

        repositorio.atualizar_Carros(
                placa,
                novoCarro
        );

        System.out.println("Carro atualizado.");
    }
}