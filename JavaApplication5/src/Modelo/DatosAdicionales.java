/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author LAPC
 */
@Entity
@Table(name = "DATOS_ADICIONALES")
@NamedQueries({
    @NamedQuery(name = "DatosAdicionales.findAll", query = "SELECT d FROM DatosAdicionales d")})
public class DatosAdicionales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "REFERENCIA")
    private String referencia;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DATOS_ADICIONALES")
    private Integer idDatosAdicionales;
    @Column(name = "FECHA_ASENTAMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaAsentamiento;
    @Column(name = "FECHA_REGULARIZACION")
    @Temporal(TemporalType.DATE)
    private Date fechaRegularizacion;

    public DatosAdicionales() {
    }

    public DatosAdicionales(Integer idDatosAdicionales) {
        this.idDatosAdicionales = idDatosAdicionales;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Integer getIdDatosAdicionales() {
        return idDatosAdicionales;
    }

    public void setIdDatosAdicionales(Integer idDatosAdicionales) {
        this.idDatosAdicionales = idDatosAdicionales;
    }

    public Date getFechaAsentamiento() {
        return fechaAsentamiento;
    }

    public void setFechaAsentamiento(Date fechaAsentamiento) {
        this.fechaAsentamiento = fechaAsentamiento;
    }

    public Date getFechaRegularizacion() {
        return fechaRegularizacion;
    }

    public void setFechaRegularizacion(Date fechaRegularizacion) {
        this.fechaRegularizacion = fechaRegularizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDatosAdicionales != null ? idDatosAdicionales.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosAdicionales)) {
            return false;
        }
        DatosAdicionales other = (DatosAdicionales) object;
        if ((this.idDatosAdicionales == null && other.idDatosAdicionales != null) || (this.idDatosAdicionales != null && !this.idDatosAdicionales.equals(other.idDatosAdicionales))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.DatosAdicionales[ idDatosAdicionales=" + idDatosAdicionales + " ]";
    }
    
}
