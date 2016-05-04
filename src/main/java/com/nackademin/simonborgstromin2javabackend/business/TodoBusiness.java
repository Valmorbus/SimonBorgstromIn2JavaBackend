/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nackademin.simonborgstromin2javabackend.business;

import com.nackademin.simonborgstromin2javabackend.facades.TodoFacade;
import com.nackademin.simonborgstromin2javabackend.entities.Todo;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author borgs_000
 */
@Stateless
public class TodoBusiness {

    @Inject
    TodoFacade tf;
    
    public void addTodo(Todo todo){
        tf.create(todo);
    }
    public void addTodo(String description, String date, boolean done){
        Todo todo = new Todo(description, date, done);
        tf.create(todo);
    }
    public void update(Todo todo){
        tf.edit(todo);
    }
    public void delete(Todo todo){
        tf.remove(todo);
    }
    
    public List<Todo> findAll(){
        return tf.findAll();
    }
    public void findTodo(Todo todo){
        tf.find(todo);
    }

    public Todo findById(int i) {
      return tf.find(i);
    }
    
  
}
