package doagency.com.modeldo.objetos;

import java.util.Date;

/**
 * Created by Diego on 12/10/2016.
 */

public class Usuario {
    public Usuario(){

    }
    public Usuario(String Nombre)
    {
        this.Nombre=Nombre;
    }

    private int id;
    private int idUsuario;
    private  String Nombre;
    private String Email;
    private String Usuario;
    private int idModelo;

    private String Celular;
    private String Ojo;
    private String Pelo;
    private String Piel;
    private String Pais;
    private String FotoPerfil;

    private String PaisActual;
    private int Estatura,idOjo,idPelo,idPiel,Zapatos,idPais,Busto,Cintura,Cadera;
    private Date FechaNacimiento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public String getPaisActual() {
        return PaisActual;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setPaisActual(String paisActual) {
        PaisActual = paisActual;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public String getOjo() {
        return Ojo;
    }

    public void setOjo(String ojo) {
        Ojo = ojo;
    }

    public String getPelo() {
        return Pelo;
    }

    public void setPelo(String pelo) {
        Pelo = pelo;
    }

    public String getPiel() {
        return Piel;
    }

    public void setPiel(String piel) {
        Piel = piel;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public int getEstatura() {
        return Estatura;
    }

    public void setEstatura(int estatura) {
        Estatura = estatura;
    }

    public int getIdOjo() {
        return idOjo;
    }

    public void setIdOjo(int idOjo) {
        this.idOjo = idOjo;
    }

    public int getIdPelo() {
        return idPelo;
    }

    public void setIdPelo(int idPelo) {
        this.idPelo = idPelo;
    }

    public int getIdPiel() {
        return idPiel;
    }

    public void setIdPiel(int idPiel) {
        this.idPiel = idPiel;
    }

    public int getZapatos() {
        return Zapatos;
    }

    public void setZapatos(int zapatos) {
        Zapatos = zapatos;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public int getBusto() {
        return Busto;
    }

    public void setBusto(int busto) {
        Busto = busto;
    }

    public int getCintura() {
        return Cintura;
    }

    public void setCintura(int cintura) {
        Cintura = cintura;
    }

    public int getCadera() {
        return Cadera;
    }

    public void setCadera(int cadera) {
        Cadera = cadera;
    }

    public String getNombre() {
        return Nombre;
    }

    public  void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsuario() {
        return Usuario;
    }

    public String getFotoPerfil() {
        return FotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        FotoPerfil = fotoPerfil;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }
}
