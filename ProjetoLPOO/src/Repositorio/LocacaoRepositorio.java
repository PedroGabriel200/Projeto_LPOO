package Repositorio;

import Modelo.Locacao;
import java.util.ArrayList;
import java.util.List;

public class LocacaoRepositorio {

    // Lista que simula o banco de dados em memória
    private final List<Locacao> locacoes;

    public LocacaoRepositorio() {
        this.locacoes = new ArrayList<>();
    }

    // Método para adicionar locação
    public void adicionar(Locacao locacao) {
        this.locacoes.add(locacao);

    }

    // Método para listar todas as locações
    public List<Locacao> listar(){
        return this.locacoes;
    }
    // Método para remover locação
    public void remover(Locacao locacao){
        this.locacoes.remove(locacao);

    }

}
