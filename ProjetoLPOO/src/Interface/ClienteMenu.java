package Interface;

import Modelo.Cliente;
import Repositorio.ClienteRepositorio;

import java.util.List;
import java.util.Scanner;

public class ClienteMenu {

    private static final Scanner scanner =
            new Scanner(System.in);

    private static final ClienteRepositorio repositorio =
            ClienteRepositorio.getInstanciaCliente();

    public void menu() {
        exibir();
    }

    public static void exibir() {

        int opcao;

        do {

            System.out.println("\n==============================");
            System.out.println("        MENU CLIENTES");
            System.out.println("==============================");
            System.out.println("1 - Adicionar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Buscar Cliente por ID");
            System.out.println("4 - Atualizar Cliente");
            System.out.println("5 - Remover Cliente");
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

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("CNH: ");
        String cnh = scanner.nextLine();

        Cliente cliente =
                new Cliente(nome, cpf, cnh);

        boolean adicionou =
                repositorio.adicionar_Clientes(cliente);

        if (adicionou) {
            System.out.println("Cliente adicionado com ID " + cliente.getId() + ".");
        } else {
            System.out.println("Não foi possível adicionar. CPF já cadastrado.");
        }
    }

    // LISTAR
    public static void listar() {

        List<Cliente> clientes =
                repositorio.listar_Clientes();

        if (clientes.isEmpty()) {

            System.out.println("Nenhum cliente cadastrado.");

        } else {

            for (Cliente cliente : clientes) {
                System.out.println();
                System.out.println(cliente);
            }
        }
    }

    // BUSCAR
    private static void buscar() {

        int id = lerInteiro("Digite o ID do cliente: ");

        Cliente cliente =
                repositorio.buscarPorId(id);

        if (cliente != null) {
            System.out.println();
            System.out.println(cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    // REMOVER
    private static void remover() {

        int id = lerInteiro("Digite o ID do cliente: ");

        boolean removeu =
                repositorio.remover_Clientes(id);

        if (removeu) {
            System.out.println("Cliente removido.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    // ATUALIZAR
    private static void atualizar() {

        int id = lerInteiro("Digite o ID do cliente: ");

        Cliente clienteExistente =
                repositorio.buscarPorId(id);

        if (clienteExistente == null) {

            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();

        System.out.print("Novo CPF: ");
        String novoCpf = scanner.nextLine();

        System.out.print("Nova CNH: ");
        String cnh = scanner.nextLine();

        Cliente novoCliente =
                new Cliente(nome, novoCpf, cnh);

        boolean atualizou =
                repositorio.atualizar_Clientes(
                        id,
                        novoCliente
                );

        if (atualizou) {
            System.out.println("Cliente atualizado.");
        } else {
            System.out.println("Não foi possível atualizar. Verifique se o CPF já está cadastrado.");
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
}
