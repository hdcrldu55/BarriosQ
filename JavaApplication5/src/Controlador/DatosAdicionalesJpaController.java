/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Modelo.DatosAdicionales;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author LAPC
 */
public class DatosAdicionalesJpaController implements Serializable {

    public DatosAdicionalesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DatosAdicionales datosAdicionales) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(datosAdicionales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DatosAdicionales datosAdicionales) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            datosAdicionales = em.merge(datosAdicionales);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = datosAdicionales.getIdDatosAdicionales();
                if (findDatosAdicionales(id) == null) {
                    throw new NonexistentEntityException("The datosAdicionales with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatosAdicionales datosAdicionales;
            try {
                datosAdicionales = em.getReference(DatosAdicionales.class, id);
                datosAdicionales.getIdDatosAdicionales();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datosAdicionales with id " + id + " no longer exists.", enfe);
            }
            em.remove(datosAdicionales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DatosAdicionales> findDatosAdicionalesEntities() {
        return findDatosAdicionalesEntities(true, -1, -1);
    }

    public List<DatosAdicionales> findDatosAdicionalesEntities(int maxResults, int firstResult) {
        return findDatosAdicionalesEntities(false, maxResults, firstResult);
    }

    private List<DatosAdicionales> findDatosAdicionalesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DatosAdicionales.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public DatosAdicionales findDatosAdicionales(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatosAdicionales.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatosAdicionalesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DatosAdicionales> rt = cq.from(DatosAdicionales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
