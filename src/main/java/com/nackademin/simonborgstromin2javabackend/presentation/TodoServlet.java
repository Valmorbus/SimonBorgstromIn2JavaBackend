/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nackademin.simonborgstromin2javabackend.presentation;

import com.nackademin.simonborgstromin2javabackend.business.TodoBusiness;
import com.nackademin.simonborgstromin2javabackend.entities.Todo;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author borgs_000
 */
@WebServlet(name = "TodoServlet", urlPatterns = {"/myservlet"})
public class TodoServlet extends HttpServlet {

    public static final String DESCRIPTION = "description";
    public static final String DUEDATE = "duedate";
    public static final String DONE = "done";
    public static final String KEY = "key";
    private int count = 0;


    @Inject
    TodoBusiness tb;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String description = req.getParameter(DESCRIPTION);
        String duedate = req.getParameter(DUEDATE);
        String doneString = req.getParameter(DONE);
        String keyString = req.getParameter(KEY);
        System.out.println("connected" +keyString);
        if (keyString == null) {
            try {
                tb.addTodo(description, stringToDate(duedate), false);
            } catch (ParseException ex) {
                Logger.getLogger(TodoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (keyString != null) {
             Todo todo = tb.findById((Integer.valueOf(keyString)+1));
               System.out.println(todo);
           //  System.out.println(keyString + "keyString");        
            if (duedate != null) {
                System.out.println(todo);
                 try {
                     todo.setDuedate(stringToDate(duedate));
                     tb.update(todo);
                     System.out.println(todo.getDuedate());
                 } catch (ParseException ex) {
                     Logger.getLogger(TodoServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }
            if (doneString != null) {
                   todo.setDone(Boolean.TRUE);
                   tb.update(todo);

            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //isDone(doneString, resp);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        System.out.print("get");
       
        List<Todo> todoList = tb.findAll();
        String objectToReturn = "[";
        for (Todo todo : todoList) {
            count++;
            String value = todoToJson(todo);
            objectToReturn += value;
            if (count != todoList.size()) {
                objectToReturn += ",";
            }
        }
        objectToReturn += "]";
        System.out.println(objectToReturn);
        PrintWriter out = resp.getWriter();
        out.print(objectToReturn);
        out.close();

    }

    private Date stringToDate(String string) throws ParseException {
        System.out.println("Häör " +string);
        DateFormat formatter;
        Date date;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        date = formatter.parse(string);
        System.out.print(date);
        return date;
    }
    
    private String todoToJson(Todo todo){
        if (todo.getDone())
         return "{"+"\"Description\""+":\""+todo.getDescription()+"\","+"\"Duedate\""+":\""+dateToString(todo.getDuedate())+"\""+
                ","+"\"Done\""+":\""+"checked"+"\"}";
        else
             return "{"+"\"Description\""+":\""+todo.getDescription()+"\","+"\"Duedate\""+":\""+dateToString(todo.getDuedate())+"\""+
                ","+"\"Done\""+":\""+""+"\"}";
    }
    
    private String dateToString(Date date){
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = DATE_FORMAT.format(date);
        return dateString;
    }

}
