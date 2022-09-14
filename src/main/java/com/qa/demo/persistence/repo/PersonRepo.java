package com.qa.demo.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.demo.persistence.domain.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {

	@Query("SELECT p from Person p")
	List<Person> findAllJPQL();

//	List<Person> findPersonByNameAndAge(String name, int age);

	@Query(value = "SELECT * from Person", nativeQuery = true)
	List<Person> findAllSQL();

	List<Person> findPersonByAgeGreaterThan(int age);

	@Query("SELECT p from Person p WHERE p.age > ?1")
	List<Person> findPersonByAgeGreaterThanJPQL(int age);

	@Query(value = "SELECT * from Person WHERE age > ?1", nativeQuery = true)
	List<Person> findPersonByAgeGreaterThanSQL(int age);

	List<Person> findPersonByAgeLessThan(int age);

	@Query("SELECT p from Person p WHERE p.age < ?1")
	List<Person> findPersonByAgeLessThanJPQL(int age);

	@Query(value = "SELECT * from Person WHERE age < ?1", nativeQuery = true)
	List<Person> findPersonByAgeLessThanSQL(int age);

	List<Person> findPersonByName(String name);

	@Query("SELECT p from Person p WHERE p.name = ?1")
	List<Person> findPersonByNameJPQL(String name);

	@Query(value = "SELECT * from Person WHERE name = ?1", nativeQuery = true)
	List<Person> findPersonByNameSQL(String name);

	Person findPersonByNameAndAge(String name, int age);

	@Query("SELECT p from Person p WHERE p.name = ?1 and p.age = ?2")
	Person findPersonByNameAndAgeJPQL(String name, int age);

	@Query(value = "SELECT * from Person WHERE name = ?1 and age = ?2", nativeQuery = true)
	Person findPersonByNameAndAgeSQL(String name, int age);

	List<Person> findPersonByNameIsNotNull();

	@Query("SELECT p from Person p WHERE p.name IS NOT NULL")
	List<Person> findPersonByNameIsNotNullJPQL();

	@Query(value = "SELECT * from Person p WHERE name IS NOT NULL", nativeQuery = true)
	List<Person> findPersonByNameIsNotNullSQL();

	List<Person> findPersonByNameIsNull();

	@Query("SELECT p from Person p WHERE p.name IS NULL")
	List<Person> findPersonByNameIsNullJPQL();

	@Query(value = "SELECT * from Person p WHERE name IS NULL", nativeQuery = true)
	List<Person> findPersonByNameIsNullSQL();

}
