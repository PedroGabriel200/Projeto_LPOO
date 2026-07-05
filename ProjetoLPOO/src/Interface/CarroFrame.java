package Interface;

import Modelo.Carro;
import Repositorio.CarroRepositorio;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CarroFrame extends JFrame {
    // Repositório de carros (Singleton)
    private final CarroRepositorio repositorio = CarroRepositorio.getInstanciaCarro();

    // Campos do formulário,
    private JTextField txtModelo;
    private JTextField txtPlaca;
    private JCheckBox chkDisponivel;
    private JTextField txtBuscarId;

    // Tabela de listagem
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public CarroFrame() {
        setTitle("Menu Carros");
        setSize(750, 600);
        setLocationRelativeTo(null);
        // pra não encerrar o programa ao fechar
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

        //   PAINEL DO FORMULÁRIO
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Dados do Carro"));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        // Linha 0: Modelo
        c.gridx = 0; c.gridy = 0;
        painelFormulario.add(new JLabel("Modelo:"), c);
        c.gridx = 1;
        txtModelo = new JTextField(20);
        painelFormulario.add(txtModelo, c);

        // Linha 1: Placa
        c.gridx = 0; c.gridy = 1;
        painelFormulario.add(new JLabel("Placa:"), c);
        c.gridx = 1;
        txtPlaca = new JTextField(20);
        painelFormulario.add(txtPlaca, c);

        // Linha 2: Disponível
        c.gridx = 0; c.gridy = 2;
        painelFormulario.add(new JLabel("Disponível:"), c);
        c.gridx = 1;
        chkDisponivel = new JCheckBox();
        chkDisponivel.setSelected(true);
        painelFormulario.add(chkDisponivel, c);

        // Linha 3: Busca por id
        c.gridx = 0; c.gridy = 3;
        painelFormulario.add(new JLabel("ID (editar/remover):"), c);
        c.gridx = 1;
        txtBuscarId = new JTextField(20);
        painelFormulario.add(txtBuscarId, c);

        // PAINEL DE BOTÕES CRUD
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JButton btnAdicionar  = new JButton("Adicionar");
        JButton btnCarregarId = new JButton("Carregar por ID");
        JButton btnAtualizar  = new JButton("Atualizar");
        JButton btnRemover    = new JButton("Remover");
        JButton btnListar     = new JButton("Listar Todos");

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnCarregarId);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnListar);

        // TABELA DE LISTAGEM
        String[] colunas = {"ID", "Modelo", "Placa", "Disponível"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            // Células não editáveis diretamente na tabela
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tabela = new JTable(modeloTabela);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Quando seleciona a linha da tabela, preenche o campo de ID
        tabela.getSelectionModel().addListSelectionListener(this::valueChanged);

        JScrollPane scroll = new JScrollPane(tabela);

        // PAINEL INFERIOR: Voltar e Sair
        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        JButton btnVoltar = new JButton("Voltar");
        JButton btnSair   = new JButton("Sair");
        painelRodape.add(btnVoltar);
        painelRodape.add(btnSair);

        // Montagem do layout principal
        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.add(painelFormulario, BorderLayout.CENTER);
        painelTopo.add(painelBotoes, BorderLayout.SOUTH);

        painelPrincipal.add(painelTopo, BorderLayout.NORTH);
        painelPrincipal.add(scroll, BorderLayout.CENTER);
        painelPrincipal.add(painelRodape, BorderLayout.SOUTH);

        add(painelPrincipal);


        // AÇÕES DOS BOTÕES

        // ADICIONAR
        btnAdicionar.addActionListener(e -> adicionarCarro());

        // CARREGAR dados de um carro pelo ID
        btnCarregarId.addActionListener(e -> carregarPorId());

        // ATUALIZAR
        btnAtualizar.addActionListener(e -> atualizarCarro());

        // REMOVER
        btnRemover.addActionListener(e -> removerCarro());

        // LISTAR — atualiza a tabela
        btnListar.addActionListener(e -> atualizarTabela());

        // VOLTAR — fecha esta janela e reabre o MainMenu
        btnVoltar.addActionListener(e -> {
            dispose();
            new MainMenu().setVisible(true);
        });

        // SAIR — confirmação antes de encerrar
        btnSair.addActionListener(e -> confirmarSaida());

        // Carrega a tabela já ao abrir a tela
        atualizarTabela();
    }

    /// Valida campos e adiciona um novo carro
    private void adicionarCarro() {

        String modelo = txtModelo.getText().trim();
        String placa  = txtPlaca.getText().trim();

        if (modelo.isEmpty() || placa.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Modelo e Placa são obrigatórios.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Carro carro = new Carro(modelo, placa, chkDisponivel.isSelected());

        if (repositorio.adicionar_Carros(carro)) {
            JOptionPane.showMessageDialog(this,
                    "Carro adicionado com ID " + carro.getId() + ".");
            limparFormulario();
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Não foi possível adicionar. Placa já cadastrada.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    ///Carrega os dados do carro pelo ID informado
    private void carregarPorId() {

        int id = lerIdDoCampo();
        if (id < 0) return;

        Carro carro = repositorio.buscarPorId(id);

        if (carro == null) {
            JOptionPane.showMessageDialog(this,
                    "Carro não encontrado.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        txtModelo.setText(carro.getModelo());
        txtPlaca.setText(carro.getPlaca());
        chkDisponivel.setSelected(carro.isDisponivel());
    }

    /// Atualiza o carro com o ID informado
    private void atualizarCarro() {

        int id = lerIdDoCampo();
        if (id < 0) return;

        String modelo = txtModelo.getText().trim();
        String placa  = txtPlaca.getText().trim();

        if (modelo.isEmpty() || placa.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Modelo e Placa são obrigatórios.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Carro novoCarro = new Carro(modelo, placa, chkDisponivel.isSelected());

        if (repositorio.atualizar_Carros(id, novoCarro)) {
            JOptionPane.showMessageDialog(this, "Carro atualizado com sucesso.");
            limparFormulario();
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Não foi possível atualizar. ID não encontrado ou placa duplicada.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /// Remove o carro com o ID informado
    private void removerCarro() {

        int id = lerIdDoCampo();
        if (id < 0) return;

        int confirmacao = JOptionPane.showConfirmDialog(this,
                "Deseja realmente remover o carro com ID " + id + "?",
                "Confirmar Remoção", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            if (repositorio.remover_Carros(id)) {
                JOptionPane.showMessageDialog(this, "Carro removido.");
                limparFormulario();
                atualizarTabela();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Carro não encontrado.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    ///Recarrega a tabela com todos os carros do repositório
    private void atualizarTabela() {

        modeloTabela.setRowCount(0); // Limpa as linhas existentes

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

    // Valida o ID, retorna -1 em caso de erro
    private int lerIdDoCampo() {
        try {
            return Integer.parseInt(txtBuscarId.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Informe um ID numérico válido.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return -1;
        }
    }

    ///Limpa os campos do formulário
    private void limparFormulario() {
        txtModelo.setText("");
        txtPlaca.setText("");
        chkDisponivel.setSelected(true);
        txtBuscarId.setText("");
    }

    // Exibe diálogo de confirmação antes de encerrar o programa
    private void confirmarSaida() {
        int resposta = JOptionPane.showConfirmDialog(this,
                "Deseja realmente sair do sistema?",
                "Confirmar Saída", JOptionPane.YES_NO_OPTION);
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

