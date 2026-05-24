package Repositorio;

import Modelo.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepositorio {

    private static FuncionarioRepositorio instancia;
    private final List<Funcionario> funcionariosRepositorio = new ArrayList<>();
    private int proximoId = 1;

    // Construtor privado (Singleton)
    private FuncionarioRepositorio() {
    }

    // Instância única
    public static FuncionarioRepositorio getInstanciaFuncionario() {

        if (instancia == null) {
            instancia = new FuncionarioRepositorio();
        }

        return instancia;
    }

    // Adicionar
    public boolean adicionar_Funcionario(Funcionario funcionario) {

        if (funcionario == null ||
                buscarPorCpf(funcionario.getCpf()) != null ||
                buscarPorMatricula(funcionario.getMatricula()) != null) {

            return false;
        }

        funcionario.setId(proximoId);
        proximoId++;

        funcionariosRepositorio.add(funcionario);
        return true;
    }

    // Listar
    public List<Funcionario> listar_Funcionarios() {
        return new ArrayList<>(funcionariosRepositorio);
    }

    // Buscar por ID
    public Funcionario buscarPorId(int id) {

        for (Funcionario funcionario : funcionariosRepositorio) {

            if (funcionario.getId() == id) {
                return funcionario;
            }
        }

        return null;
    }

    // Buscar por CPF
    public Funcionario buscarPorCpf(String cpf) {

        if (cpf == null) {
            return null;
        }

        for (Funcionario funcionario : funcionariosRepositorio) {

            if (funcionario.getCpf() != null &&
                    funcionario.getCpf().equalsIgnoreCase(cpf)) {

                return funcionario;
            }
        }

        return null;
    }

    // Buscar por Matrícula
    public Funcionario buscarPorMatricula(String matricula) {

        if (matricula == null) {
            return null;
        }

        for (Funcionario funcionario : funcionariosRepositorio) {

            if (funcionario.getMatricula() != null &&
                    funcionario.getMatricula().equalsIgnoreCase(matricula)) {

                return funcionario;
            }
        }

        return null;
    }

    // Remover
    public boolean remover_Funcionario(int id) {

        Funcionario funcionario = buscarPorId(id);

        if (funcionario != null) {
            funcionariosRepositorio.remove(funcionario);
            return true;
        }

        return false;
    }

    // Atualizar
    public boolean atualizar_Funcionario(int id, Funcionario novoFuncionario) {

        if (novoFuncionario == null) {
            return false;
        }

        for (int i = 0; i < funcionariosRepositorio.size(); i++) {

            Funcionario funcionarioAtual = funcionariosRepositorio.get(i);

            if (funcionarioAtual.getId() == id) {

                // Verifica CPF duplicado
                Funcionario funcionarioMesmoCpf =
                        buscarPorCpf(novoFuncionario.getCpf());

                if (funcionarioMesmoCpf != null &&
                        funcionarioMesmoCpf.getId() != id) {

                    return false;
                }

                // Verifica matrícula duplicada
                Funcionario funcionarioMesmaMatricula =
                        buscarPorMatricula(novoFuncionario.getMatricula());

                if (funcionarioMesmaMatricula != null &&
                        funcionarioMesmaMatricula.getId() != id) {

                    return false;
                }

                novoFuncionario.setId(id);
                funcionariosRepositorio.set(i, novoFuncionario);

                return true;
            }
        }

        return false;
    }

    // Métodos auxiliares
    public boolean adicionar(Funcionario funcionario) {
        return adicionar_Funcionario(funcionario);
    }

    public boolean remover(Funcionario funcionario) {

        if (funcionario == null) {
            return false;
        }

        return remover_Funcionario(funcionario.getId());
    }
}