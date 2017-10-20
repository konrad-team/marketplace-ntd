/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.konrad.project1.ntd.ejb;

import co.konrad.project1.ntd.ejb.exceptions.NonexistentEntityException;
import co.konrad.project1.ntd.ejb.exceptions.PreexistingEntityException;
import co.konrad.project1.ntd.ejb.exceptions.RollbackFailureException;
import co.konrad.project1.ntd.entities.FacturaEntity;
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
public class FacturaEntityJpaController implements Serializable {

    public FacturaEntityJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FacturaEntity facturaEntity) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(facturaEntity);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findFacturaEntity(facturaEntity.getFacturaId()) != null) {
                throw new PreexistingEntityException("FacturaEntity " + facturaEntity + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FacturaEntity facturaEntity) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            facturaEntity = em.merge(facturaEntity);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = facturaEntity.getFacturaId();
                if (findFacturaEntity(id) == null) {
                    throw new NonexistentEntityException("The facturaEntity with id " + id + " no longer exists.");
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
            FacturaEntity facturaEntity;
            try {
                facturaEntity = em.getReference(FacturaEntity.class, id);
                facturaEntity.getFacturaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facturaEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(facturaEntity);
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

    public List<FacturaEntity> findFacturaEntityEntities() {
        return findFacturaEntityEntities(true, -1, -1);
    }

    public List<FacturaEntity> findFacturaEntityEntities(int maxResults, int firstResult) {
        return findFacturaEntityEntities(false, maxResults, firstResult);
    }

    private List<FacturaEntity> findFacturaEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FacturaEntity.class));
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

    public FacturaEntity findFacturaEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FacturaEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FacturaEntity> rt = cq.from(FacturaEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
