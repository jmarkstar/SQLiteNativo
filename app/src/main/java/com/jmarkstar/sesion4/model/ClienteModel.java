package com.jmarkstar.sesion4.model;

/**
 * Created by jmarkstar on 11/05/2017.
 */

public class ClienteModel {

    public static final String TABLE_NAME = "cliente";
    public static final String ID_FIELD = "id";
    public static final String NOMBRE_FIELD = "nombre_completo";
    public static final String EMAIL_FIELD = "email";
    public static final String TELEFONO_FIELD = "telefono";
    public static final String DIRECCION_FIELD = "direccion";
    public static final String ID_EMPRESA_FIELD = "id_empresa";
    public static final String FECHA_NAC_FIELD = "fecha_nac";
    public static final String ID_ESTADO_CIVIL_FIELD = "id_estado_civil";

    private Integer id;
    private String nombreCompleto;
    private String email;
    private String telefono;
    private String direccion;
    private Integer idEmpresaCliente;
    private String fechNacimiento;
    private Integer idEstadoCivil;

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
}
