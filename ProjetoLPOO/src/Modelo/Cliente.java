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
    public void setCnh(String cnh){
        this.cnh = cnh;
    }

    @Override
    public String toString() {
        return "ID: " + getId() +
                " | Nome: " + getNome() +
                " | CPF: " + getCpf() +
                " | CNH: " + cnh;
    }
}
