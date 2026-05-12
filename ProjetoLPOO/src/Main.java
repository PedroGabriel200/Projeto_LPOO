import Interface.ClienteMenu;
import Repositorio.LocacaoRepositorio;
import Repositorio.CarroRepositorio;


public class Main {

    public static void main(String[] args) {

        ClienteMenu clienteMenu = new ClienteMenu();
        clienteMenu.menu();

        LocacaoRepositorio repo = new LocacaoRepositorio();
        CarroRepositorio carro = new CarroRepositorio();
    }
}