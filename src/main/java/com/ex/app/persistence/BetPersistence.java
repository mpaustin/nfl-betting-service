package com.ex.app.persistence;

import com.ex.app.entities.Bet;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("ALL")
@NoArgsConstructor
@Repository("betPersistence")
public class BetPersistence implements Persistable{
    private final Logger LOG = Logger.getLogger(this.getClass());

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List getAll() {

        LOG.debug("Attempting to get all bets");

        String query = "FROM Bet";
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery(query);

        List<Bet> users = q.list();

        LOG.debug("Successfully got all bets");
        return users;

    }

    public List getAllCompleted() {

        LOG.debug("Attempting to get all completed bets");

        String query = "FROM Bet WHERE completed = :completed";
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery(query);
        q.setParameter("completed", true);

        List<Bet> compBets = q.list();

        LOG.debug("Successfully got all completed bets");
        return compBets;
    }

    public List getAllNotCompleted() {

        LOG.debug("Attempting to get all not completed bets");

        String query = "FROM Bet WHERE completed = :completed";
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery(query);
        q.setParameter("completed", false);

        List<Bet> notCompBets = q.list();

        LOG.debug("Successfully got all not completed bets");
        return notCompBets;
    }

    public List getUserAllBets(Object user) {

        LOG.debug("Attempting to get user all bets");

        String query = "FROM Bet WHERE betUser = :user";
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery(query);
        q.setParameter("user", user);

        List<Bet> userBets = q.list();

        LOG.debug("Successfully got user all bets");
        return userBets;
    }

    public List getUserCompBets(Object username) {

        LOG.debug("Attempting to get user completed bets");

        String query = "FROM Bet WHERE betUser = :username AND completed = :completed";
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery(query);
        q.setParameter("username", username);
        q.setParameter("completed", true);

        List<Bet> userCompBets = q.list();

        LOG.debug("Successfully got user completed bets");
        return userCompBets;
    }

    public List getUserNotCompBets(Object username) {

        LOG.debug("Attempting to get user not completed bets");
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Bet WHERE betUser = :username AND completed = :completed";

        Query q = session.createQuery(query);
        q.setParameter("username", username);
        q.setParameter("completed", false);

        List<Bet> userNotCompBets = q.list();

        LOG.debug("Successfully got user not completed bets");
        return userNotCompBets;
    }

    @Override
    public Object getById(Object id) {

        LOG.debug("Attempting to get bet by ID");
        Session session = sessionFactory.getCurrentSession();
        Bet bet = session.get(Bet.class, (Integer)id);

        LOG.debug("Successfully got bet by ID");
        return bet;
    }

    @Override
    public void addNew(Object obj) {

        LOG.debug("Attempting to add new bet");
        Session session = sessionFactory.getCurrentSession();
        session.save(obj);
        LOG.debug("Successfully added new bet");
    }

    @Override
    public void update(Object obj) {

        LOG.debug("Attempting to update bet");
        Session session = sessionFactory.getCurrentSession();
        session.update(obj);
        LOG.debug("Successfully updated bet");
    }

    @Override
    public void delete(Object obj) {

        LOG.debug("Attempting to delete bet");
        Session session = sessionFactory.getCurrentSession();
        session.delete(obj);
        LOG.debug("Successfully deleted bet");
    }

    @Override
    public boolean authenticate(String username, String password) {
        return false;
    }
}
