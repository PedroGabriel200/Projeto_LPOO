package Interface;

import Modelo.Funcionario;
import Repositorio.FuncionarioRepositorio;

import java.util.List;
import java.util.Scanner;

public class FuncionarioMenu {

    private static final Scanner scanner =
            new Scanner(System.in);

    private static final FuncionarioRepositorio repositorio =
            FuncionarioRepositorio.getInstanciaFuncionario();

    public void menu() {
        exibir();
    }

    public static void exibir() {

        int opcao;

        do {

            System.out.println("\n==============================");
            System.out.println("      MENU FUNCIONÁRIOS");
            System.out.println("==============================");
            System.out.println("1 - Adicionar Funcionário");
            System.out.println("2 - Listar Funcionários");
            System.out.println("3 - Buscar Funcionário por ID");
            System.out.println("4 - Atualizar Funcionário");
            System.out.println("5 - Remover Funcionário");
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

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        double salario = lerDouble("Salário: ");

        Funcionario funcionario =
                new Funcionario(
                        0,
                        cpf,
                        nome,
                        matricula,
                        salario
                );

        boolean adicionou =
                repositorio.adicionar_Funcionario(funcionario);

        if (adicionou) {
            System.out.println("Funcionário adicionado com ID "
                    + funcionario.getId() + ".");
        } else {
            System.out.println("Não foi possível adicionar. CPF ou matrícula já cadastrados.");
        }
    }

    // LISTAR
    private static void listar() {

        List<Funcionario> funcionarios =
                repositorio.listar_Funcionarios();

        if (funcionarios.isEmpty()) {

            System.out.println("Nenhum funcionário cadastrado.");

        } else {

            for (Funcionario funcionario : funcionarios) {
                System.out.println();
                System.out.println(funcionario);
            }
        }
    }

    // BUSCAR
    private static void buscar() {

        int id =
                lerInteiro("Digite o ID do funcionário: ");

        Funcionario funcionario =
                repositorio.buscarPorId(id);

        if (funcionario != null) {

            System.out.println();
            System.out.println(funcionario);

        } else {

            System.out.println("Funcionário não encontrado.");
        }
    }

    // REMOVER
    private static void remover() {

        int id =
                lerInteiro("Digite o ID do funcionário: ");

        boolean removeu =
                repositorio.remover_Funcionario(id);

        if (removeu) {
            System.out.println("Funcionário removido.");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    // ATUALIZAR
    private static void atualizar() {

        int id =
                lerInteiro("Digite o ID do funcionário: ");

        Funcionario funcionarioExistente =
                repositorio.buscarPorId(id);

        if (funcionarioExistente == null) {

            System.out.println("Funcionário não encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();

        System.out.print("Novo CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Nova matrícula: ");
        String matricula = scanner.nextLine();

        double salario =
                lerDouble("Novo salário: ");

        Funcionario novoFuncionario =
                new Funcionario(
                        id,
                        cpf,
                        nome,
                        matricula,
                        salario
                );

        boolean atualizou =
                repositorio.atualizar_Funcionario(
                        id,
                        novoFuncionario
                );

        if (atualizou) {

            System.out.println("Funcionário atualizado.");

        } else {

            System.out.println(
                    "Não foi possível atualizar. " +
                            "CPF ou matrícula já cadastrados."
            );
        }
    }

    // LER INTEIRO
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

    // LER DOUBLE
    private static double lerDouble(String mensagem) {

        while (true) {

            System.out.print(mensagem);
            String entrada = scanner.nextLine();

            try {

                return Double.parseDouble(entrada);

            } catch (NumberFormatException erro) {

                System.out.println("Digite um valor válido.");
            }
        }
    }
}