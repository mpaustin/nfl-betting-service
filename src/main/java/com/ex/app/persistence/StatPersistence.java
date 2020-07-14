package com.ex.app.persistence;

import com.ex.app.entities.Player;
import com.ex.app.entities.Stat;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@NoArgsConstructor
@Repository("statPersistence")
public class StatPersistence implements Persistable {

    private final Logger LOG = Logger.getLogger(this.getClass());

    private SessionFactory sessionFactory;
    private EntityManager em;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List getAll() {
        LOG.info("Trying to return all stats");

        String query = "FROM Player";
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery(query);

        List<Player> statistics = q.list();

        LOG.debug("Successfully got all stats");
        return statistics;
    }

    public List getLeaders(String category) {
        LOG.info("Trying to return stat leaders");

        String query = "FROM Player WHERE " + category + "order by " + category + "desc limit 10";
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery(query);

        List<Player> statistics = q.list();

        LOG.debug("Successfully got all leaders");
        return statistics;
    }

    public List<Player> getByName(Object obj1, Object obj2) {
        LOG.info("Trying to get player by name");

        String query = "FROM Player p WHERE p.firstname = :firstname AND p.lastname = :lastname";
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery(query);
        q.setParameter("firstname", obj1);
        q.setParameter("lastname", obj2);

        List<Player> statistics = q.list();

        LOG.debug("Successfully retrieved player by name");

        return statistics;
    }

    public List<Player> getByCategory(String category) {
        LOG.info("Trying to get stats by category");

        String query = "Select lastname, firstname, teamAbbr, position, " + category + "from Player";
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery(query);

        List<Player> statistics = q.list();

        LOG.debug("Successfully retrieved stats by category");

       return statistics;
    }

    public List<Player> getByTeam(String team) {
        LOG.info("Trying to get stats by team");

        String query = "FROM Player p WHERE p.teamAbbr = :team";
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery(query);
        q.setParameter("team", team);

        List<Player> statistics = q.list();

        LOG.debug("Successfully retrieved stats by team");

        return statistics;
    }


    @Override
    public Object getById(Object id) {
        return null;
    }

    @Override
    public void addNew(Object obj) {

    }

    @Override
    public void update(Object obj) {

    }

    @Override
    public void delete(Object obj) {

    }

    @Override
    public boolean authenticate(String username, String password) {
        return false;
    }
}
