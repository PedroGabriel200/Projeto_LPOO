import Interface.ClienteMenu;
import Repositorio.LocacaoRepositorio;

public class Main {

    public static void main(String[] args) {

        ClienteMenu clienteMenu = new ClienteMenu();
        clienteMenu.menu();

        LocacaoRepositorio repo = new LocacaoRepositorio();
    }
}