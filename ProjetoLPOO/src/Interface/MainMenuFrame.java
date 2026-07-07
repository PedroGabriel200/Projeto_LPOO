package Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {

        setTitle("Sistema de Locadora");
        setSize(600,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Color fundo = new Color(245,245,245);

        JPanel principal = new JPanel();
        principal.setBackground(fundo);
        principal.setLayout(new BoxLayout(principal, BoxLayout.Y_AXIS));
        principal.setBorder(new EmptyBorder(25,25,25,25));

        // ========= LOGO =========

        ImageIcon icon = new ImageIcon(
                getClass().getResource("/imagens/logo.png"));

        Image imagem = icon.getImage().getScaledInstance(
                180,
                180,
                Image.SCALE_SMOOTH);

        JLabel logo = new JLabel(new ImageIcon(imagem));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titulo = new JLabel("Sistema de Locadora");
        titulo.setFont(new Font("Segoe UI",Font.BOLD,28));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Menu Principal");
        subtitulo.setFont(new Font("Segoe UI",Font.PLAIN,16));
        subtitulo.setForeground(Color.GRAY);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        principal.add(Box.createVerticalStrut(10));
        principal.add(logo);
        principal.add(Box.createVerticalStrut(10));
        principal.add(titulo);
        principal.add(subtitulo);
        principal.add(Box.createVerticalStrut(35));

        // ========= BOTÕES =========

        JButton carro = criarBotao("🚗  Carros");
        JButton cliente = criarBotao("👤  Clientes");
        JButton funcionario = criarBotao("👔  Funcionários");
        JButton locacao = criarBotao("📋  Locações");
        JButton sair = criarBotaoVermelho("Sair");

        principal.add(carro);
        principal.add(Box.createVerticalStrut(15));

        principal.add(cliente);
        principal.add(Box.createVerticalStrut(15));

        principal.add(funcionario);
        principal.add(Box.createVerticalStrut(15));

        principal.add(locacao);
        principal.add(Box.createVerticalStrut(30));

        principal.add(sair);

        add(principal);

        // AÇÕES

        carro.addActionListener(e -> {
            dispose();
            new CarroFrame().setVisible(true);
        });

        cliente.addActionListener(e -> {
            dispose();
            new ClienteFrame().setVisible(true);
        });

        funcionario.addActionListener(e -> {
            dispose();
            new FuncionarioFrame().setVisible(true);
        });

        locacao.addActionListener(e -> {
            dispose();
            new LocacaoFrame().setVisible(true);
        });

        sair.addActionListener(e -> {

            int resposta = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente sair do sistema?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION);

            if(resposta == JOptionPane.YES_OPTION){
                System.exit(0);
            }

        });

        setVisible(true);
    }

    private JButton criarBotao(String texto){

        JButton botao = new JButton(texto);

        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setMaximumSize(new Dimension(320,50));
        botao.setPreferredSize(new Dimension(320,50));

        botao.setFont(new Font("Segoe UI",Font.BOLD,16));

        botao.setBackground(new Color(0,123,255));
        botao.setForeground(Color.WHITE);

        botao.setFocusPainted(false);

        return botao;
    }

    private JButton criarBotaoVermelho(String texto){

        JButton botao = criarBotao(texto);

        botao.setBackground(new Color(220,53,69));

        return botao;
    }

}