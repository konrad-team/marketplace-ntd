/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.konrad.project1.ntd.ejb;

import co.konrad.project1.ntd.ejb.exceptions.NonexistentEntityException;
import co.konrad.project1.ntd.ejb.exceptions.PreexistingEntityException;
import co.konrad.project1.ntd.ejb.exceptions.RollbackFailureException;
import co.konrad.project1.ntd.entities.MetodoPagoEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author PC
 */
public class MetodoPagoEntityJpaController implements Serializable {

    public MetodoPagoEntityJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MetodoPagoEntity metodoPagoEntity) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(metodoPagoEntity);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findMetodoPagoEntity(metodoPagoEntity.getMetodoPagoId()) != null) {
                throw new PreexistingEntityException("MetodoPagoEntity " + metodoPagoEntity + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MetodoPagoEntity metodoPagoEntity) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            metodoPagoEntity = em.merge(metodoPagoEntity);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = metodoPagoEntity.getMetodoPagoId();
                if (findMetodoPagoEntity(id) == null) {
                    throw new NonexistentEntityException("The metodoPagoEntity with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            MetodoPagoEntity metodoPagoEntity;
            try {
                metodoPagoEntity = em.getReference(MetodoPagoEntity.class, id);
                metodoPagoEntity.getMetodoPagoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The metodoPagoEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(metodoPagoEntity);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MetodoPagoEntity> findMetodoPagoEntityEntities() {
        return findMetodoPagoEntityEntities(true, -1, -1);
    }

    public List<MetodoPagoEntity> findMetodoPagoEntityEntities(int maxResults, int firstResult) {
        return findMetodoPagoEntityEntities(false, maxResults, firstResult);
    }

    private List<MetodoPagoEntity> findMetodoPagoEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MetodoPagoEntity.class));
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

    public MetodoPagoEntity findMetodoPagoEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MetodoPagoEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getMetodoPagoEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MetodoPagoEntity> rt = cq.from(MetodoPagoEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
