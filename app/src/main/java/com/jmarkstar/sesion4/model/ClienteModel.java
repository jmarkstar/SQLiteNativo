package com.jmarkstar.sesion4.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Podemos usar Parcelable o Serializable para poder pasar datos entre activities.
 *
 * Created by jmarkstar on 11/05/2017.
 */
public class ClienteModel implements Parcelable {

    public static final String TABLE_NAME = "cliente";
    public static final String ID_FIELD = "id";
    public static final String NOMBRE_FIELD = "nombre_completo";
    public static final String EMAIL_FIELD = "email";
    public static final String TELEFONO_FIELD = "telefono";
    public static final String DIRECCION_FIELD = "direccion";
    public static final String ID_EMPRESA_FIELD = "id_empresa";
    public static final String FECHA_NAC_FIELD = "fecha_nac";
    public static final String ID_ESTADO_CIVIL_FIELD = "id_estado_civil";
    public static final String TIENE_HIJOS_FIELD = "tiene_hijos";

    private Integer id;
    private String nombreCompleto;
    private String email;
    private String telefono;
    private String direccion;
    private Integer idEmpresaCliente;
    private String fechNacimiento;
    private Integer idEstadoCivil;
    private Boolean tieneHijos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getIdEmpresaCliente() {
        return idEmpresaCliente;
    }

    public void setIdEmpresaCliente(Integer idEmpresaCliente) {
        this.idEmpresaCliente = idEmpresaCliente;
    }

    public String getFechNacimiento() {
        return fechNacimiento;
    }

    public void setFechNacimiento(String fechNacimiento) {
        this.fechNacimiento = fechNacimiento;
    }

    public Integer getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(Integer idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public Boolean getTieneHijos() {
        return tieneHijos;
    }

    public void setTieneHijos(Boolean tieneHijos) {
        this.tieneHijos = tieneHijos;
    }

    @Override
    public String toString() {
        return "ClienteModel{" +
                "id=" + id +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", idEmpresaCliente=" + idEmpresaCliente +
                ", fechNacimiento='" + fechNacimiento + '\'' +
                ", idEstadoCivil=" + idEstadoCivil +
                ", tieneHijos=" + tieneHijos +
                '}';
    }

    //METODO PARCELABLE

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel destino, int flags) {
        destino.writeValue(this.id);
        destino.writeString(this.nombreCompleto);
        destino.writeString(this.email);
        destino.writeString(this.telefono);
        destino.writeString(this.direccion);
        destino.writeValue(this.idEmpresaCliente);
        destino.writeString(this.fechNacimiento);
        destino.writeValue(this.idEstadoCivil);
        destino.writeValue(this.tieneHijos);
    }

    public ClienteModel() {
    }

    protected ClienteModel(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nombreCompleto = in.readString();
        this.email = in.readString();
        this.telefono = in.readString();
        this.direccion = in.readString();
        this.idEmpresaCliente = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fechNacimiento = in.readString();
        this.idEstadoCivil = (Integer) in.readValue(Integer.class.getClassLoader());
        this.tieneHijos = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ClienteModel> CREATOR = new Parcelable.Creator<ClienteModel>() {
        @Override
        public ClienteModel createFromParcel(Parcel source) {
            return new ClienteModel(source);
        }

        @Override
        public ClienteModel[] newArray(int size) {
            return new ClienteModel[size];
        }
    };
}
