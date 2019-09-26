package by.morozova.people;

import by.morozova.people.mvp.PeopleActivity;
import dagger.Component;

@Component(modules = {PersonAdapterModule.class})
public interface AppComponent {
    void injectsMainActivity(PeopleActivity peopleActivity);
}
