package Interface;

import Modelo.Carro;
import Repositorio.CarroRepositorio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class CarroFrame extends JFrame {

    private final CarroRepositorio repositorio =
            CarroRepositorio.getInstanciaCarro();

    private JTextField txtModelo;
    private JTextField txtPlaca;
    private JCheckBox chkDisponivel;
    private JTextField txtBuscarId;

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public CarroFrame() {

        setTitle("Cadastro de Carros");
        setSize(900, 750); // Aumentado ligeiramente para melhor respiro visual
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        // Cores da Identidade Visual Padrão
        Color fundo = new Color(245, 245, 245);
        Color corPrimaria = new Color(0, 123, 255);
        Color corSecundaria = Color.DARK_GRAY;
        Color corPerigo = new Color(220, 53, 69);
        Font fontePadrao = new Font("Segoe UI", Font.PLAIN, 14);

        JPanel principal = new JPanel(new BorderLayout(20, 20));
        principal.setBackground(fundo);
        principal.setBorder(new EmptyBorder(25, 25, 25, 25));

        // =======================
        // CABEÇALHO
        // =======================

        JPanel topo = new JPanel();
        topo.setBackground(fundo);
        topo.setLayout(new BoxLayout(topo, BoxLayout.Y_AXIS));

        ImageIcon icone = new ImageIcon(
                getClass().getResource("/imagens/logo.png"));

        Image imagem = icone.getImage().getScaledInstance(
                110,
                110,
                Image.SCALE_SMOOTH);

        JLabel logo = new JLabel(new ImageIcon(imagem));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titulo = new JLabel("Cadastro de Carros");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JLabel subtitulo = new JLabel("Gerencie os veículos da locadora");
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitulo.setForeground(Color.GRAY);

        topo.add(logo);
        topo.add(Box.createVerticalStrut(8));
        topo.add(titulo);
        topo.add(subtitulo);
        topo.add(Box.createVerticalStrut(15));

        principal.add(topo, BorderLayout.NORTH);

        // =======================
        // CENTRO (Formulário + Tabela)
        // =======================

        JPanel centro = new JPanel(new BorderLayout(15, 15));
        centro.setBackground(fundo);

        // Card Formulário (Branco com bordas limpas)
        JPanel cardFormulario = new JPanel(new GridBagLayout());
        cardFormulario.setBackground(Color.WHITE);
        cardFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                new EmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;

        txtModelo = new JTextField();
        txtPlaca = new JTextField();
        txtBuscarId = new JTextField();
        chkDisponivel = new JCheckBox("Disponível no sistema");

        configurarCampo(txtModelo, "Modelo", fontePadrao);
        configurarCampo(txtPlaca, "Placa", fontePadrao);
        configurarCampo(txtBuscarId, "ID (Ações)", fontePadrao);

        chkDisponivel.setBackground(Color.WHITE);
        chkDisponivel.setFont(fontePadrao);
        chkDisponivel.setFocusPainted(false);
        chkDisponivel.setSelected(true);

        // Grid - Linha 1
        c.gridx = 0;
        c.gridy = 0;
        cardFormulario.add(txtModelo, c);

        c.gridx = 1;
        cardFormulario.add(txtPlaca, c);

        // Grid - Linha 2
        c.gridx = 0;
        c.gridy = 1;
        cardFormulario.add(txtBuscarId, c);

        c.gridx = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        cardFormulario.add(chkDisponivel, c);

        c.fill = GridBagConstraints.HORIZONTAL; // Reseta o padrão

        // ===================
        // PAINEL DE BOTÕES
        // ===================

        JPanel painelBotoes = new JPanel(new GridLayout(1, 5, 10, 0));
        painelBotoes.setBackground(Color.WHITE);

        // Instanciando botões com o padrão visual limpo
        JButton btnAdicionar = criarBotaoEstilizado("Adicionar", corPrimaria, Color.WHITE, fontePadrao);
        JButton btnBuscar = criarBotaoEstilizado("Carregar", corSecundaria, Color.WHITE, fontePadrao);
        JButton btnAtualizar = criarBotaoEstilizado("Atualizar", corSecundaria, Color.WHITE, fontePadrao);
        JButton btnRemover = criarBotaoEstilizado("Remover", corPerigo, Color.WHITE, fontePadrao);
        JButton btnListar = criarBotaoEstilizado("Atualizar Lista", corSecundaria, Color.WHITE, fontePadrao);

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnBuscar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnListar);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.insets = new Insets(15, 8, 5, 8); // Margem maior acima dos botões

        cardFormulario.add(painelBotoes, c);

        centro.add(cardFormulario, BorderLayout.NORTH);

        // ===================
        // TABELA ESTILIZADA
        // ===================

        modeloTabela = new DefaultTableModel(
                new Object[]{"ID", "Modelo", "Placa", "Disponível"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela = new JTable(modeloTabela);
        tabela.setRowHeight(26);
        tabela.setFont(fontePadrao);
        tabela.setGridColor(Color.LIGHT_GRAY);

        JTableHeader cabecalho = tabela.getTableHeader();
        cabecalho.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cabecalho.setBackground(Color.WHITE);
        cabecalho.setForeground(Color.BLACK);

        tabela.getSelectionModel().addListSelectionListener(this::valueChanged);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        centro.add(scroll, BorderLayout.CENTER);

        principal.add(centro, BorderLayout.CENTER);

        // ===================
        // RODAPÉ
        // ===================

        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rodape.setBackground(fundo);

        JButton btnVoltar = criarBotaoEstilizado("Voltar", Color.WHITE, Color.BLACK, fontePadrao);
        btnVoltar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        btnVoltar.setPreferredSize(new Dimension(110, 35));

        JButton btnSair = criarBotaoEstilizado("Sair", Color.WHITE, corPerigo, fontePadrao);
        btnSair.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        btnSair.setPreferredSize(new Dimension(110, 35));

        rodape.add(btnVoltar);
        rodape.add(btnSair);

        principal.add(rodape, BorderLayout.SOUTH);

        add(principal);

        // Eventos e Listeners
        btnAdicionar.addActionListener(e -> adicionarCarro());
        btnBuscar.addActionListener(e -> carregarPorId());
        btnAtualizar.addActionListener(e -> atualizarCarro());
        btnRemover.addActionListener(e -> removerCarro());
        btnListar.addActionListener(e -> atualizarTabela());

        btnVoltar.addActionListener(e -> {
            dispose();
            new MainMenuFrame().setVisible(true);
        });

        btnSair.addActionListener(e -> confirmarSaida());

        atualizarTabela();
    }

    private void configurarCampo(JTextField campo, String titulo, Font fonte) {
        campo.setFont(fonte);
        campo.setPreferredSize(new Dimension(250, 45));
        // Adiciona um espaçamento interno leve além da borda com título
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(titulo),
                BorderFactory.createEmptyBorder(2, 5, 2, 5)
        ));
    }

    // Método construtor auxiliar unificado para botões
    private JButton criarBotaoEstilizado(String texto, Color background, Color foreground, Font fonte) {
        JButton botao = new JButton(texto);
        botao.setFont(fonte);
        botao.setBackground(background);
        botao.setForeground(foreground);
        botao.setFocusPainted(false);
        botao.setPreferredSize(new Dimension(130, 38));
        return botao;
    }

    // ============================
    // MÉTODOS DE REGRA DE NEGÓCIO
    // ============================

    private void adicionarCarro() {
        String modelo = txtModelo.getText().trim();
        String placa = txtPlaca.getText().trim();

        if (modelo.isEmpty() || placa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha Modelo e Placa.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Carro carro = new Carro(modelo, placa, chkDisponivel.isSelected());

        if (repositorio.adicionar_Carros(carro)) {
            JOptionPane.showMessageDialog(this, "Carro cadastrado com sucesso!");
            limparFormulario();
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Já existe um carro cadastrado com esta placa.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarPorId() {
        int id = lerIdDoCampo();
        if (id < 0) return;

        Carro carro = repositorio.buscarPorId(id);

        if (carro == null) {
            JOptionPane.showMessageDialog(this, "Carro não encontrado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        txtModelo.setText(carro.getModelo());
        txtPlaca.setText(carro.getPlaca());
        chkDisponivel.setSelected(carro.isDisponivel());
    }

    private void atualizarCarro() {
        int id = lerIdDoCampo();
        if (id < 0) return;

        String modelo = txtModelo.getText().trim();
        String placa = txtPlaca.getText().trim();

        if (modelo.isEmpty() || placa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Carro carro = new Carro(modelo, placa, chkDisponivel.isSelected());

        if (repositorio.atualizar_Carros(id, carro)) {
            JOptionPane.showMessageDialog(this, "Carro atualizado com sucesso.");
            limparFormulario();
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Não foi possível atualizar o carro.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerCarro() {
        int id = lerIdDoCampo();
        if (id < 0) return;

        int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir este carro?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            if (repositorio.remover_Carros(id)) {
                JOptionPane.showMessageDialog(this, "Carro removido com sucesso.");
                limparFormulario();
                atualizarTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Carro não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        List<Carro> carros = repositorio.listar_Carros();

        for (Carro carro : carros) {
            modeloTabela.addRow(new Object[]{
                    carro.getId(),
                    carro.getModelo(),
                    carro.getPlaca(),
                    carro.isDisponivel() ? "Sim" : "Não"
            });
        }
    }

    private int lerIdDoCampo() {
        try {
            return Integer.parseInt(txtBuscarId.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Informe um número de ID válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    private void limparFormulario() {
        txtModelo.setText("");
        txtPlaca.setText("");
        txtBuscarId.setText("");
        chkDisponivel.setSelected(true);
    }

    private void confirmarSaida() {
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente sair do sistema?", "Confirmar Saída", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
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