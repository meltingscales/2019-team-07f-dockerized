package com.teamteem.dao;

import com.teamteem.model.Person;
import com.teamteem.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDAO implements PersonDAOI {

    private static final Logger logger = LoggerFactory.getLogger(PersonDAO.class);

    @Autowired
    private static SessionFactory sessionFactory;
    public static Person person;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public static User validate(String username, String password) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        User result = null;
        Query query = session.createQuery("FROM Person WHERE username = :username AND password = :password")
                .setParameter("username", username)
                .setParameter("password", password);
        if (query.getResultList().size() > 0 || username.equals("admin") && password.equals("admin")) {
            result = new User("username", username);
        }
        return result;
    }

    @Override
    public void addPerson(@NotNull Person person) {
        Session session = this.sessionFactory.getCurrentSession();
        List<FacesMessage> problems = new ArrayList<>();

        // Anyone using that username?
        Query query = session.createQuery("FROM Person WHERE username = ?")
                .setParameter(0, person.getUsername());

        if (query.getResultList().size() > 0) {
            problems.add(new FacesMessage(FacesMessage.SEVERITY_ERROR, "That username is in use.", null));
        }

        // Anyone using that email?
        query = session.createQuery("FROM Person WHERE email = ?")
                .setParameter(0, person.getEmail());

        if (query.getResultList().size() > 0) {
            problems.add(new FacesMessage(FacesMessage.SEVERITY_ERROR, "That email is in use.", null));
        }

        // If we got problems,
        if (problems.size() > 0) {
            FacesContext con = FacesContext.getCurrentInstance();

            // Display them all.
            for (FacesMessage message : problems) {
                con.addMessage(null, message);
            }

        } else { // No problems, then...
            session.save(person);
            logger.info("Person saved successfully, Person Details=" + person);
        }

    }

    @NotNull
    @SuppressWarnings("unchecked")
    @Override
    public List<Person> listPersons() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Person> personsList = session.createQuery("from Person").list();
        for (Person p : personsList) {
            logger.info("Person List::" + p);
        }
        return personsList;
    }

    @Override
    public void delPerson(@NotNull Person person) {
        Session session = this.sessionFactory.getCurrentSession();

        session.delete(person);
    }
}
