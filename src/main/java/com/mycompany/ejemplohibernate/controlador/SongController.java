package com.mycompany.ejemplohibernate.controlador;

import com.mycompany.ejemplohibernate.controlador.exceptions.NonexistentEntityException;
import com.mycompany.ejemplohibernate.controlador.exceptions.PreexistingEntityException;
import com.mycompany.ejemplohibernate.modelo.Song;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class SongController implements Serializable {

    public SongController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Song song) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(song);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSong(song.getId()) != null) {
                throw new PreexistingEntityException("Song " + song + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Song song) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            song = em.merge(song);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = song.getId();
                if (findSong(id) == null) {
                    throw new NonexistentEntityException("The song with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Song song;
            try {
                song = em.getReference(Song.class, id);
                song.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The song with id " + id + " no longer exists.", enfe);
            }
            em.remove(song);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Song> findAll() {
        return findSongEntities(true, -1, -1);
    }

    public List<Song> findSongEntities(int maxResults, int firstResult) {
        return findSongEntities(false, maxResults, firstResult);
    }

    private List<Song> findSongEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Song.class));
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

    public Song findSong(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Song.class, id);
        } finally {
            em.close();
        }
    }

    public int getSongCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Song> rt = cq.from(Song.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
