package com.ex.app.persistence;

import com.ex.app.entities.User;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@NoArgsConstructor
@Repository("userPersistence")
public class UserPersistence implements Persistable{
    private final Logger LOG = Logger.getLogger(this.getClass());

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean authenticate(String username, String password) {

        // authenticate user-entered username and password
        LOG.debug("Attempting to authenticate user");

        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, username);
        if(user == null) {
            LOG.debug("Username not found");
            return false;
        } else {
            String p = user.getPassword();
            if(!password.equals(p)) {
                LOG.debug("Password not a match");
                return false;
            } else {
                LOG.debug("Authenticated");
                return true;
            }
        }
    }

    @Override
    public List getAll() {

        LOG.debug("Attempting to get all users");

        String query = "FROM User";
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery(query);

        List<User> users = q.list();

        LOG.debug("Successfully got all users");
        return users;
    }

    @Override
    public Object getById(Object id) {

        LOG.debug("Attempting to get user by ID");

        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, (String)id);
        LOG.debug("Successfully got user by ID");
        return user;
    }

    @Override
    public void addNew(Object obj) {

        LOG.debug("Attempting to add new user");
        Session session = sessionFactory.getCurrentSession();
        session.save(obj);
        LOG.debug("Successfully added new user");
    }

    @Override
    public void update(Object obj) {

        LOG.debug("Attempting to update user");
        Session session = sessionFactory.getCurrentSession();
        session.update(obj);
        LOG.debug("Successfully updated user");
    }

    @Override
    public void delete(Object obj) {

        LOG.debug("Attempting to delete user");
        Session session = sessionFactory.getCurrentSession();
        session.delete(obj);
        LOG.debug("Successfully deleted user");
    }
}
