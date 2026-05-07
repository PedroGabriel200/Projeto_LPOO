package Repositorio;

import Modelo.Carro;

import java.util.ArrayList;
import java.util.List;


public class CarroRepositorio {
    private final List<Carro> carros = new ArrayList<>();

    // Adicionar carro
    public void adicionarcarro(Carro carro) {
        carros.add(carro);
    }

    // Listar todos os carros
    public List<Carro> listarCarros() {
        return carros;
    }

    // Buscar carro pela placa
    public Carro buscarPorPlaca(String placa) {
        for (Carro carro : carros) {

            if (carro.getPlaca().equals(placa)) {
                return carro;
            }
        }
        return null;
    }

    // Remover carro
    public boolean removerCarro(String placa) {
        Carro carro = buscarPorPlaca(placa);

        if (carro != null) {
            carros.remove(carro);
            return true;
        }
        return false;
    }
}
