package Servicos;

import Repositorio.ClienteRepositorio;
import Modelo.Cliente;

public class ClienteServicos {
    ClienteRepositorio repositorio = new ClienteRepositorio();
    int proximoId = 1;


    public void cadastrar(String cpf, String nome, String cnh) {
        Cliente cliente = new Cliente(proximoId, cpf, nome, cnh);
        repositorio.adicionar(cliente);
        proximoId = proximoId + 1;
    }

    public java.util.ArrayList<Cliente> listar() {
        return repositorio.listar();
    }

    public boolean excluir(int id) {
        for (Cliente cliente : repositorio.listar()) {
            if (cliente.getId() == id) {
                repositorio.remover(cliente);
                return true;
            }
        }

        return false;
    }

}