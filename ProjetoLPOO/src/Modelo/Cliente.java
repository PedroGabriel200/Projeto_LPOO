package Modelo;

public class Cliente extends Pessoa {

    private String cnh;

    public Cliente(int id, String cpf, String nome, String cnh){
        super(id, nome, cpf);
        this.cnh = cnh;
    }

    public String getCnh(){
        return cnh;
    }
    public void setCnh(String Cnh){
        this.cnh = cnh;
    }
}
