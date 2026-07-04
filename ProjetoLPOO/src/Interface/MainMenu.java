package Interface;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu(){

        setTitle("Menu Principal");
        setSize(550,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel painel = new JPanel(new GridLayout(5,1,10,10));

        painel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JButton carro = new JButton("Menu Carros");
        JButton cliente = new JButton("Menu Clientes");
        JButton funcionario = new JButton("Menu Funcionários");
        JButton locacao = new JButton("Menu Locações");
        JButton sair = new JButton("Sair");

        painel.add(carro);
        painel.add(cliente);
        painel.add(funcionario);
        painel.add(locacao);
        painel.add(sair);

        add(painel);

        sair.addActionListener(e -> System.exit(0));

        carro.addActionListener(e -> CarroMenu.exibir());
        cliente.addActionListener(e -> ClienteMenu.exibir());
        funcionario.addActionListener(e -> FuncionarioMenu.exibir());
        locacao.addActionListener(e -> LocacaoMenu.exibir());

    }

}