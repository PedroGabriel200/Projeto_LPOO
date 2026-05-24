package Repositorio;

import Modelo.Carro;

import java.util.ArrayList;
import java.util.List;

public class CarroRepositorio {

    private static CarroRepositorio instancia;
    private final List<Carro> carrosRepositor = new ArrayList<>();

    //Construtor
    private CarroRepositorio() {
    }

    public static CarroRepositorio getInstancia() {

        if (instancia == null) {
            instancia = new CarroRepositorio();
        }
        return instancia;
    }

    // Adicionar
    public boolean adicionar_Carros(Carro carro) {

        if (buscarPorPlaca(carro.getPlaca()) != null) {
            return false;
        }

        carrosRepositor.add(carro);
        return true;
    }

    // Listar
    public List<Carro> listar_Carros() {
        return new ArrayList<>(carrosRepositor);
    }

    // Buscar
    public Carro buscarPorPlaca(String placa) {

        for (Carro carro : carrosRepositor) {

            if (carro.getPlaca().equalsIgnoreCase(placa)) {
                return carro;
            }
        }

        return null;
    }

    // Remover
    public boolean remover_Carros(String placa) {

        Carro carro = buscarPorPlaca(placa);

        if (carro != null) {
            carrosRepositor.remove(carro);
            return true;
        }

        return false;
    }

    // Atualizar
    public boolean atualizar_Carros(String placa, Carro novoCarro) {

        for (int i = 0; i < carrosRepositor.size(); i++) {

            if (carrosRepositor.get(i).getPlaca().equalsIgnoreCase(placa)) {

                carrosRepositor.set(i, novoCarro);
                return true;
            }
        }

        return false;
    }
}