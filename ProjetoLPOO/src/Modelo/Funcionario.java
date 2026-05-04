package Modelo;

public class Funcionario extends Pessoa {

    private String matricula;
    private double salario;

    public Funcionario(int id, String cpf, String nome,String matricula, double salario){
        super(id, nome, cpf);
        this.matricula = matricula;
        this.salario = salario;
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
    public double getSalario(){
        return salario;
    }

}
