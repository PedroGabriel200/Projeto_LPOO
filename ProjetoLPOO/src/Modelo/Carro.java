package Modelo;

public class Carro {

    private int id;
    private String modelo;
    private String placa;
    private boolean disponivel;

    public Carro(int id, String modelo,String placa, boolean disponivel){
        this.id = id;
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
        return "Carro{" +
                "id=" + id +
                ", modelo='" + modelo + '\'' +
                ", placa='" + placa + '\'' +
                ", disponivel=" + disponivel +
                '}';
    }
}

