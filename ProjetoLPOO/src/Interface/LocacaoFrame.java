package Interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LocacaoFrame extends JFrame {

    private JTextField campoCliente;
    private JTextField campoCarro;
    private JTextField campoFuncionario;
    private JTextField campoDataInicio;
    private JTextField campoDataFim;

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public LocacaoFrame() {
        setTitle("Cadastro de Locações");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel painelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        campoCliente = new JTextField();
        campoCarro = new JTextField();
        campoFuncionario = new JTextField();
        campoDataInicio = new JTextField();
        campoDataFim = new JTextField();

        painelFormulario.add(new JLabel("Cliente:"));
        painelFormulario.add(campoCliente);

        painelFormulario.add(new JLabel("Carro:"));
        painelFormulario.add(campoCarro);

        painelFormulario.add(new JLabel("Funcionário:"));
        painelFormulario.add(campoFuncionario);

        painelFormulario.add(new JLabel("Data Início:"));
        painelFormulario.add(campoDataInicio);

        painelFormulario.add(new JLabel("Data Fim:"));
        painelFormulario.add(campoDataFim);

        JButton btnCadastrar = new JButton("Cadastrar Locação");
        JButton btnExcluir = new JButton("Excluir Selecionada");

        painelFormulario.add(btnCadastrar);
        painelFormulario.add(btnExcluir);

        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Cliente");
        modeloTabela.addColumn("Carro");
        modeloTabela.addColumn("Funcionário");
        modeloTabela.addColumn("Data Início");
        modeloTabela.addColumn("Data Fim");

        tabela = new JTable(modeloTabela);

        add(painelFormulario, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        btnCadastrar.addActionListener(e -> cadastrarLocacao());
        btnExcluir.addActionListener(e -> excluirLocacao());

        setVisible(true);
    }

    private void cadastrarLocacao() {
        String cliente = campoCliente.getText();
        String carro = campoCarro.getText();
        String funcionario = campoFuncionario.getText();
        String dataInicio = campoDataInicio.getText();
        String dataFim = campoDataFim.getText();

        if (cliente.isEmpty() || carro.isEmpty() || funcionario.isEmpty()
                || dataInicio.isEmpty() || dataFim.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        modeloTabela.addRow(new Object[]{
                cliente,
                carro,
                funcionario,
                dataInicio,
                dataFim
        });

        limparCampos();
    }

    private void excluirLocacao() {
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma locação para excluir.");
            return;
        }

        modeloTabela.removeRow(linha);
    }

    private void limparCampos() {
        campoCliente.setText("");
        campoCarro.setText("");
        campoFuncionario.setText("");
        campoDataInicio.setText("");
        campoDataFim.setText("");
    }
}