package Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ClienteFrame extends JFrame {

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtCnh;
    private JTextField txtBuscarId;

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public ClienteFrame() {

        setTitle("Menu Clientes");
        setSize(800, 650); // Ajustado levemente para acomodar melhor os elementos estilizados
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Cores da identidade visual do Login
        Color corFundo = new Color(245, 245, 245);
        Color corPrimaria = new Color(0, 123, 255);
        Font fontePadrao = new Font("Segoe UI", Font.PLAIN, 14);
        Font fonteTitulo = new Font("Segoe UI", Font.BOLD, 16);

        JPanel painelPrincipal = new JPanel(new BorderLayout(15, 15));
        painelPrincipal.setBackground(corFundo);
        painelPrincipal.setBorder(new EmptyBorder(25, 25, 25, 25));

        // ================= FORMULÁRIO (Estilo Card) =================

        JPanel cardFormulario = new JPanel(new GridBagLayout());
        cardFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                new EmptyBorder(15, 15, 15, 15)
        ));
        cardFormulario.setBackground(Color.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        // Título do Card
        JLabel lblTituloForm = new JLabel("Dados do Cliente");
        lblTituloForm.setFont(fonteTitulo);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        cardFormulario.add(lblTituloForm, c);
        c.gridwidth = 1; // Reseta

        // Linha 1: Nome
        c.gridx = 0;
        c.gridy = 1;
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(fontePadrao);
        cardFormulario.add(lblNome, c);

        c.gridx = 1;
        c.weightx = 1.0; // Faz o campo expandir horizontalmente
        txtNome = new JTextField();
        txtNome.setFont(fontePadrao);
        txtNome.setPreferredSize(new Dimension(200, 30));
        cardFormulario.add(txtNome, c);

        // Linha 1: CPF
        c.gridx = 2;
        c.weightx = 0;
        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setFont(fontePadrao);
        cardFormulario.add(lblCpf, c);

        c.gridx = 3;
        c.weightx = 0.5;
        txtCpf = new JTextField();
        txtCpf.setFont(fontePadrao);
        txtCpf.setPreferredSize(new Dimension(150, 30));
        cardFormulario.add(txtCpf, c);

        // Linha 2: CNH
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        JLabel lblCnh = new JLabel("CNH:");
        lblCnh.setFont(fontePadrao);
        cardFormulario.add(lblCnh, c);

        c.gridx = 1;
        c.weightx = 1.0;
        txtCnh = new JTextField();
        txtCnh.setFont(fontePadrao);
        txtCnh.setPreferredSize(new Dimension(200, 30));
        cardFormulario.add(txtCnh, c);

        // Linha 2: ID Busca
        c.gridx = 2;
        c.weightx = 0;
        JLabel lblId = new JLabel("ID (Busca):");
        lblId.setFont(fontePadrao);
        cardFormulario.add(lblId, c);

        c.gridx = 3;
        c.weightx = 0.5;
        txtBuscarId = new JTextField();
        txtBuscarId.setFont(fontePadrao);
        txtBuscarId.setPreferredSize(new Dimension(150, 30));
        cardFormulario.add(txtBuscarId, c);


        // ================= BOTÕES DE AÇÃO =================

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelBotoes.setBackground(corFundo);

        JButton btnAdicionar = criarBotaoEstilizado("Adicionar", corPrimaria, Color.WHITE, fontePadrao);
        JButton btnCarregar = criarBotaoEstilizado("Carregar por ID", Color.DARK_GRAY, Color.WHITE, fontePadrao);
        JButton btnAtualizar = criarBotaoEstilizado("Atualizar", Color.DARK_GRAY, Color.WHITE, fontePadrao);
        JButton btnRemover = criarBotaoEstilizado("Remover", new Color(220, 53, 69), Color.WHITE, fontePadrao); // Vermelho pastel
        JButton btnListar = criarBotaoEstilizado("Listar Todos", Color.DARK_GRAY, Color.WHITE, fontePadrao);

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnCarregar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnListar);


        // ================= TABELA =================

        String[] colunas = {"ID", "Nome", "CPF", "CNH"};

        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela = new JTable(modeloTabela);
        tabela.setFont(fontePadrao);
        tabela.setRowHeight(25);
        tabela.setGridColor(Color.LIGHT_GRAY);

        // Estilizando o cabeçalho da tabela
        JTableHeader cabecalho = tabela.getTableHeader();
        cabecalho.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cabecalho.setBackground(Color.WHITE);
        cabecalho.setForeground(Color.BLACK);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));


        // ================= RODAPÉ =================

        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        painelRodape.setBackground(corFundo);

        JButton btnVoltar = criarBotaoEstilizado("Voltar", Color.WHITE, Color.BLACK, fontePadrao);
        btnVoltar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JButton btnSair = criarBotaoEstilizado("Sair", Color.WHITE, Color.RED, fontePadrao);
        btnSair.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        painelRodape.add(btnVoltar);
        painelRodape.add(btnSair);


        // ================= MONTAGEM DO LAYOUT =================

        JPanel painelTopo = new JPanel(new BorderLayout(0, 10));
        painelTopo.setBackground(corFundo);
        painelTopo.add(cardFormulario, BorderLayout.CENTER);
        painelTopo.add(painelBotoes, BorderLayout.SOUTH);

        painelPrincipal.add(painelTopo, BorderLayout.NORTH);
        painelPrincipal.add(scroll, BorderLayout.CENTER);
        painelPrincipal.add(painelRodape, BorderLayout.SOUTH);

        add(painelPrincipal);

        // ================= AÇÕES =================

        btnAdicionar.addActionListener(e -> cadastrarCliente());
        btnRemover.addActionListener(e -> excluirCliente());
        btnListar.addActionListener(e -> atualizarTabela());

        btnVoltar.addActionListener(e -> {
            dispose();
            new MainMenuFrame().setVisible(true);
        });

        btnSair.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente sair do sistema?",
                    "Confirmar Saída",
                    JOptionPane.YES_NO_OPTION);

            if (resposta == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    // Método auxiliar para criar botões com o mesmo padrão visual do LoginFrame
    private JButton criarBotaoEstilizado(String texto, Color background, Color foreground, Font fonte) {
        JButton botao = new JButton(texto);
        botao.setFont(fonte);
        botao.setBackground(background);
        botao.setForeground(foreground);
        botao.setFocusPainted(false);
        botao.setPreferredSize(new Dimension(130, 35));
        return botao;
    }

    private void cadastrarCliente() {
        String nome = txtNome.getText().trim();
        String cpf = txtCpf.getText().trim();
        String cnh = txtCnh.getText().trim();

        if (nome.isEmpty() || cpf.isEmpty() || cnh.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        modeloTabela.addRow(new Object[]{
                modeloTabela.getRowCount() + 1,
                nome,
                cpf,
                cnh
        });

        limparCampos();
    }

    private void excluirCliente() {
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente da tabela.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        modeloTabela.removeRow(linha);
    }

    private void atualizarTabela() {
        // Quando ligar ao repositório, coloque a listagem aqui.
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtCnh.setText("");
        txtBuscarId.setText("");
    }
}