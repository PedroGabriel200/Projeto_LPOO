package Interface;
import java.util.Scanner;
import Modelo.Cliente;
import Servicos.ClienteServicos;



public class ClienteMenu {
    ClienteServicos service = new ClienteServicos();
    Scanner sc = new Scanner(System.in);

    public void menu() {
        int opcao = -1;

        while (opcao !=0)
        {
        System.out.println("Escolha uma opção");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Listar Clientes");
        System.out.println("3 - Excluir Cliente");
        System.out.println("0 - Sair");
        opcao = sc.nextInt();
        sc.nextLine();

        if (opcao == 1) {
            cadastrarCliente();
        }

        if (opcao == 2) {
            listarClientes();
        }

        if (opcao == 3) {
            excluirCliente();
        }


        }

    }

    public void listarClientes() {
        for (Cliente c : service.listar()) {
            System.out.println(c);
        }
    }

    public void cadastrarCliente(){
        System.out.print("Digite o CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Digite o nome: ");
        String nome = sc.nextLine();

        System.out.print("Digite a CNH: ");
        String cnh = sc.nextLine();

        service.cadastrar(cpf, nome, cnh);
        System.out.println("Cliente cadastrado!");
    }

    public void excluirCliente(){
        System.out.print("Digite o ID do cliente que deseja excluir:");
        int id = sc.nextInt();
        sc.nextLine();

        boolean removido = service.excluir(id);

        if(removido) {
            System.out.println("Cliente removido com sucesso!");
        } else{
            System.out.println("Cliente não encontrado!");
        }
    }

}
