/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.konrad.project1.ntd.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author PC
 */
@Entity
@Table(name="comentario_producto")
public class ComentarioProductoEntity implements Serializable{
    /**
     * llave primaria del comentario
     */
    @Id
    @Column(name="id_comentario", unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * comentarios del usuario hacia el producto
     */
    @Column(name="comentario_comentario", unique=false)
    private String comentario;
    /**
     * valoracion del comentario
     */
    @Column(name="valoracion_comentario", unique=false)
    private int valoracion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }
}
