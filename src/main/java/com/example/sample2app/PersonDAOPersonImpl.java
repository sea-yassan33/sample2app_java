package com.example.sample2app;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class PersonDAOPersonImpl implements PersonDAO<Person> {
  private static final long serialVersionUID = 1L;

  @PersistenceContext
  private EntityManager entityManager;

  public PersonDAOPersonImpl(){
    super();
  }

  @Override
  public List<Person> getAll(){
    List<Person>list = null;
    CriteriaBuilder builder =entityManager.getCriteriaBuilder();
    CriteriaQuery<Person> query =builder.createQuery(Person.class);
    Root<Person> root = query.from(Person.class);
    query.select(root);
    list = (List<Person>)entityManager.createQuery(query).getResultList();
    return list;
  }

  @Override
  public Person findById(long id){
    return (Person)entityManager.createQuery("from Person where id = " + id ).getSingleResult();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Person> findByName(String name){
    return (List<Person>)entityManager.createQuery("from Person where name = '"+ name +"'").getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Person> find(String fstr){
    List<Person> list = null;
    Query query = entityManager
            .createNamedQuery("findWithName")
            .setParameter("fname", "%" + fstr + "%");
    list = query.getResultList();
    return list;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Person> findByAge(int min, int max){
    return (List<Person>)entityManager
            .createNamedQuery("findByAge")
            .setParameter("min", min)
            .setParameter("max", max)
            .getResultList();
  }
}
