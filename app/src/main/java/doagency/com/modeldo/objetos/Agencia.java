package doagency.com.modeldo.objetos;

/**
 * Created by Diego on 12/03/2017.
 */

public class Agencia {

    public Agencia(){

    }
    private int idAgenciaModelo;
    private String NombreAgencia;
    private String RUCAgencia;
    private int idAgencia;
    private String Telefono;
    private String Direccion;
    private String Pais;
    private int idPais;
    private String Agencia;
    private String NombreModelo;
    private String newEmail;
    private String elusuariom;
    private int idUsuario;
    private String Soles;
    private String Dolares;
    private int idModelo;
    public Agencia(String NombreAgencia, int idAgencia, int idAgenciaModelo, String RUCAgencia  ) {
        this.NombreAgencia = NombreAgencia;
        this.idAgencia = idAgencia;
        this.idAgenciaModelo=idAgenciaModelo;
        this.RUCAgencia=RUCAgencia;
    }

    public int getIdAgenciaModelo() {
        return idAgenciaModelo;
    }

    public void setIdAgenciaModelo(int idAgenciaModelo) {
        this.idAgenciaModelo = idAgenciaModelo;
    }

    public String getNombreAgencia() {
        return NombreAgencia;
    }

    public void setNombreAgencia(String nombreAgencia) {
        NombreAgencia = nombreAgencia;
    }

    public String getRUCAgencia() {
        return RUCAgencia;
    }

    public void setRUCAgencia(String RUCAgencia) {
        this.RUCAgencia = RUCAgencia;
    }

    public int getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(int idAgencia) {
        this.idAgencia = idAgencia;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getAgencia() {
        return Agencia;
    }

    public void setAgencia(String agencia) {
        Agencia = agencia;
    }

    public String getNombreModelo() {
        return NombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        NombreModelo = nombreModelo;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getSoles() {
        return Soles;
    }

    public void setSoles(String soles) {
        Soles = soles;
    }

    public String getDolares() {
        return Dolares;
    }

    public void setDolares(String dolares) {
        Dolares = dolares;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public String getElusuariom() {
        return elusuariom;
    }

    public void setElusuariom(String elusuariom) {
        this.elusuariom = elusuariom;
    }
}
