package com.example.contactosg5fireb.models;

import java.io.Serializable;

public class ContactoModel implements Serializable {
    private String _id;
    private String _nombre;
    private String _apellido;
    private String _numeroCelular;
    private String _numeroFijo;
    private String _numeroTrabajo;
    private String _correoElectronico;

    public ContactoModel() {
    }

    public ContactoModel(String _nombre, String _apellido, String _numeroCelular, String _numeroFijo, String _numeroTrabajo, String _correoElectronico) {
        this._nombre = _nombre;
        this._apellido = _apellido;
        this._numeroCelular = _numeroCelular;
        this._numeroFijo = _numeroFijo;
        this._numeroTrabajo = _numeroTrabajo;
        this._correoElectronico = _correoElectronico;
    }

    public ContactoModel(String _id, String _nombre, String _apellido, String _numeroCelular, String _numeroFijo, String _numeroTrabajo, String _correoElectronico) {
        this._id = _id;
        this._nombre = _nombre;
        this._apellido = _apellido;
        this._numeroCelular = _numeroCelular;
        this._numeroFijo = _numeroFijo;
        this._numeroTrabajo = _numeroTrabajo;
        this._correoElectronico = _correoElectronico;
    }

    @Override
    public String toString() {
        return "ContactoModel{" +
                "_id=" + _id +
                ", _nombre='" + _nombre + '\'' +
                ", _apellido='" + _apellido + '\'' +
                ", _numeroCelular='" + _numeroCelular + '\'' +
                ", _numeroFijo='" + _numeroFijo + '\'' +
                ", _numeroTrabajo='" + _numeroTrabajo + '\'' +
                ", _correoElectronico='" + _correoElectronico + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_apellido() {
        return _apellido;
    }

    public void set_apellido(String _apellido) {
        this._apellido = _apellido;
    }

    public String get_numeroCelular() {
        return _numeroCelular;
    }

    public void set_numeroCelular(String _numeroCelular) {
        this._numeroCelular = _numeroCelular;
    }

    public String get_numeroFijo() {
        return _numeroFijo;
    }

    public void set_numeroFijo(String _numeroFijo) {
        this._numeroFijo = _numeroFijo;
    }

    public String get_numeroTrabajo() {
        return _numeroTrabajo;
    }

    public void set_numeroTrabajo(String _numeroTrabajo) {
        this._numeroTrabajo = _numeroTrabajo;
    }

    public String get_correoElectronico() {
        return _correoElectronico;
    }

    public void set_correoElectronico(String _correoElectronico) {
        this._correoElectronico = _correoElectronico;
    }
}
