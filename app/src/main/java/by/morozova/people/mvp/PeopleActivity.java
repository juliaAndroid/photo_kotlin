package by.morozova.people.mvp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import by.morozova.people.App;
import by.morozova.people.RxSearchObservable;
import by.morozova.people.database.DatabasePerson;
import by.morozova.people.PersonAdapter;
import by.morozova.people.R;
import by.morozova.people.database.entity.Person;

public class PeopleActivity extends AppCompatActivity {


	@Inject
	public PersonAdapter personAdapter;
	private PeoplePresenter peoplePresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		App.getComponent().injectsMainActivity(this);

		init();

		PeopleModel peopleModel = new PeopleModel(DatabasePerson.getInstance(getApplicationContext()));
		peoplePresenter = new PeoplePresenter(peopleModel);
		peoplePresenter.attachView(this);
		peoplePresenter.viewIsReady();
	}

	@Override
	protected void onStart() {
		peoplePresenter.loadPeople();
		super.onStart();
	}

	private void init() {
		RadioButton rbById = findViewById(R.id.rb_by_id);
		RadioButton rbByName = findViewById(R.id.rb_by_name);
		Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
		rbById.setOnClickListener(v -> peoplePresenter.loadPeople());
		rbByName.setOnClickListener(v -> peoplePresenter.sortByName());

		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

		RecyclerView userList = findViewById(R.id.list_people);
		userList.setLayoutManager(layoutManager);
		userList.setAdapter(personAdapter);
	}

	public void showPeople(List<Person> people) {
		personAdapter.setData(people);
	}

    @SuppressLint("CheckResult")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.search_menu, menu);
		MenuItem mSearch = menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        RxSearchObservable.fromView(mSearchView)
                .map(text -> text.toLowerCase().trim())
                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribe(this::search);
		return true;
	}

	private void search(String s) {
		if(s.isEmpty()){
			peoplePresenter.loadPeople();
		} else {
			peoplePresenter.searchByName(s);
		}
	}

    @Override
    protected void onDestroy() {
	    peoplePresenter.detachView();
        super.onDestroy();
    }
}