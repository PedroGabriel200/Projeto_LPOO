package Interface;

import Modelo.Funcionario;
import Repositorio.FuncionarioRepositorio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CadastroPrimeiroFuncionarioFrame extends JFrame {

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtMatricula;
    private JTextField txtSalario;
    private JPasswordField txtSenha;

    private JButton btnCadastrar;

    private final FuncionarioRepositorio repositorio =
            FuncionarioRepositorio.getInstanciaFuncionario();

    public CadastroPrimeiroFuncionarioFrame() {

        setTitle("Primeiro Acesso");
        setSize(700,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Color fundo = new Color(245,245,245);

        JPanel principal = new JPanel();
        principal.setBackground(fundo);
        principal.setLayout(new BoxLayout(principal, BoxLayout.Y_AXIS));
        principal.setBorder(new EmptyBorder(20,20,20,20));

        // ================= LOGO =================

        ImageIcon icone = new ImageIcon(
                getClass().getResource("/imagens/logo.png"));

        Image imagem = icone.getImage().getScaledInstance(
                180,
                180,
                Image.SCALE_SMOOTH);

        JLabel logo = new JLabel(new ImageIcon(imagem));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titulo = new JLabel("Primeiro Acesso");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Segoe UI",Font.BOLD,28));

        JLabel subtitulo = new JLabel("Cadastre o administrador do sistema");
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setFont(new Font("Segoe UI",Font.PLAIN,16));
        subtitulo.setForeground(Color.GRAY);

        principal.add(logo);
        principal.add(Box.createVerticalStrut(10));
        principal.add(titulo);
        principal.add(Box.createVerticalStrut(5));
        principal.add(subtitulo);
        principal.add(Box.createVerticalStrut(25));

        // ================= CARD =================

        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);

        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220,220,220)),
                new EmptyBorder(20,20,20,20)
        ));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;

        Font fonte = new Font("Segoe UI",Font.PLAIN,15);

        txtNome = new JTextField();
        txtCpf = new JTextField();
        txtMatricula = new JTextField();
        txtSalario = new JTextField();
        txtSenha = new JPasswordField();

        configurarCampo(txtNome,"Nome",fonte);
        configurarCampo(txtCpf,"CPF",fonte);
        configurarCampo(txtMatricula,"Matrícula",fonte);
        configurarCampo(txtSalario,"Salário",fonte);
        configurarCampo(txtSenha,"Senha",fonte);

        // Linha 1

        c.gridx = 0;
        c.gridy = 0;
        card.add(txtNome,c);

        c.gridx = 1;
        card.add(txtCpf,c);

        // Linha 2

        c.gridx = 0;
        c.gridy = 1;
        card.add(txtMatricula,c);

        c.gridx = 1;
        card.add(txtSalario,c);

        // Linha 3

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        card.add(txtSenha,c);

        // Botão

        c.gridy = 3;

        btnCadastrar = new JButton("Cadastrar Funcionário");
        btnCadastrar.setFont(new Font("Segoe UI",Font.BOLD,16));
        btnCadastrar.setBackground(new Color(0,123,255));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFocusPainted(false);
        btnCadastrar.setPreferredSize(new Dimension(300,45));

        card.add(btnCadastrar,c);

        principal.add(card);

        add(principal);

        btnCadastrar.addActionListener(e -> cadastrarFuncionario());
    }

    private void configurarCampo(JTextField campo,
                                 String titulo,
                                 Font fonte){

        campo.setFont(fonte);
        campo.setPreferredSize(new Dimension(250,45));
        campo.setBorder(BorderFactory.createTitledBorder(titulo));
    }

    private void cadastrarFuncionario() {

        try {

            String nome = txtNome.getText().trim();
            String cpf = txtCpf.getText().trim();
            String matricula = txtMatricula.getText().trim();
            String senha = new String(txtSenha.getPassword()).trim();

            double salario = Double.parseDouble(
                    txtSalario.getText().replace(",", "."));

            if(nome.isEmpty() ||
                    cpf.isEmpty() ||
                    matricula.isEmpty() ||
                    senha.isEmpty()){

                JOptionPane.showMessageDialog(
                        this,
                        "Preencha todos os campos."
                );
                return;
            }

            Funcionario funcionario = new Funcionario(
                    0,
                    cpf,
                    nome,
                    matricula,
                    salario,
                    senha
            );

            if(repositorio.adicionar(funcionario)){

                JOptionPane.showMessageDialog(
                        this,
                        "Primeiro funcionário cadastrado com sucesso!"
                );

                dispose();
                new LoginFrame().setVisible(true);

            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Erro ao cadastrar funcionário.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }

        }catch(NumberFormatException ex){

            JOptionPane.showMessageDialog(
                    this,
                    "Informe um salário válido.");
        }
    }

    private void configurarCampo(JTextField campo,
                                 String titulo,
                                 Font fonte,
                                 boolean dummy){}

}