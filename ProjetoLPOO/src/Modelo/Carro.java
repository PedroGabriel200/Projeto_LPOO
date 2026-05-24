package Modelo;

public class Carro {

    private int id;
    private String modelo;
    private String placa;
    private boolean disponivel;

    public Carro(String modelo, String placa, boolean disponivel){
        this.modelo = modelo;
        this.placa = placa;
        this.disponivel = disponivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public boolean isDisponivel()
    {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel)
    {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return
                "ID: " + id +
                " | Modelo: " + modelo  +
                " | Placa: " + placa +
                " | Disponível: " + (disponivel ? "Sim" : "Não");
    }
}
