package br.com.liviazeviani.lista_tarefas.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.liviazeviani.lista_tarefas.entity.Todo;
import br.com.liviazeviani.lista_tarefas.respository.TodoRepository;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }
    
    public List<Todo> create( Todo todo){
        todoRepository.save(todo);
        return list();

    }

    public List<Todo> list(){
        Sort sort = Sort.by( "priorization").descending().and(
            Sort.by( "name").ascending()
            );
        
        return todoRepository.findAll(sort);


    }

    public List<Todo> update(){

    }

    public List<Todo> delete(){

    }
}
