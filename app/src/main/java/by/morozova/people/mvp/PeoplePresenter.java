package by.morozova.people.mvp;


class PeoplePresenter {

	private PeopleActivity view;
	private final PeopleModel model;

	public PeoplePresenter(PeopleModel model) {
		this.model = model;
	}

	public void attachView(PeopleActivity peopleActivity) {
		view = peopleActivity;
	}

	public void viewIsReady() {
		loadPeople();
	}

	public void detachView() {
		view = null;
	}

	public void loadPeople() {
		model.loadPeople(people -> view.showPeople(people));
	}

	public void sortByName() {
		model.sortByName(people -> view.showPeople(people));
	}

    public void searchByName(String s) {
		model.searchByName(people -> view.showPeople(people), s);
    }
}
