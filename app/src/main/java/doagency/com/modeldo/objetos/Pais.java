package doagency.com.modeldo.objetos;

/**
 * Created by Diego on 17/03/2017.
 */

public class Pais {
    private int idPais;
    private String Pais;
    private int Importancia;
    public Pais(){

    }
    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public int getImportancia() {
        return Importancia;
    }

    public void setImportancia(int importancia) {
        Importancia = importancia;
    }
}
