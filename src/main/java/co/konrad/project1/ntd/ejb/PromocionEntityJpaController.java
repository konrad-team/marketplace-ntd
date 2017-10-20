/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.konrad.project1.ntd.ejb;

import co.konrad.project1.ntd.ejb.exceptions.NonexistentEntityException;
import co.konrad.project1.ntd.ejb.exceptions.PreexistingEntityException;
import co.konrad.project1.ntd.ejb.exceptions.RollbackFailureException;
import co.konrad.project1.ntd.entities.PromocionEntity;
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
public class PromocionEntityJpaController implements Serializable {

    public PromocionEntityJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PromocionEntity promocionEntity) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(promocionEntity);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPromocionEntity(promocionEntity.getPromocionId()) != null) {
                throw new PreexistingEntityException("PromocionEntity " + promocionEntity + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PromocionEntity promocionEntity) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            promocionEntity = em.merge(promocionEntity);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = promocionEntity.getPromocionId();
                if (findPromocionEntity(id) == null) {
                    throw new NonexistentEntityException("The promocionEntity with id " + id + " no longer exists.");
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
            PromocionEntity promocionEntity;
            try {
                promocionEntity = em.getReference(PromocionEntity.class, id);
                promocionEntity.getPromocionId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The promocionEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(promocionEntity);
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

    public List<PromocionEntity> findPromocionEntityEntities() {
        return findPromocionEntityEntities(true, -1, -1);
    }

    public List<PromocionEntity> findPromocionEntityEntities(int maxResults, int firstResult) {
        return findPromocionEntityEntities(false, maxResults, firstResult);
    }

    private List<PromocionEntity> findPromocionEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PromocionEntity.class));
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

    public PromocionEntity findPromocionEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PromocionEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getPromocionEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PromocionEntity> rt = cq.from(PromocionEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
