package Repositorio;

import Modelo.Carro;

import java.util.ArrayList;
import java.util.List;

public class CarroRepositorio {

    private static CarroRepositorio instancia;
    private final List<Carro> CarrosRepositorio = new ArrayList<>();
    private int proximoId = 1;

    //Construtor
    private CarroRepositorio() {
    }

    public static CarroRepositorio getInstanciaCarro() {

        if (instancia == null) {
            instancia = new CarroRepositorio();
        }
        return instancia;
    }

    // Adicionar
    public boolean adicionar_Carros(Carro carro) {

        if (carro == null || buscarPorPlaca(carro.getPlaca()) != null) {
            return false;
        }

        carro.setId(proximoId);
        proximoId = proximoId + 1;
        CarrosRepositorio.add(carro);
        return true;
    }

    // Listar
    public List<Carro> listar_Carros() {
        return new ArrayList<>(CarrosRepositorio);
    }

    // Buscar
    public Carro buscarPorId(int id) {

        for (Carro carro : CarrosRepositorio) {

            if (carro.getId() == id) {
                return carro;
            }
        }

        return null;
    }

    public Carro buscarPorPlaca(String placa) {

        if (placa == null) {
            return null;
        }

        for (Carro carro : CarrosRepositorio) {

            if (carro.getPlaca() != null && carro.getPlaca().equalsIgnoreCase(placa)) {
                return carro;
            }
        }

        return null;
    }

    // Remover
    public boolean remover_Carros(int id) {

        Carro carro = buscarPorId(id);

        if (carro != null) {
            CarrosRepositorio.remove(carro);
            return true;
        }

        return false;
    }

    // Atualizar
    public boolean atualizar_Carros(int id, Carro novoCarro) {

        if (novoCarro == null) {
            return false;
        }

        for (int i = 0; i < CarrosRepositorio.size(); i++) {

            Carro carroAtual = CarrosRepositorio.get(i);

            if (carroAtual.getId() == id) {

                Carro carroComMesmaPlaca = buscarPorPlaca(novoCarro.getPlaca());

                if (carroComMesmaPlaca != null && carroComMesmaPlaca.getId() != id) {
                    return false;
                }

                novoCarro.setId(id);
                CarrosRepositorio.set(i, novoCarro);
                return true;
            }
        }

        return false;
    }
}
