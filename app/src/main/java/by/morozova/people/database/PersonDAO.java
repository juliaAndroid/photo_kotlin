package by.morozova.people.database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import by.morozova.people.database.entity.Person;

@Dao
public interface PersonDAO {

	@Insert
	void insertAll(List<Person> people);

	@Query("SELECT * FROM People")
	List<Person> getAllPeople();

	@Query("SELECT * FROM people ORDER BY name ASC")
	List<Person> sortByName();

	@Query("SELECT * FROM people WHERE name LIKE '%' || :text || '%'")
    List<Person> searchByName(String text);
}
