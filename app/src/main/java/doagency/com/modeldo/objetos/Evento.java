package doagency.com.modeldo.objetos;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Diego on 19/03/2017.
 */

public class Evento {
   private int idEvento;
    private String NEvento;
    private Date FechaInicio;
    private Date FechaFin;
    private Time horaFin;
    private Time horaInicio;
    private int cantHoras;
    private int idModelo;
    private int idTipoMoneda;
    private String TipoMoneda;
    private String NombreModelo;
    private int idUsuario;
    private Float Precio;
    private int idAgencia;
    private String NAgencia;
    private int pagado;
    private int enviado;
    private String NewEmail;
    private String elusuariom;

    public Evento(){

    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getNEvento() {
        return NEvento;
    }

    public void setNEvento(String NEvento) {
        this.NEvento = NEvento;
    }



    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getCantHoras() {
        return cantHoras;
    }

    public void setCantHoras(int cantHoras) {
        this.cantHoras = cantHoras;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public int getIdTipoMoneda() {
        return idTipoMoneda;
    }

    public void setIdTipoMoneda(int idTipoMoneda) {
        this.idTipoMoneda = idTipoMoneda;
    }

    public String getTipoMoneda() {
        return TipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        TipoMoneda = tipoMoneda;
    }

    public String getNombreModelo() {
        return NombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        NombreModelo = nombreModelo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Float getPrecio() {
        return Precio;
    }

    public void setPrecio(Float precio) {
        Precio = precio;
    }

    public int getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(int idAgencia) {
        this.idAgencia = idAgencia;
    }

    public String getNAgencia() {
        return NAgencia;
    }

    public void setNAgencia(String NAgencia) {
        this.NAgencia = NAgencia;
    }

    public int getPagado() {
        return pagado;
    }

    public void setPagado(int pagado) {
        this.pagado = pagado;
    }

    public int getEnviado() {
        return enviado;
    }

    public void setEnviado(int enviado) {
        this.enviado = enviado;
    }

    public String getNewEmail() {
        return NewEmail;
    }

    public void setNewEmail(String newEmail) {
        NewEmail = newEmail;
    }

    public String getElusuariom() {
        return elusuariom;
    }

    public void setElusuariom(String elusuariom) {
        this.elusuariom = elusuariom;
    }


    public Date getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        FechaFin = fechaFin;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }
}
