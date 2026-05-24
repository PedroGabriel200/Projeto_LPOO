package Repositorio;

import Modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepositorio {

    private static ClienteRepositorio instancia;
    private final List<Cliente> ClientesRepositorio = new ArrayList<>();
    private int proximoId = 1;

    //Construtor
    private ClienteRepositorio() {
    }

    public static ClienteRepositorio getInstanciaCliente() {

        if (instancia == null) {
            instancia = new ClienteRepositorio();
        }
        return instancia;
    }

    // Adicionar
    public boolean adicionar_Clientes(Cliente cliente) {

        if (cliente == null || buscarPorCpf(cliente.getCpf()) != null) {
            return false;
        }

        cliente.setId(proximoId);
        proximoId = proximoId + 1;
        ClientesRepositorio.add(cliente);
        return true;
    }

    // Listar
    public List<Cliente> listar_Clientes() {
        return new ArrayList<>(ClientesRepositorio);
    }

    // Buscar
    public Cliente buscarPorId(int id) {

        for (Cliente cliente : ClientesRepositorio) {

            if (cliente.getId() == id) {
                return cliente;
            }
        }

        return null;
    }

    public Cliente buscarPorCpf(String cpf) {

        if (cpf == null) {
            return null;
        }

        for (Cliente cliente : ClientesRepositorio) {

            if (cliente.getCpf() != null && cliente.getCpf().equalsIgnoreCase(cpf)) {
                return cliente;
            }
        }

        return null;
    }

    // Remover
    public boolean remover_Clientes(int id) {

        Cliente cliente = buscarPorId(id);

        if (cliente != null) {
            ClientesRepositorio.remove(cliente);
            return true;
        }

        return false;
    }

    // Atualizar
    public boolean atualizar_Clientes(int id, Cliente novoCliente) {

        if (novoCliente == null) {
            return false;
        }

        for (int i = 0; i < ClientesRepositorio.size(); i++) {

            Cliente clienteAtual = ClientesRepositorio.get(i);

            if (clienteAtual.getId() == id) {

                Cliente clienteComMesmoCpf = buscarPorCpf(novoCliente.getCpf());

                if (clienteComMesmoCpf != null && clienteComMesmoCpf.getId() != id) {
                    return false;
                }

                novoCliente.setId(id);
                ClientesRepositorio.set(i, novoCliente);
                return true;
            }
        }

        return false;
    }

    public boolean adicionar(Cliente cliente) {
        return adicionar_Clientes(cliente);
    }

    public boolean remover(Cliente cliente) {

        if (cliente == null) {
            return false;
        }

        return remover_Clientes(cliente.getId());
    }
}
