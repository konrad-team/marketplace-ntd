/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.konrad.project1.ntd.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author PC
 */
@Entity
@Table(name="metodo_pago")
public class MetodoPagoEntity implements Serializable{
    /**
     * llave primaria del metodo de pago
     */
    @Id
    @Column(name="id_metodo_pago", unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * nombre del metodo de pago
     */
    @Column(name="nombre_metodo_pago")
    private String nombre;
    /**
     * detalle del metodo de pago
     */
    @Column(name="detalle_metodo_pago")
    private String detalle;
    /**
     * banco del metodo de pago
     */
    @Column(name="banco_metodo_pago")
    private String banco;
    /**
     * numero cuenta del metodo de pago
     */
    @Column(name="numero_cuenta_metodo_pago")
    private Long numeroCuenta;
    /**
     * fecha de vencimiento del metodo de pago
     */
    @Column(name="fecha_vencimiento_metodo_pago")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    /**
     * clave del metodo de pago
     */
    @Column(name="clave_metodo_pago")
    private Long clave;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Long getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Long getClave() {
        return clave;
    }

    public void setClave(Long clave) {
        this.clave = clave;
    }
}
