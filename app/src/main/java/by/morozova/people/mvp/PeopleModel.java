package by.morozova.people.mvp;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import by.morozova.people.database.DatabasePerson;
import by.morozova.people.database.entity.Person;

public class PeopleModel {

	private final DatabasePerson databasePerson;

	public PeopleModel(DatabasePerson databasePerson) {
		this.databasePerson = databasePerson;
	}

	public void loadPeople(PeopleCallback callback) {
		LoadPeople loadUsersTask = new LoadPeople(callback);
		loadUsersTask.execute();
	}

	public void sortByName(PeopleCallback callback) {
		SortByName sortByName = new SortByName(callback);
		sortByName.execute();
	}

	public void searchByName(PeopleCallback callback, String text) {
		SearchByName searchByName = new SearchByName(callback, text);
		searchByName.execute();
//		callback.onLoad(databasePerson.getAppDatabase().personDao().searchByName(text));
	}

	interface PeopleCallback {
		void onLoad(List<Person> users);
	}

	class LoadPeople extends AsyncTask<Void, Void, List<Person>> {

		private final PeopleCallback callback;

		LoadPeople(PeopleCallback callback) {
			this.callback = callback;
		}

		@Override
		protected List<Person> doInBackground(Void... params) {
			List<Person> people = databasePerson.getAppDatabase().personDao().getAllPeople();
			if(people != null && people.size() < 1){
				databasePerson.getAppDatabase().personDao().insertAll(setData());
			}
			return people;
		}

		@Override
		protected void onPostExecute(List<Person> people) {
			if (callback != null) {
				callback.onLoad(people);
			}
		}
	}

	class SortByName extends AsyncTask<Void, Void, List<Person>> {

		private final PeopleCallback callback;

		SortByName(PeopleCallback callback) {
			this.callback = callback;
		}

		@Override
		protected List<Person> doInBackground(Void... params) {
			return databasePerson.getAppDatabase().personDao().sortByName();
		}

		@Override
		protected void onPostExecute(List<Person> people) {
			if (callback != null) {
				callback.onLoad(people);
			}
		}
	}

	class SearchByName extends AsyncTask<Void, Void, List<Person>> {

		private final PeopleCallback callback;
		private final String text;

		SearchByName(PeopleCallback callback, String text) {
			this.callback = callback;
			this.text = text;
		}

		@Override
		protected List<Person> doInBackground(Void... params) {
			return databasePerson.getAppDatabase().personDao().searchByName(text);
		}

		@Override
		protected void onPostExecute(List<Person> people) {
			if (callback != null) {
				callback.onLoad(people);
			}
		}
	}

	private List<Person> setData(){
		List<Person> people = new ArrayList<>();
		people.add(new Person(1, "Max", true, "+37520339021", "Minsk"));
		people.add(new Person(2, "Vlad", true, "+37557467471", "Mogilev"));
		people.add(new Person(3, "Vika", false, "+37545674747", "Minsk"));
		people.add(new Person(4, "Nastya", false, "+37525757567", "Brest"));
		people.add(new Person(5, "Nikita", true, "+37521323213", "Brest"));
		people.add(new Person(6, "Lola", false, "+37525452545", "Vitebsk"));
		people.add(new Person(7, "Olga", false, "+37545245241", "Minsk"));
		people.add(new Person(8, "Sasha", true, "+37527827878", "Minsk"));
		people.add(new Person(9, "Lesha",  false, "+37527878778", "Grodno"));
		people.add(new Person(10, "Andrey", true, "+37520878788", "Gomel"));
		return people;
	}
}
