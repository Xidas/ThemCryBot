package com.melorean.springConfig;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableScheduling
@ComponentScan(basePackages = "com.melorean")
public class AppConfigDao {


    @Bean(name = "dataSource", destroyMethod = "")
    public DataSource dataSource(){
        final BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:5432/xdsDB");
        ds.setUsername("postgres");
        ds.setPassword("1121");
        ds.setDefaultAutoCommit(false);
        ds.setDefaultCatalog("public");
        ds.setValidationQuery("SELECT 1");
        return ds;
    }

//    @Bean(name = "entityManager")
//    public  EntityManager entityManager() throws NamingException {
//        EntityManager em = entityManagerFactory().createEntityManager();
//        em.clear();
//        return em;
//    }

        @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory() throws NamingException {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("com.melorean.entity");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties());
        factoryBean.setPersistenceUnitName("entityManagerFactory");
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }
    private Properties jpaProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
//         properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.default_schema", "tcb");
        properties.put("hibernate.jdbc.batch_size", 1000);
//        properties.put("hibernate.session_factory_name", "sessionFactory");
//        properties.put("hibernate.current_session_context_class", "thread");
        properties.put("hibernate.order_inserts", true);
        properties.put("hibernate.order_updates", true);
        return properties;
    }

//    @Bean(name = "sessionFactory")
//    public LocalSessionFactoryBean sessionFactory(){
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
////        sessionFactory.setAnnotatedClasses(UserEntity.class);
//        sessionFactory.setAnnotatedPackages("com.melorean.entity");
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan("com.melorean.entity");
//        sessionFactory.setHibernateProperties(jpaProperties());
//        return sessionFactory;
//    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager transactionManager() throws NamingException {
//        return new JpaTransactionManager(entityManagerFactory());
//    }
//
//    @Bean(name = "txManagerHib")
//    @Autowired
//    public HibernateTransactionManager transactionManagerHib(SessionFactory s) {
//        HibernateTransactionManager txManager = new HibernateTransactionManager();
//        txManager.setSessionFactory(s);
//        return txManager;
//    }
    @Bean(name = "txManagerJDBC")
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }

    @Bean(name = "txManagerJpa")
    public JpaTransactionManager jpaTransactionManager() throws NamingException {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
        jpaTransactionManager.setJpaDialect(new HibernateJpaDialect());
        return jpaTransactionManager;
    }

}


