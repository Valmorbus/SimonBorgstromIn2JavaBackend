/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nackademin.simonborgstromin2javabackend.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author borgs_000
 */
@Entity
@Table(name = "TODO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Todo.findAll", query = "SELECT t FROM Todo t"),
    @NamedQuery(name = "Todo.findById", query = "SELECT t FROM Todo t WHERE t.id = :id"),
    @NamedQuery(name = "Todo.findByDescription", query = "SELECT t FROM Todo t WHERE t.description = :description"),
    @NamedQuery(name = "Todo.findByDuedate", query = "SELECT t FROM Todo t WHERE t.duedate = :duedate"),
    @NamedQuery(name = "Todo.findByDone", query = "SELECT t FROM Todo t WHERE t.done = :done")})
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DUEDATE")
    @Temporal(TemporalType.DATE)
    private Date duedate;
    @Column(name = "DONE")
    private Boolean done;

    public Todo() {
    }

    public Todo(Integer id) {
        this.id = id;
    }

    public Todo(Integer id, String description, Date duedate) {
        this.id = id;
        this.description = description;
        this.duedate = duedate;
    }

    public Todo(String description, Date duedate, boolean done) {
      this.description = description;
      this.duedate = duedate;
      this.done = done;
      
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Todo)) {
            return false;
        }
        Todo other = (Todo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nackademin.simonborgstromin2javabackend.entities.Todo[ id=" + id + " ]";
    }
    
}
