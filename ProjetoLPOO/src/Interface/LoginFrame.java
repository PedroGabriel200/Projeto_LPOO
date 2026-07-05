package Interface;

import Modelo.Funcionario;
import Repositorio.FuncionarioRepositorio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtMatricula;
    private JPasswordField txtSenha;
    private JButton btnEntrar;

    private final FuncionarioRepositorio repositorio =
            FuncionarioRepositorio.getInstanciaFuncionario();

    public LoginFrame() {

        // Se não existir funcionário cadastrado,
        // abre a tela de cadastro do primeiro funcionário
        if (!repositorio.existeFuncionario()) {
            SwingUtilities.invokeLater(() -> {
                new CadastroPrimeiroFuncionarioFrame().setVisible(true);
            });
            dispose();
            return;
        }

        initComponents();
    }

    private void initComponents() {

        setTitle("Sistema de Locadora");
        setSize(550,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Color fundo = new Color(245, 245, 245);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setBackground(fundo);
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(new EmptyBorder(30, 30, 30, 30));

        // ======================
        // ESPAÇO PARA SUA LOGO
        // ======================

        JLabel lblLogo = new JLabel("SUA LOGO");
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblEmpresa = new JLabel("Sistema de Locadora");
        lblEmpresa.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblEmpresa.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelPrincipal.add(Box.createVerticalStrut(20));
        painelPrincipal.add(lblLogo);
        painelPrincipal.add(lblEmpresa);
        painelPrincipal.add(Box.createVerticalStrut(60));

        JPanel card = new JPanel(new GridBagLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        card.setBackground(Color.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;

        JLabel titulo = new JLabel("Informe sua matrícula e senha");
        titulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        card.add(titulo, c);

        c.gridy++;

        txtMatricula = new JTextField(20);
        txtMatricula.setPreferredSize(new Dimension(250, 35));
        txtMatricula.setBorder(BorderFactory.createTitledBorder("Matrícula"));
        card.add(txtMatricula, c);

        c.gridy++;

        txtSenha = new JPasswordField(20);
        txtSenha.setPreferredSize(new Dimension(250, 35));
        txtSenha.setBorder(BorderFactory.createTitledBorder("Senha"));
        card.add(txtSenha, c);

        c.gridy++;

        btnEntrar = new JButton("Entrar");
        btnEntrar.setBackground(new Color(0, 123, 255));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFocusPainted(false);

        card.add(btnEntrar, c);

        painelPrincipal.add(card);

        add(painelPrincipal);

        btnEntrar.addActionListener(e -> realizarLogin());

        getRootPane().setDefaultButton(btnEntrar);
    }

    private void realizarLogin() {

        String matricula = txtMatricula.getText().trim();
        String senha = new String(txtSenha.getPassword());

        if (matricula.isEmpty() || senha.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Informe a matrícula e a senha.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        Funcionario funcionario = repositorio.autenticar(matricula, senha);

        if (funcionario != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login realizado com sucesso!");

            dispose();

            new MainMenu().setVisible(true);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Matrícula ou senha inválidas.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);

            txtSenha.setText("");
            txtSenha.requestFocus();
        }
    }
}