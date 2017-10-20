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
@Table(name="cliente")
public class ClienteEntity implements Serializable{
    /**
     * llave primaria del cliente
     */
    @Id
    @Column(name="id_cliente", unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * tipo de documento del cliente
     */
    @Column(name="tipo_documento_cliente")
    private String tipoDocumento;
    /**
     * número del documento del cliente
     */
    @Column(name="numero_documento_cliente")
    private Long numeroDocumento;
    /**
     * nombre del cliente
     */
    @Column(name="nombre_cliente")
    private String nombre;
    /**
     * apellido del cliente
     */
    @Column(name="apellido_cliente")
    private String apellido;
    /**
     * teléfono del cliente
     */
    @Column(name="telefono_cliente")
    private Long telefono;
    /**
     * corréo del cliente
     */
    @Column(name="email_cliente", unique=true)
    private String email;
    /**
     * fecha de nacimiento del cliente
     */
    @Column(name="fecha_nacimiento_cliente")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    /**
     * dirección del cliente
     */
    @Column(name="direccion_cliente")
    private String direccion;
    /**
     * ciudad del cliente
     */
    @Column(name="ciudad_cliente")
    private String ciudad;
    /**
     * pais del cliente
     */
    @Column(name="pais_cliente")
    private String pais;
    /**
     * nombre de usuario del cliente
     */
    @Column(name="usuario_cliente")
    private String usuario;
    /**
     * contraseña del cliente
     */
    @Column(name="password_cliente")
    private String password;
    /**
     * imagen del cliente
     */
    @Lob
    @Column(name="imagen_cliente", length=100000)
    private byte[] imagen;
    /**
     * llave foranea del cliente a envio
     */
    @ManyToOne
    @JoinColumn(name="id_envio")
    private EnvioEntity envio;
    /**
     * llave foranea del cliente al carrito de compras
     */
    @ManyToOne
    @JoinColumn(name="id_carrito")
    private CarritoEntity carrito;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Long getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public EnvioEntity getEnvio() {
        return envio;
    }

    public void setEnvio(EnvioEntity envio) {
        this.envio = envio;
    }

    public CarritoEntity getCarrito() {
        return carrito;
    }

    public void setCarrito(CarritoEntity carrito) {
        this.carrito = carrito;
    }
}
