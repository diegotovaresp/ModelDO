package doagency.com.modeldo.objetos;

/**
 * Created by Diego on 11/03/2017.
 */

public class Album {

    public Album(){

    }

    private int idModeloAlbum;
    private int idModelo;
    private  String Nombre;
    private int idTipoModelo;
    private String NombreModelo;
    private String newEmail;
    private int idUsuario;
    private String elusuariom;

    public Album(String Nombre, int idModeloAlbum, int idModelo, int idTipoModelo  ) {
        this.idModelo = idModelo;
        this.Nombre = Nombre;
        this.idModeloAlbum=idModeloAlbum;
        this.idTipoModelo=idTipoModelo;
    }
    public int getIdModeloAlbum() {
        return idModeloAlbum;
    }

    public void setIdModeloAlbum(int idModeloAlbum) {
        this.idModeloAlbum = idModeloAlbum;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getIdTipoModelo() {
        return idTipoModelo;
    }

    public void setIdTipoModelo(int idTipoModelo) {
        this.idTipoModelo = idTipoModelo;
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

    public String getElusuariom() {
        return elusuariom;
    }

    public void setElusuariom(String elusuariom) {
        this.elusuariom = elusuariom;
    }
}
