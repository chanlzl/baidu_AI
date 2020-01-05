package com.baidu.ai.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

    public static Session getSession(){
        Configuration configuration = new Configuration().configure();//创建配置对象
        SessionFactory sessionFactory = configuration.buildSessionFactory();//创建会话工厂
        Session session = sessionFactory.openSession();
        return session;
    }
}
