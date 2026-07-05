package Modelo;

public class Funcionario extends Pessoa {

    private String matricula;
    private double salario;
    private String senha;

    public Funcionario(int id, String cpf, String nome,String matricula, double salario, String senha){
        super(id, nome, cpf);
        this.matricula = matricula;
        this.salario = salario;
        this.senha = senha;
    }
    public void setMatricula(String Matricula){
        this.matricula = matricula;
    }
    public String getMatricula(){
        return matricula;
    }
    public void setSalario(Double Salario){
        this.salario = Salario;

    }
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public double getSalario(){
        return salario;
    }
    @Override
    public String toString() {
        return
                "ID: " + getId() +
                        " | Nome: " + getNome() +
                        " | CPF: " + getCpf() +
                        " | Matricula: " + getMatricula() +
                        " | Salário: " + getSalario()+
                        " | Senha: " + getSenha();

    }

}
