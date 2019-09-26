package by.morozova.people.database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabasePerson {

	private static DatabasePerson mInstance;

	//our app database object
	private AppRoomDatabase appRoomDatabase;

	private DatabasePerson(Context mCtx) {
		appRoomDatabase = Room.databaseBuilder(mCtx, AppRoomDatabase.class, "People").build();
	}

	public static synchronized DatabasePerson getInstance(Context mCtx) {
		if (mInstance == null) {
			mInstance = new DatabasePerson(mCtx);
		}
		return mInstance;
	}

	public AppRoomDatabase getAppDatabase() {
		return appRoomDatabase;
	}
}
