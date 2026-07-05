package Interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ClienteFrame extends JFrame {

    private JTextField campoNome;
    private JTextField campoCpf;
    private JTextField campoCnh;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public ClienteFrame() {
        setTitle("Cadastro de Clientes");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        campoNome = new JTextField();
        campoCpf = new JTextField();
        campoCnh = new JTextField();

        painelFormulario.add(new JLabel("Nome:"));
        painelFormulario.add(campoNome);

        painelFormulario.add(new JLabel("CPF:"));
        painelFormulario.add(campoCpf);

        painelFormulario.add(new JLabel("CNH:"));
        painelFormulario.add(campoCnh);

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnExcluir = new JButton("Excluir Selecionado");

        painelFormulario.add(btnCadastrar);
        painelFormulario.add(btnExcluir);

        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("CPF");
        modeloTabela.addColumn("CNH");

        tabela = new JTable(modeloTabela);

        add(painelFormulario, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        btnCadastrar.addActionListener(e -> cadastrarCliente());
        btnExcluir.addActionListener(e -> excluirCliente());

        setVisible(true);
    }

    private void cadastrarCliente() {
        String nome = campoNome.getText();
        String cpf = campoCpf.getText();
        String cnh = campoCnh.getText();

        if (nome.isEmpty() || cpf.isEmpty() || cnh.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        modeloTabela.addRow(new Object[]{nome, cpf, cnh});

        limparCampos();
    }

    private void excluirCliente() {
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.");
            return;
        }

        modeloTabela.removeRow(linha);
    }

    private void limparCampos() {
        campoNome.setText("");
        campoCpf.setText("");
        campoCnh.setText("");
    }
}