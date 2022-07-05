package app.dao;

import app.models.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User ");
        return query.getResultList();
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void remove(User user) {
        sessionFactory.getCurrentSession().remove(user);
    }

    @Override
    public User getUserById(Long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }
}

