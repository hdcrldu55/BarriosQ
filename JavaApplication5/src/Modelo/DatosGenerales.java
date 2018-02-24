/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author LAPC
 */
@Entity
@Table(name = "DATOS_GENERALES")
@NamedQueries({
    @NamedQuery(name = "DatosGenerales.findAll", query = "SELECT d FROM DatosGenerales d")})
public class DatosGenerales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "HOST")
    private String host;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TABLA_2")
    private Integer idTabla2;

    public DatosGenerales() {
    }

    public DatosGenerales(Integer idTabla2) {
        this.idTabla2 = idTabla2;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getIdTabla2() {
        return idTabla2;
    }

    public void setIdTabla2(Integer idTabla2) {
        this.idTabla2 = idTabla2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTabla2 != null ? idTabla2.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosGenerales)) {
            return false;
        }
        DatosGenerales other = (DatosGenerales) object;
        if ((this.idTabla2 == null && other.idTabla2 != null) || (this.idTabla2 != null && !this.idTabla2.equals(other.idTabla2))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.DatosGenerales[ idTabla2=" + idTabla2 + " ]";
    }
    
}
