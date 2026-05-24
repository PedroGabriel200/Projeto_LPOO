package Modelo;

import java.time.LocalDate;

public class Locacao {

    private int id;
    private Cliente cliente;
    private Carro carro;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Funcionario funcionario;

    public Locacao(int id, Cliente cliente, Carro carro, LocalDate dataInicio, LocalDate dataFim, Funcionario funcionario) {
        this.id = id;
        this.cliente = cliente;
        this.carro = carro;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.funcionario = funcionario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    @Override
    public String toString() {
        return
                "ID: " + getId() +
                        " | Clinte: " + getCliente() +
                        " | Carro: " + getCarro() +
                        " | Data Início: " + getDataInicio() +
                        " | Data Fim: " + getDataFim() +
                        " | Funcionário: " + getFuncionario();
    }
}
