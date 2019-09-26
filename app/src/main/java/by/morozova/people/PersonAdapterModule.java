package by.morozova.people;

import dagger.Module;
import dagger.Provides;

@Module
public class PersonAdapterModule {

    @Provides
    PersonAdapter providePersonAdapter(){
        return new PersonAdapter();
    }
}