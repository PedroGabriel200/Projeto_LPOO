package Repositorio;

import Modelo.Carro;
import Modelo.Cliente;
import Modelo.Funcionario;
import Modelo.Locacao;

import java.util.ArrayList;
import java.util.List;

public class LocacaoRepositorio {

    private static LocacaoRepositorio instancia;

    private final List<Locacao> locacoesRepositorio =
            new ArrayList<>();

    private int proximoId = 1;

    // Construtor privado
    private LocacaoRepositorio() {
    }

    // Singleton
    public static LocacaoRepositorio getInstanciaLocacao() {

        if (instancia == null) {
            instancia = new LocacaoRepositorio();
        }

        return instancia;
    }

    // ADICIONAR
    public boolean adicionar_Locacao(Locacao locacao) {

        if (locacao == null) {
            return false;
        }

        Cliente cliente = locacao.getCliente();
        Carro carro = locacao.getCarro();
        Funcionario funcionario = locacao.getFuncionario();

        // Validações
        if (cliente == null ||
                carro == null ||
                funcionario == null) {

            return false;
        }

        // Verifica disponibilidade do carro
        if (!carro.isDisponivel()) {
            return false;
        }

        // Define ID
        locacao.setId(proximoId);
        proximoId++;

        // Torna carro indisponível
        carro.setDisponivel(false);

        // Salva locação
        locacoesRepositorio.add(locacao);

        return true;
    }

    // LISTAR
    public List<Locacao> listar_Locacoes() {

        return new ArrayList<>(locacoesRepositorio);
    }

    // BUSCAR POR ID
    public Locacao buscarPorId(int id) {

        for (Locacao locacao : locacoesRepositorio) {

            if (locacao.getId() == id) {
                return locacao;
            }
        }

        return null;
    }

    // REMOVER
    public boolean remover_Locacao(int id) {

        Locacao locacao =
                buscarPorId(id);

        if (locacao == null) {
            return false;
        }

        // Libera o carro
        Carro carro = locacao.getCarro();

        if (carro != null) {
            carro.setDisponivel(true);
        }

        // Remove locação
        locacoesRepositorio.remove(locacao);

        return true;
    }

    // ATUALIZAR
    public boolean atualizar_Locacao(
            int id,
            Locacao novaLocacao
    ) {

        if (novaLocacao == null) {
            return false;
        }

        for (int i = 0;
             i < locacoesRepositorio.size();
             i++) {

            Locacao locacaoAtual =
                    locacoesRepositorio.get(i);

            if (locacaoAtual.getId() == id) {

                Carro carroAtual =
                        locacaoAtual.getCarro();

                Carro novoCarro =
                        novaLocacao.getCarro();

                // Se mudou de carro
                if (carroAtual != novoCarro) {

                    // Libera carro antigo
                    if (carroAtual != null) {
                        carroAtual.setDisponivel(true);
                    }

                    // Verifica disponibilidade do novo carro
                    if (novoCarro == null ||
                            !novoCarro.isDisponivel()) {

                        // Restaura carro antigo
                        if (carroAtual != null) {
                            carroAtual.setDisponivel(false);
                        }

                        return false;
                    }

                    // Ocupa novo carro
                    novoCarro.setDisponivel(false);
                }

                novaLocacao.setId(id);

                locacoesRepositorio.set(
                        i,
                        novaLocacao
                );

                return true;
            }
        }

        return false;
    }

    // Métodos auxiliares
    public boolean adicionar(Locacao locacao) {

        return adicionar_Locacao(locacao);
    }

    public boolean remover(Locacao locacao) {

        if (locacao == null) {
            return false;
        }

        return remover_Locacao(
                locacao.getId()
        );
    }
}