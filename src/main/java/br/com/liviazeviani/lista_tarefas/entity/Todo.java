package br.com.liviazeviani.lista_tarefas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private boolean realized;
    private int priorization;

   
    public Todo(String name, String description, boolean realized, int priorization) {
        this.name = name;
        this.description = description;
        this.realized = realized;
        this.priorization = priorization;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isRealized() {
        return realized;
    }
    public void setRealized(boolean realized) {
        this.realized = realized;
    }
    public int getPriorization() {
        return priorization;
    }
    public void setPriorization(int priorization) {
        this.priorization = priorization;
    }

    

}
