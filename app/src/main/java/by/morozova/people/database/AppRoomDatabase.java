package by.morozova.people.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import by.morozova.people.database.entity.Person;

@Database(entities = {Person.class}, version = 1)
public abstract class AppRoomDatabase extends RoomDatabase {

	public abstract PersonDAO personDao();
}
