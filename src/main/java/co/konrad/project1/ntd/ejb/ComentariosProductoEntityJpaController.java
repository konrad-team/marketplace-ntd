/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.konrad.project1.ntd.ejb;

import co.konrad.project1.ntd.ejb.exceptions.NonexistentEntityException;
import co.konrad.project1.ntd.ejb.exceptions.PreexistingEntityException;
import co.konrad.project1.ntd.ejb.exceptions.RollbackFailureException;
import co.konrad.project1.ntd.entities.ComentariosProductoEntity;
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
public class ComentariosProductoEntityJpaController implements Serializable {

    public ComentariosProductoEntityJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ComentariosProductoEntity comentariosProductoEntity) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(comentariosProductoEntity);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findComentariosProductoEntity(comentariosProductoEntity.getComentariosProductoId()) != null) {
                throw new PreexistingEntityException("ComentariosProductoEntity " + comentariosProductoEntity + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ComentariosProductoEntity comentariosProductoEntity) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            comentariosProductoEntity = em.merge(comentariosProductoEntity);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = comentariosProductoEntity.getComentariosProductoId();
                if (findComentariosProductoEntity(id) == null) {
                    throw new NonexistentEntityException("The comentariosProductoEntity with id " + id + " no longer exists.");
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
            ComentariosProductoEntity comentariosProductoEntity;
            try {
                comentariosProductoEntity = em.getReference(ComentariosProductoEntity.class, id);
                comentariosProductoEntity.getComentariosProductoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comentariosProductoEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(comentariosProductoEntity);
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

    public List<ComentariosProductoEntity> findComentariosProductoEntityEntities() {
        return findComentariosProductoEntityEntities(true, -1, -1);
    }

    public List<ComentariosProductoEntity> findComentariosProductoEntityEntities(int maxResults, int firstResult) {
        return findComentariosProductoEntityEntities(false, maxResults, firstResult);
    }

    private List<ComentariosProductoEntity> findComentariosProductoEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ComentariosProductoEntity.class));
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

    public ComentariosProductoEntity findComentariosProductoEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ComentariosProductoEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getComentariosProductoEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ComentariosProductoEntity> rt = cq.from(ComentariosProductoEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
