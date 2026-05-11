package Repositorio;

import Modelo.Locacao;
import java.util.ArrayList;
import java.util.List;

public class LocacaoRepositorio {

    // Lista que simula o banco de dados em memória
    private List<Locacao> locacoes;

    public LocacaoRepositorio() {
        this.locacoes = new ArrayList<>();
    }
}
