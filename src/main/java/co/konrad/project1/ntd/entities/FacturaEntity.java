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
@Table(name="factura")
public class FacturaEntity implements Serializable{

    /**
     * llave primaria de la factura
     */
    @Id
    @Column(name="id_factura", unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * fecha de la factura
     */
    @Column(name="fecha_factura", unique=false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    /**
     * valor total de la factura
     */
    @Column(name="valor_total_factura", unique=false)
    private Long valorTotal;
    /**
     * llave foranea de la factura al metodo de pago
     */
    @ManyToOne
    @JoinColumn(name="id_metodo_pago")
    private MetodoPagoEntity metodoPago;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Long valorTotal) {
        this.valorTotal = valorTotal;
    }

    public MetodoPagoEntity getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPagoEntity metodoPago) {
        this.metodoPago = metodoPago;
    }
}
