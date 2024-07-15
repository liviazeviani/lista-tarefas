package br.com.liviazeviani.lista_tarefas.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.liviazeviani.lista_tarefas.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

    
}
