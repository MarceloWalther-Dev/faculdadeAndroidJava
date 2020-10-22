package model;

import java.io.Serializable;
import java.util.Date;

public class Eventos implements Serializable {

    private int id;
    private String nomeDoEvento;
    private String dataDoEvento;
    private String localDoEvento;

    public Eventos(int id, String nomeDoEvento, String dataDoEvento, String localDoEvento) {
        this.id = id;
        this.nomeDoEvento = nomeDoEvento;
        this.dataDoEvento = dataDoEvento;
        this.localDoEvento = localDoEvento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeDoEvento() {
        return nomeDoEvento;
    }

    public void setNomeDoEvento(String nomeDoEvento) {
        this.nomeDoEvento = nomeDoEvento;
    }

    public String getDataDoEvento() {
        return dataDoEvento;
    }

    public void setDataDoEvento(String dataDoEvento) {
        this.dataDoEvento = dataDoEvento;
    }

    public String getLocalDoEvento() {
        return localDoEvento;
    }

    public void setLocalDoEvento(String localDoEvento) {
        this.localDoEvento = localDoEvento;
    }

    @Override
    public String toString() {
        return "Club: " + nomeDoEvento + '\n' +
                "Data : " + dataDoEvento +'\n' +
                "Local: " + localDoEvento ;
    }
}
