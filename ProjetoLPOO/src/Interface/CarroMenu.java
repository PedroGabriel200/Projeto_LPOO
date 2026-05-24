package Interface;

import Modelo.Carro;
import Repositorio.CarroRepositorio;

import java.util.List;
import java.util.Scanner;

public class CarroMenu {

    private static final Scanner scanner =
            new Scanner(System.in);

    private static final CarroRepositorio repositorio =
            CarroRepositorio.getInstanciaCarro();

    public static void exibir() {

        int opcao;

        do {

            System.out.println("\n==============================");
            System.out.println("        MENU CARROS");
            System.out.println("==============================");
            System.out.println("1 - Adicionar Carro");
            System.out.println("2 - Listar Carros");
            System.out.println("3 - Buscar Carro por ID");
            System.out.println("4 - Atualizar Carro");
            System.out.println("5 - Remover Carro");
            System.out.println("0 - Voltar");
            System.out.println("==============================");

            opcao = lerInteiro("Escolha: ");

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
                    atualizar();
                    break;

                case 5:
                    remover();
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

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        boolean disponivel = lerBoolean("Disponível (true/false): ");

        Carro carro =
                new Carro(modelo, placa, disponivel);

        boolean adicionou =
                repositorio.adicionar_Carros(carro);

        if (adicionou) {
            System.out.println("Carro adicionado com ID " + carro.getId() + ".");
        } else {
            System.out.println("Não foi possível adicionar. Placa já cadastrada.");
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
                System.out.println();
                System.out.println(carro);
            }
        }
    }

    // BUSCAR
    private static void buscar() {

        int id = lerInteiro("Digite o ID do carro: ");

        Carro carro =
                repositorio.buscarPorId(id);

        if (carro != null) {
            System.out.println();
            System.out.println(carro);
        } else {
            System.out.println("Carro não encontrado.");
        }
    }

    // REMOVER
    private static void remover() {

        int id = lerInteiro("Digite o ID do carro: ");

        boolean removeu =
                repositorio.remover_Carros(id);

        if (removeu) {
            System.out.println("Carro removido.");
        } else {
            System.out.println("Carro não encontrado.");
        }
    }

    // ATUALIZAR
    private static void atualizar() {

        int id = lerInteiro("Digite o ID do carro: ");

        Carro carroExistente =
                repositorio.buscarPorId(id);

        if (carroExistente == null) {

            System.out.println("Carro não encontrado.");
            return;
        }

        System.out.print("Novo modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Nova placa: ");
        String novaPlaca = scanner.nextLine();

        boolean disponivel = lerBoolean("Disponível (true/false): ");

        Carro novoCarro =
                new Carro(modelo, novaPlaca, disponivel);

        boolean atualizou =
                repositorio.atualizar_Carros(
                        id,
                        novoCarro
                );

        if (atualizou) {
            System.out.println("Carro atualizado.");
        } else {
            System.out.println("Não foi possível atualizar. Verifique se a placa já está cadastrada.");
        }
    }

    private static int lerInteiro(String mensagem) {

        while (true) {

            System.out.print(mensagem);
            String entrada = scanner.nextLine();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException erro) {
                System.out.println("Digite um número válido.");
            }
        }
    }

    private static boolean lerBoolean(String mensagem) {

        while (true) {

            System.out.print(mensagem);
            String entrada = scanner.nextLine();

            if (entrada.equalsIgnoreCase("true")) {
                return true;
            }

            if (entrada.equalsIgnoreCase("false")) {
                return false;
            }

            System.out.println("Digite true ou false.");
        }
    }
}
