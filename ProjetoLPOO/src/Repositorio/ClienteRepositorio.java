package Repositorio;
import Modelo.Cliente;
import java.util.ArrayList;

public class ClienteRepositorio {

    ArrayList<Cliente> clientes = new ArrayList<>();

    public void adicionar(Cliente cliente){
        clientes.add(cliente);
    }

    public ArrayList<Cliente> listar(){
        return clientes;
    }

    public void remover(Cliente cliente){
        clientes.remove(cliente);
    }

}
