/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import models.Task;

/**
 *
 * @author Pelle
 */
@Stateless
public class TaskFacade extends AbstractFacade<Task> {
    @PersistenceContext(unitName = "se.karlsson_TodoList-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TaskFacade() {
        super(Task.class);
    }
    
    public List<Task> getAll() {
        Query query = em.createQuery("SELECT e FROM Task e");
        return query.getResultList();
    }
    
    public void create(Task task) {
        List<Task> tasks = getAll();
        int maxId = 0;
        for(Task task2 : tasks) {
            int taskId = task2.getId();
            if (taskId > maxId) {
                maxId = taskId;
            }
        }
        task.setId(maxId + 1);
        em.persist(task);
    }

    public void delete(int id) {
        List<Task> tasks = getAll();
        for(Task task : tasks) {
            int taskId = task.getId();
            if (taskId == id) {
                em.remove(task);
            }
        }
    }
    
}
