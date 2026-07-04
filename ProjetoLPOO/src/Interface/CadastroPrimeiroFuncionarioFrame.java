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
        setSize(550,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel principal = new JPanel();
        principal.setBackground(new Color(245,245,245));
        principal.setLayout(new BoxLayout(principal, BoxLayout.Y_AXIS));
        principal.setBorder(new EmptyBorder(30,30,30,30));

        JLabel logo = new JLabel("SUA LOGO");
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 34));

        JLabel titulo = new JLabel("Primeiro acesso ao sistema");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        JLabel subtitulo = new JLabel("Cadastre o primeiro funcionário");
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        principal.add(logo);
        principal.add(Box.createVerticalStrut(10));
        principal.add(titulo);
        principal.add(subtitulo);
        principal.add(Box.createVerticalStrut(30));

        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8,8,8,8);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;

        txtNome = new JTextField(20);
        txtCpf = new JTextField(20);
        txtMatricula = new JTextField(20);
        txtSalario = new JTextField(20);
        txtSenha = new JPasswordField(20);

        card.add(new JLabel("Nome"), c);

        c.gridy++;
        card.add(txtNome, c);

        c.gridy++;
        card.add(new JLabel("CPF"), c);

        c.gridy++;
        card.add(txtCpf, c);

        c.gridy++;
        card.add(new JLabel("Matrícula"), c);

        c.gridy++;
        card.add(txtMatricula, c);

        c.gridy++;
        card.add(new JLabel("Salário"), c);

        c.gridy++;
        card.add(txtSalario, c);

        c.gridy++;
        card.add(new JLabel("Senha"), c);

        c.gridy++;
        card.add(txtSenha, c);

        c.gridy++;

        btnCadastrar = new JButton("Cadastrar");

        btnCadastrar.setBackground(new Color(0,123,255));
        btnCadastrar.setForeground(Color.WHITE);

        card.add(btnCadastrar,c);

        principal.add(card);

        add(principal);

        btnCadastrar.addActionListener(e -> cadastrarFuncionario());
    }

    private void cadastrarFuncionario() {

        try {

            String nome = txtNome.getText().trim();
            String cpf = txtCpf.getText().trim();
            String matricula = txtMatricula.getText().trim();
            String senha = new String(txtSenha.getPassword()).trim();

            double salario =
                    Double.parseDouble(txtSalario.getText().replace(",", "."));

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
                        JOptionPane.ERROR_MESSAGE
                );

            }

        }catch(NumberFormatException ex){

            JOptionPane.showMessageDialog(
                    this,
                    "Salário inválido."
            );

        }
    }

}