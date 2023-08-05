package hn.uth.proyectofinal.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
//creacion de las tablas para el DAO
@Entity(tableName = "lugar_table")
public class Lugar {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo (name = "idlugar")
    private int idlugar;
    @NonNull
    @ColumnInfo(name = "lugar")
    private String lugar;
    @NonNull
    @ColumnInfo(name = "tipoLugar")
    private  String tipoLugar;
    @NonNull
    @ColumnInfo(name = "fecha")
    private Date fecha;
    @NonNull
    @ColumnInfo(name = "longitud")
    private Double longitud;
    @NonNull
    @ColumnInfo(name = "latitud")
    private Double latitud;
    @NonNull
    @ColumnInfo(name = "descripcion")
    private String descripcion;
    @NonNull
    @ColumnInfo(name = "retorno")
    private Boolean retorno;


    public Lugar(@NonNull String lugar, @NonNull String tipoLugar, @NonNull Date fecha, @NonNull Double longitud, @NonNull Double latitud, @NonNull String descripcion, @NonNull Boolean retorno) {
        this.lugar = lugar;
        this.tipoLugar = tipoLugar;
        this.fecha = fecha;
        this.longitud = longitud;
        this.latitud = latitud;
        this.descripcion = descripcion;
        this.retorno = retorno;
    }

    public int getIdlugar() {
        return idlugar;
    }

    public void setIdlugar(int idlugar) {
        this.idlugar = idlugar;
    }

    @NonNull
    public String getLugar() {
        return lugar;
    }

    public void setLugar(@NonNull String lugar) {
        this.lugar = lugar;
    }

    @NonNull
    public String getTipoLugar() {
        return tipoLugar;
    }

    public void setTipoLugar(@NonNull String tipoLugar) {
        this.tipoLugar = tipoLugar;
    }

    @NonNull
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(@NonNull Date fecha) {
        this.fecha = fecha;
    }

    @NonNull
    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(@NonNull Double longitud) {
        this.longitud = longitud;
    }

    @NonNull
    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(@NonNull Double latitud) {
        this.latitud = latitud;
    }
}




