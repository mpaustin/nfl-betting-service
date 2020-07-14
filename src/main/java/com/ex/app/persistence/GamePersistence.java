package com.ex.app.persistence;

import com.ex.app.entities.Game;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@NoArgsConstructor
@Repository("gamePersistence")
public class GamePersistence implements Persistable{
    private final Logger LOG = Logger.getLogger(this.getClass());
    // TODO: logging

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List getAll() {

        String query = "FROM Games";
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery(query);

        List<Game> games = q.list();

        return games;
    }

    public List getAllCompleted() {

        String query = "FROM Games WHERE completed = :completed";
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery(query);
        q.setParameter("completed", true);

        List<Game> compGames = q.list();

        return compGames;
    }

    public List getAllNotCompleted() {

        String query = "FROM Games WHERE completed = :completed";
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery(query);
        q.setParameter("completed", false);

        List<Game> notCompGames = q.list();

        return notCompGames;
    }

    @Override
    public Object getById(Object id) {

        Session session = sessionFactory.getCurrentSession();
        Game game = session.get(Game.class, (Integer)id);
        return game;
    }

    @Override
    public void addNew(Object obj) {

        Session session = sessionFactory.getCurrentSession();
        session.save(obj);
    }

    @Override
    public void update(Object obj) {

        Session session = sessionFactory.getCurrentSession();
        session.update(obj);
    }

    @Override
    public void delete(Object obj) {

        Session session = sessionFactory.getCurrentSession();
        session.delete(obj);
    }

    @Override
    public boolean authenticate(String username, String password) {
        return false;
    }
}
