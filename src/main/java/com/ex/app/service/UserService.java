package com.ex.app.service;

import com.ex.app.entities.User;
import com.ex.app.persistence.Persistable;
import com.ex.app.persistence.UserPersistence;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Scope("prototype")
public class UserService implements Persistable, ApplicationContextAware,
        BeanNameAware, BeanFactoryAware, InitializingBean, BeanPostProcessor, DisposableBean {

    private UserPersistence persistence;

    @Autowired
    public void setPersistence(UserPersistence userPersistence) {
        this.persistence = userPersistence;
    }

    @Transactional
    public boolean authenticate(String username, String password) {
        return persistence.authenticate(username, password);
    }

    @Override
    @Transactional
    public List getAll() {
        return persistence.getAll();
    }

    @Override
    @Transactional
    public Object getById(Object id) {
        return persistence.getById(id);
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

    @Override
    @Transactional
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    @Transactional
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
