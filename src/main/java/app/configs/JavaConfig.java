package app.configs;

import app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

// 4. Конфигурация Spring через JavaConfig и аннотации, по аналогии с предыдущими проектами.
// Без использования xml. Без Spring Boot.

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement // если все пошло не по плану делает ролбэк
@ComponentScan(value = "app")
public class JavaConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(environment.getProperty("db.url"));
        driverManagerDataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
        driverManagerDataSource.setUsername(environment.getProperty("db.username"));
        driverManagerDataSource.setPassword(environment.getProperty("db.password"));
        return driverManagerDataSource;
    }

    // 5. Внесите изменения в конфигурацию для работы с базой данных.
    // Вместо SessionFactory должен использоваться EntityManager.
//    @Bean // BEFORE
//    public LocalSessionFactoryBean getSessionFactory() {
//        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
//        localSessionFactoryBean.setDataSource(getDataSource());
//        localSessionFactoryBean.setPackagesToScan("app.models");
//
//        localSessionFactoryBean.setHibernateProperties(getHibernateProperties());
//        localSessionFactoryBean.setAnnotatedClasses(User.class);
//        return localSessionFactoryBean;
//    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManageFB = new LocalContainerEntityManagerFactoryBean();
        entityManageFB.setJpaVendorAdapter(getJpaVendorAdapter());
        entityManageFB.setDataSource(getDataSource());
        entityManageFB.setPackagesToScan("app.models");
        entityManageFB.setJpaProperties(getHibernateProperties());
        return entityManageFB;

    }

    @Bean
    public JpaVendorAdapter getJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public JpaTransactionManager getTransactionManager() {
        return new JpaTransactionManager(getEntityManagerFactoryBean().getObject());
    }

//    @Bean // BEFORE
//    public HibernateTransactionManager getTransactionManager() {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(getSessionFactory().getObject());
//        return transactionManager;
//    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }


}
