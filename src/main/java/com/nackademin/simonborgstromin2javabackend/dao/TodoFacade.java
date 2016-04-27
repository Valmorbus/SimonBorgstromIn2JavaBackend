/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nackademin.simonborgstromin2javabackend.dao;

import com.nackademin.simonborgstromin2javabackend.entities.Todo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author borgs_000
 */
@Stateless
public class TodoFacade extends AbstractFacade<Todo> {

    @PersistenceContext(unitName = "persistenseUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TodoFacade() {
        super(Todo.class);
    }
    
}
