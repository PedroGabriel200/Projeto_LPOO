package Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class LocacaoFrame extends JFrame {

    private JTextField txtCliente;
    private JTextField txtCarro;
    private JTextField txtFuncionario;
    private JTextField txtDataInicio;
    private JTextField txtDataFim;
    private JTextField txtBuscarId;

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public LocacaoFrame() {

        setTitle("Menu Locações");
        setSize(900, 700); // Ajustado para 700 para dar mais respiro à tabela e ao formulário duplo
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Cores e Tipografia da Identidade Visual Unificada
        Color fundo = new Color(245, 245, 245);
        Color corPrimaria = new Color(0, 123, 255);
        Color corSecundaria = Color.DARK_GRAY;
        Color corPerigo = new Color(220, 53, 69);
        Font fontePadrao = new Font("Segoe UI", Font.PLAIN, 14);
        Font fonteTitulo = new Font("Segoe UI", Font.BOLD, 16);

        JPanel painelPrincipal = new JPanel(new BorderLayout(15, 15));
        painelPrincipal.setBackground(fundo);
        painelPrincipal.setBorder(new EmptyBorder(25, 25, 25, 25));

        // ================= FORMULÁRIO (Estilo Card Branco) =================

        JPanel cardFormulario = new JPanel(new GridBagLayout());
        cardFormulario.setBackground(Color.WHITE);
        cardFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                new EmptyBorder(15, 15, 15, 15)
        ));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 8, 6, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        // Título Interno do Card
        JLabel lblTituloForm = new JLabel("Dados da Locação");
        lblTituloForm.setFont(fonteTitulo);
        c.gridx = 0; c.gridy = 0;
        c.gridwidth = 4;
        cardFormulario.add(lblTituloForm, c);
        c.gridwidth = 1; // Reseta o gridwidth

        // Linha 1: Cliente e Carro
        c.gridy = 1;
        c.gridx = 0; c.weightx = 0;
        JLabel lblCliente = new JLabel("Cliente:"); lblCliente.setFont(fontePadrao);
        cardFormulario.add(lblCliente, c);

        c.gridx = 1; c.weightx = 1.0;
        txtCliente = new JTextField(); txtCliente.setFont(fontePadrao);
        txtCliente.setPreferredSize(new Dimension(200, 30));
        cardFormulario.add(txtCliente, c);

        c.gridx = 2; c.weightx = 0;
        JLabel lblCarro = new JLabel("Carro:"); lblCarro.setFont(fontePadrao);
        cardFormulario.add(lblCarro, c);

        c.gridx = 3; c.weightx = 1.0;
        txtCarro = new JTextField(); txtCarro.setFont(fontePadrao);
        txtCarro.setPreferredSize(new Dimension(200, 30));
        cardFormulario.add(txtCarro, c);

        // Linha 2: Funcionário e Data Início
        c.gridy = 2;
        c.gridx = 0; c.weightx = 0;
        JLabel lblFuncionario = new JLabel("Funcionário:"); lblFuncionario.setFont(fontePadrao);
        cardFormulario.add(lblFuncionario, c);

        c.gridx = 1; c.weightx = 1.0;
        txtFuncionario = new JTextField(); txtFuncionario.setFont(fontePadrao);
        txtFuncionario.setPreferredSize(new Dimension(200, 30));
        cardFormulario.add(txtFuncionario, c);

        c.gridx = 2; c.weightx = 0;
        JLabel lblDataInicio = new JLabel("Data Início:"); lblDataInicio.setFont(fontePadrao);
        cardFormulario.add(lblDataInicio, c);

        c.gridx = 3; c.weightx = 1.0;
        txtDataInicio = new JTextField(); txtDataInicio.setFont(fontePadrao);
        txtDataInicio.setPreferredSize(new Dimension(200, 30));
        cardFormulario.add(txtDataInicio, c);

        // Linha 3: Data Fim e ID de Busca
        c.gridy = 3;
        c.gridx = 0; c.weightx = 0;
        JLabel lblDataFim = new JLabel("Data Fim:"); lblDataFim.setFont(fontePadrao);
        cardFormulario.add(lblDataFim, c);

        c.gridx = 1; c.weightx = 1.0;
        txtDataFim = new JTextField(); txtDataFim.setFont(fontePadrao);
        txtDataFim.setPreferredSize(new Dimension(200, 30));
        cardFormulario.add(txtDataFim, c);

        c.gridx = 2; c.weightx = 0;
        JLabel lblId = new JLabel("ID (Ações):"); lblId.setFont(fontePadrao);
        cardFormulario.add(lblId, c);

        c.gridx = 3; c.weightx = 1.0;
        txtBuscarId = new JTextField(); txtBuscarId.setFont(fontePadrao);
        txtBuscarId.setPreferredSize(new Dimension(200, 30));
        cardFormulario.add(txtBuscarId, c);


        // ================= BOTÕES DE AÇÃO =================

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelBotoes.setBackground(fundo);

        JButton btnAdicionar = criarBotaoEstilizado("Adicionar", corPrimaria, Color.WHITE, fontePadrao);
        JButton btnCarregar = criarBotaoEstilizado("Carregar por ID", corSecundaria, Color.WHITE, fontePadrao);
        JButton btnAtualizar = criarBotaoEstilizado("Atualizar", corSecundaria, Color.WHITE, fontePadrao);
        JButton btnRemover = criarBotaoEstilizado("Remover", corPerigo, Color.WHITE, fontePadrao);
        JButton btnListar = criarBotaoEstilizado("Listar Todos", corSecundaria, Color.WHITE, fontePadrao);

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnCarregar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnListar);


        // ================= TABELA ESTILIZADA =================

        String[] colunas = {
                "ID",
                "Cliente",
                "Carro",
                "Funcionário",
                "Data Início",
                "Data Fim"
        };

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
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Estilizando o cabeçalho da tabela
        JTableHeader cabecalho = tabela.getTableHeader();
        cabecalho.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cabecalho.setBackground(Color.WHITE);
        cabecalho.setForeground(Color.BLACK);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));


        // ================= RODAPÉ =================

        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        painelRodape.setBackground(fundo);

        JButton btnVoltar = criarBotaoEstilizado("Voltar", Color.WHITE, Color.BLACK, fontePadrao);
        btnVoltar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JButton btnSair = criarBotaoEstilizado("Sair", Color.WHITE, corPerigo, fontePadrao);
        btnSair.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        painelRodape.add(btnVoltar);
        painelRodape.add(btnSair);


        // ================= MONTAGEM DO LAYOUT =================

        JPanel painelTopo = new JPanel(new BorderLayout(0, 10));
        painelTopo.setBackground(fundo);
        painelTopo.add(cardFormulario, BorderLayout.CENTER);
        painelTopo.add(painelBotoes, BorderLayout.SOUTH);

        painelPrincipal.add(painelTopo, BorderLayout.NORTH);
        painelPrincipal.add(scroll, BorderLayout.CENTER);
        painelPrincipal.add(painelRodape, BorderLayout.SOUTH);

        add(painelPrincipal);

        // ================= AÇÕES =================

        btnAdicionar.addActionListener(e -> cadastrarLocacao());
        btnRemover.addActionListener(e -> excluirLocacao());

        btnAtualizar.addActionListener(e -> {
            // Implementar atualização no repositório futuramente
        });

        btnCarregar.addActionListener(e -> {
            // Implementar busca por ID no repositório futuramente
        });

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

    // Construtor auxiliar padronizado de botões para unificação do design
    private JButton criarBotaoEstilizado(String texto, Color background, Color foreground, Font fonte) {
        JButton botao = new JButton(texto);
        botao.setFont(fonte);
        botao.setBackground(background);
        botao.setForeground(foreground);
        botao.setFocusPainted(false);
        botao.setPreferredSize(new Dimension(135, 35));
        return botao;
    }

    private void cadastrarLocacao() {

        String cliente = txtCliente.getText().trim();
        String carro = txtCarro.getText().trim();
        String funcionario = txtFuncionario.getText().trim();
        String inicio = txtDataInicio.getText().trim();
        String fim = txtDataFim.getText().trim();

        if (cliente.isEmpty() || carro.isEmpty() || funcionario.isEmpty()
                || inicio.isEmpty() || fim.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        modeloTabela.addRow(new Object[]{
                modeloTabela.getRowCount() + 1,
                cliente,
                carro,
                funcionario,
                inicio,
                fim
        });

        limparCampos();
    }

    private void excluirLocacao() {

        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma locação na tabela.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        modeloTabela.removeRow(linha);
    }

    private void atualizarTabela() {
        // Quando criar o LocacaoRepositorio, carregue os dados aqui.
    }

    private void limparCampos() {
        txtCliente.setText("");
        txtCarro.setText("");
        txtFuncionario.setText("");
        txtDataInicio.setText("");
        txtDataFim.setText("");
        txtBuscarId.setText("");
    }
}