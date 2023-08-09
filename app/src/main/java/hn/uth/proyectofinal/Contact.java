package hn.uth.proyectofinal;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Contact {
    private int idcontacto;
    private String nombre;
    private String telefono;
    private String direccion;

    public int getIdcontacto() {
        return idcontacto;
    }
    public Contact(int idcontacto, String nombre, String telefono, String direccion) {
        this.idcontacto = idcontacto;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public void setIdcontacto(int idcontacto) {
        this.idcontacto = idcontacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


}
