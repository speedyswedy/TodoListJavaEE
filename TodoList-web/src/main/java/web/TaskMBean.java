/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import controller.TaskFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import models.Task;

/**
 *
 * @author Pelle
 */
@ManagedBean(name = "task")
@SessionScoped
public class TaskMBean {
    @EJB
    private TaskFacade taskFacade;
    
    private String label;

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    /**
     * Creates a new instance of TaskMBean
     */
    public TaskMBean() {
    }
    
    public List<Task> getAllTasks() {
        return taskFacade.getAll();
    }
    
    public void delete(int id) {
        taskFacade.delete(id);
    }
    
    public void create() {
        Task task = new Task();
        task.setLabel(label);
        taskFacade.create(task);
    }
}
