package Interface;

import Modelo.Carro;
import Modelo.Cliente;
import Modelo.Funcionario;
import Modelo.Locacao;

import Repositorio.CarroRepositorio;
import Repositorio.ClienteRepositorio;
import Repositorio.FuncionarioRepositorio;
import Repositorio.LocacaoRepositorio;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class LocacaoMenu {

    private static final Scanner scanner =
            new Scanner(System.in);

    private static final LocacaoRepositorio locacaoRepositorio =
            LocacaoRepositorio.getInstanciaLocacao();

    private static final ClienteRepositorio clienteRepositorio =
            ClienteRepositorio.getInstanciaCliente();

    private static final CarroRepositorio carroRepositorio =
            CarroRepositorio.getInstanciaCarro();

    private static final FuncionarioRepositorio funcionarioRepositorio =
            FuncionarioRepositorio.getInstanciaFuncionario();

    public void menu() {
        exibir();
    }

    public static void exibir() {

        int opcao;

        do {

            System.out.println("\n==============================");
            System.out.println("        MENU LOCAÇÕES");
            System.out.println("==============================");
            System.out.println("1 - Criar Locação");
            System.out.println("2 - Listar Locações");
            System.out.println("3 - Buscar Locação por ID");
            System.out.println("4 - Atualizar Locação");
            System.out.println("5 - Remover Locação");
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
        System.out.println("Clientes Cadastrados:");
        ClienteMenu.listar();
        System.out.println();

        int idCliente =
                lerInteiro("ID do cliente: ");

        Cliente cliente =
                clienteRepositorio.buscarPorId(idCliente);

        if (cliente == null) {

            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("Carros Cadastrados:");
        CarroMenu.listar();
        System.out.println();

        int idCarro =
                lerInteiro("ID do carro: ");

        Carro carro =
                carroRepositorio.buscarPorId(idCarro);

        if (carro == null) {

            System.out.println("Carro não encontrado.");
            return;
        }

        if (!carro.isDisponivel()) {

            System.out.println("Carro indisponível.");
            return;
        }
        System.out.println("Funcionários Cadastrados:");
        FuncionarioMenu.listar();
        System.out.println();

        int idFuncionario =
                lerInteiro("ID do funcionário: ");

        Funcionario funcionario =
                funcionarioRepositorio.buscarPorId(idFuncionario);

        if (funcionario == null) {

            System.out.println("Funcionário não encontrado.");
            return;
        }

        LocalDate dataInicio =
                lerData("Data de início (AAAA-MM-DD): ");

        LocalDate dataFim =
                lerData("Data de fim (AAAA-MM-DD): ");

        Locacao locacao =
                new Locacao(
                        0,
                        cliente,
                        carro,
                        dataInicio,
                        dataFim,
                        funcionario
                );

        boolean adicionou =
                locacaoRepositorio.adicionar_Locacao(locacao);

        if (adicionou) {

            System.out.println(
                    "Locação criada com ID "
                            + locacao.getId()
            );

        } else {

            System.out.println(
                    "Não foi possível criar a locação."
            );
        }
    }

    // LISTAR
    private static void listar() {

        List<Locacao> locacoes =
                locacaoRepositorio.listar_Locacoes();

        if (locacoes.isEmpty()) {

            System.out.println(
                    "Nenhuma locação cadastrada."
            );

        } else {

            for (Locacao locacao : locacoes) {

                System.out.println("\n====================");
                System.out.println(
                        "ID: " + locacao.getId()
                );

                System.out.println(
                        "Cliente: "
                                + locacao.getCliente().getNome()
                );

                System.out.println(
                        "Carro: "
                                + locacao.getCarro().getModelo()
                );

                System.out.println(
                        "Funcionário: "
                                + locacao.getFuncionario().getNome()
                );

                System.out.println(
                        "Data início: "
                                + locacao.getDataInicio()
                );

                System.out.println(
                        "Data fim: "
                                + locacao.getDataFim()
                );
            }
        }
    }

    // BUSCAR
    private static void buscar() {

        int id =
                lerInteiro("Digite o ID da locação: ");

        Locacao locacao =
                locacaoRepositorio.buscarPorId(id);

        if (locacao != null) {

            System.out.println("\n====================");

            System.out.println(
                    "ID: " + locacao.getId()
            );

            System.out.println(
                    "Cliente: "
                            + locacao.getCliente().getNome()
            );

            System.out.println(
                    "Carro: "
                            + locacao.getCarro().getModelo()
            );

            System.out.println(
                    "Funcionário: "
                            + locacao.getFuncionario().getNome()
            );

            System.out.println(
                    "Data início: "
                            + locacao.getDataInicio()
            );

            System.out.println(
                    "Data fim: "
                            + locacao.getDataFim()
            );

        } else {

            System.out.println("Locação não encontrada.");
        }
    }

    // REMOVER
    private static void remover() {

        int id =
                lerInteiro("Digite o ID da locação: ");

        boolean removeu =
                locacaoRepositorio.remover_Locacao(id);

        if (removeu) {

            System.out.println("Locação removida.");

        } else {

            System.out.println("Locação não encontrada.");
        }
    }

    // ATUALIZAR
    private static void atualizar() {

        int id =
                lerInteiro("Digite o ID da locação: ");

        Locacao locacaoExistente =
                locacaoRepositorio.buscarPorId(id);

        if (locacaoExistente == null) {

            System.out.println("Locação não encontrada.");
            return;
        }

        int idCliente =
                lerInteiro("Novo ID do cliente: ");

        Cliente cliente =
                clienteRepositorio.buscarPorId(idCliente);

        if (cliente == null) {

            System.out.println("Cliente não encontrado.");
            return;
        }

        int idCarro =
                lerInteiro("Novo ID do carro: ");

        Carro carro =
                carroRepositorio.buscarPorId(idCarro);

        if (carro == null) {

            System.out.println("Carro não encontrado.");
            return;
        }

        int idFuncionario =
                lerInteiro("Novo ID do funcionário: ");

        Funcionario funcionario =
                funcionarioRepositorio.buscarPorId(idFuncionario);

        if (funcionario == null) {

            System.out.println("Funcionário não encontrado.");
            return;
        }

        LocalDate dataInicio =
                lerData("Nova data de início (AAAA-MM-DD): ");

        LocalDate dataFim =
                lerData("Nova data de fim (AAAA-MM-DD): ");

        Locacao novaLocacao =
                new Locacao(
                        id,
                        cliente,
                        carro,
                        dataInicio,
                        dataFim,
                        funcionario
                );

        boolean atualizou =
                locacaoRepositorio.atualizar_Locacao(
                        id,
                        novaLocacao
                );

        if (atualizou) {

            System.out.println("Locação atualizada.");

        } else {

            System.out.println(
                    "Não foi possível atualizar a locação."
            );
        }
    }

    // LER INTEIRO
    private static int lerInteiro(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            String entrada =
                    scanner.nextLine();

            try {

                return Integer.parseInt(entrada);

            } catch (NumberFormatException erro) {

                System.out.println(
                        "Digite um número válido."
                );
            }
        }
    }

    // LER DATA
    private static LocalDate lerData(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            String entrada =
                    scanner.nextLine();

            try {

                return LocalDate.parse(entrada);

            } catch (DateTimeParseException erro) {

                System.out.println(
                        "Data inválida. Use o formato AAAA-MM-DD."
                );
            }
        }
    }
}