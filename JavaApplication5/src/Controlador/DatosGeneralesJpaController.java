/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Controlador.exceptions.PreexistingEntityException;
import Modelo.DatosGenerales;
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
public class DatosGeneralesJpaController implements Serializable {

    public DatosGeneralesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DatosGenerales datosGenerales) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(datosGenerales);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatosGenerales(datosGenerales.getIdTabla2()) != null) {
                throw new PreexistingEntityException("DatosGenerales " + datosGenerales + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DatosGenerales datosGenerales) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            datosGenerales = em.merge(datosGenerales);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = datosGenerales.getIdTabla2();
                if (findDatosGenerales(id) == null) {
                    throw new NonexistentEntityException("The datosGenerales with id " + id + " no longer exists.");
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
            DatosGenerales datosGenerales;
            try {
                datosGenerales = em.getReference(DatosGenerales.class, id);
                datosGenerales.getIdTabla2();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datosGenerales with id " + id + " no longer exists.", enfe);
            }
            em.remove(datosGenerales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DatosGenerales> findDatosGeneralesEntities() {
        return findDatosGeneralesEntities(true, -1, -1);
    }

    public List<DatosGenerales> findDatosGeneralesEntities(int maxResults, int firstResult) {
        return findDatosGeneralesEntities(false, maxResults, firstResult);
    }

    private List<DatosGenerales> findDatosGeneralesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DatosGenerales.class));
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

    public DatosGenerales findDatosGenerales(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatosGenerales.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatosGeneralesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DatosGenerales> rt = cq.from(DatosGenerales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
