package com.ex.app.service;

import com.ex.app.persistence.Persistable;
import com.ex.app.persistence.StatPersistence;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StatService implements Persistable, ApplicationContextAware,
        BeanNameAware, BeanFactoryAware, InitializingBean, BeanPostProcessor, DisposableBean {

    private StatPersistence persistence;

    @Autowired
    public void setPersistence(StatPersistence statPersistence) {
        this.persistence = statPersistence;
    }

    @Override
    @Transactional
    public List getAll() {
        return persistence.getAll();
    }

    @Transactional
    public List getByName(String firstname, String lastname) {
        return persistence.getByName(firstname, lastname);
    }

    @Transactional
    public List getByCategory(String category) {
        return persistence.getByCategory(category);
    }

    @Transactional
    public List getByTeam(String team) {
        return persistence.getByTeam(team);
    }

    @Transactional
    public List getLeaders(String category) {
        return persistence.getLeaders(category);
    }

    @Override
    @Transactional
    public Object getById(Object id) {
        return null;
    }

    @Override
    @Transactional
    public void addNew(Object obj) {
        persistence.addNew(obj);
    }

    @Override
    @Transactional
    public void update(Object obj) {
        persistence.update(obj);
    }

    @Override
    @Transactional
    public void delete(Object obj) {
        persistence.delete(obj);
    }

    @Override
    @Transactional
    public boolean authenticate(String username, String password) {
        return false;
    }

    @Override
    @Transactional
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    @Transactional
    public void setBeanName(String s) {

    }

    @Override
    @Transactional
    public void destroy() throws Exception {

    }

    @Override
    @Transactional
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    @Transactional
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
