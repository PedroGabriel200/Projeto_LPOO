package Interface;

import Modelo.Funcionario;
import Repositorio.FuncionarioRepositorio;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class FuncionarioFrame extends JFrame {

    private final FuncionarioRepositorio repositorio =
            FuncionarioRepositorio.getInstanciaFuncionario();

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtMatricula;
    private JTextField txtSalario;
    private JTextField txtSenha;
    private JTextField txtBuscarId;

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public FuncionarioFrame() {

        setTitle("Menu Funcionários");
        setSize(850, 700); // Aumentado levemente para acomodar confortavelmente o formulário expandido
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
        JLabel lblTituloForm = new JLabel("Dados do Funcionário");
        lblTituloForm.setFont(fonteTitulo);
        c.gridx = 0; c.gridy = 0;
        c.gridwidth = 4;
        cardFormulario.add(lblTituloForm, c);
        c.gridwidth = 1; // Reseta

        // Linha 1: Nome e CPF
        c.gridy = 1;
        c.gridx = 0; c.weightx = 0;
        JLabel lblNome = new JLabel("Nome:"); lblNome.setFont(fontePadrao);
        cardFormulario.add(lblNome, c);

        c.gridx = 1; c.weightx = 1.0;
        txtNome = new JTextField(); txtNome.setFont(fontePadrao);
        txtNome.setPreferredSize(new Dimension(200, 30));
        cardFormulario.add(txtNome, c);

        c.gridx = 2; c.weightx = 0;
        JLabel lblCpf = new JLabel("CPF:"); lblCpf.setFont(fontePadrao);
        cardFormulario.add(lblCpf, c);

        c.gridx = 3; c.weightx = 0.5;
        txtCpf = new JTextField(); txtCpf.setFont(fontePadrao);
        txtCpf.setPreferredSize(new Dimension(150, 30));
        cardFormulario.add(txtCpf, c);

        // Linha 2: Matrícula e Salário
        c.gridy = 2;
        c.gridx = 0; c.weightx = 0;
        JLabel lblMatricula = new JLabel("Matrícula:"); lblMatricula.setFont(fontePadrao);
        cardFormulario.add(lblMatricula, c);

        c.gridx = 1; c.weightx = 1.0;
        txtMatricula = new JTextField(); txtMatricula.setFont(fontePadrao);
        txtMatricula.setPreferredSize(new Dimension(200, 30));
        cardFormulario.add(txtMatricula, c);

        c.gridx = 2; c.weightx = 0;
        JLabel lblSalario = new JLabel("Salário:"); lblSalario.setFont(fontePadrao);
        cardFormulario.add(lblSalario, c);

        c.gridx = 3; c.weightx = 0.5;
        txtSalario = new JTextField(); txtSalario.setFont(fontePadrao);
        txtSalario.setPreferredSize(new Dimension(150, 30));
        cardFormulario.add(txtSalario, c);

        // Linha 3: Senha e ID de Busca
        c.gridy = 3;
        c.gridx = 0; c.weightx = 0;
        JLabel lblSenha = new JLabel("Senha:"); lblSenha.setFont(fontePadrao);
        cardFormulario.add(lblSenha, c);

        c.gridx = 1; c.weightx = 1.0;
        txtSenha = new JTextField(); txtSenha.setFont(fontePadrao);
        txtSenha.setPreferredSize(new Dimension(200, 30));
        cardFormulario.add(txtSenha, c);

        c.gridx = 2; c.weightx = 0;
        JLabel lblId = new JLabel("ID (Ações):"); lblId.setFont(fontePadrao);
        cardFormulario.add(lblId, c);

        c.gridx = 3; c.weightx = 0.5;
        txtBuscarId = new JTextField(); txtBuscarId.setFont(fontePadrao);
        txtBuscarId.setPreferredSize(new Dimension(150, 30));
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

        String[] colunas = {"ID", "Nome", "CPF", "Matrícula", "Salário"};

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
        tabela.getSelectionModel().addListSelectionListener(this::valueChanged);

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


        // ================= MONTAGEM DOS LAYOUTS =================

        JPanel painelTopo = new JPanel(new BorderLayout(0, 10));
        painelTopo.setBackground(fundo);
        painelTopo.add(cardFormulario, BorderLayout.CENTER);
        painelTopo.add(painelBotoes, BorderLayout.SOUTH);

        painelPrincipal.add(painelTopo, BorderLayout.NORTH);
        painelPrincipal.add(scroll, BorderLayout.CENTER);
        painelPrincipal.add(painelRodape, BorderLayout.SOUTH);

        add(painelPrincipal);

        // ================= AÇÕES E EVENTOS =================

        btnAdicionar.addActionListener(e -> adicionarFuncionario());
        btnCarregar.addActionListener(e -> carregarFuncionario());
        btnAtualizar.addActionListener(e -> atualizarFuncionario());
        btnRemover.addActionListener(e -> removerFuncionario());
        btnListar.addActionListener(e -> atualizarTabela());

        btnVoltar.addActionListener(e -> {
            dispose();
            new MainMenuFrame().setVisible(true);
        });

        btnSair.addActionListener(e -> confirmarSaida());

        atualizarTabela();
        setVisible(true);
    }

    // Construtor auxiliar padronizado de botões
    private JButton criarBotaoEstilizado(String texto, Color background, Color foreground, Font fonte) {
        JButton botao = new JButton(texto);
        botao.setFont(fonte);
        botao.setBackground(background);
        botao.setForeground(foreground);
        botao.setFocusPainted(false);
        botao.setPreferredSize(new Dimension(135, 35));
        return botao;
    }

    // ============================
    // MÉTODOS DE REGRA DE NEGÓCIO
    // ============================

    private void adicionarFuncionario() {
        try {
            Funcionario funcionario = new Funcionario(
                    0,
                    txtCpf.getText().trim(),
                    txtNome.getText().trim(),
                    txtMatricula.getText().trim(),
                    Double.parseDouble(txtSalario.getText().trim()),
                    txtSenha.getText().trim()
            );

            if (repositorio.adicionar_Funcionario(funcionario)) {
                JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");
                limparFormulario();
                atualizarTabela();
            } else {
                JOptionPane.showMessageDialog(this, "CPF ou matrícula já cadastrados.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Por favor, informe um valor de salário válido.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void carregarFuncionario() {
        Funcionario f = repositorio.buscarPorId(lerId());

        if (f == null) {
            JOptionPane.showMessageDialog(this, "Funcionário não encontrado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        txtNome.setText(f.getNome());
        txtCpf.setText(f.getCpf());
        txtMatricula.setText(f.getMatricula());
        txtSalario.setText(String.valueOf(f.getSalario()));
        txtSenha.setText(f.getSenha());
    }

    private void atualizarFuncionario() {
        int id = lerId();
        if (id < 0) {
            JOptionPane.showMessageDialog(this, "Informe um ID válido para atualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Funcionario funcionario = new Funcionario(
                    id,
                    txtCpf.getText().trim(),
                    txtNome.getText().trim(),
                    txtMatricula.getText().trim(),
                    Double.parseDouble(txtSalario.getText().trim()),
                    txtSenha.getText().trim()
            );

            if (repositorio.atualizar_Funcionario(id, funcionario)) {
                JOptionPane.showMessageDialog(this, "Funcionário atualizado com sucesso.");
                limparFormulario();
                atualizarTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Por favor, informe um valor de salário válido.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void removerFuncionario() {
        int id = lerId();
        if (id < 0) {
            JOptionPane.showMessageDialog(this, "Informe um ID válido para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int op = JOptionPane.showConfirmDialog(this, "Deseja realmente remover este funcionário?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            if (repositorio.remover_Funcionario(id)) {
                JOptionPane.showMessageDialog(this, "Funcionário removido com sucesso.");
                limparFormulario();
                atualizarTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Funcionário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        List<Funcionario> lista = repositorio.listar_Funcionarios();

        for (Funcionario f : lista) {
            modeloTabela.addRow(new Object[]{
                    f.getId(),
                    f.getNome(),
                    f.getCpf(),
                    f.getMatricula(),
                    f.getSalario()
            });
        }
    }

    private int lerId() {
        try {
            return Integer.parseInt(txtBuscarId.getText().trim());
        } catch (Exception e) {
            return -1;
        }
    }

    private void limparFormulario() {
        txtNome.setText("");
        txtCpf.setText("");
        txtMatricula.setText("");
        txtSalario.setText("");
        txtSenha.setText("");
        txtBuscarId.setText("");
    }

    private void confirmarSaida() {
        int op = JOptionPane.showConfirmDialog(this, "Deseja realmente sair do sistema?", "Confirmar Saída", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void valueChanged(ListSelectionEvent e) {
        int linha = tabela.getSelectedRow();
        if (linha >= 0) {
            txtBuscarId.setText(modeloTabela.getValueAt(linha, 0).toString());
        }
    }
}