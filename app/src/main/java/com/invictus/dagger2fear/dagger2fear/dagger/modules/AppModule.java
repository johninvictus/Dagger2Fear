package com.invictus.dagger2fear.dagger2fear.dagger.modules;

import com.invictus.dagger2fear.dagger2fear.repository.api.TmdbService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by invictus on 4/28/18.
 */
@Module(includes = {
        ViewModelModule.class,
        RoomModule.class,
        NetworkModule.class
})
public class AppModule {
    @Provides
    @Singleton
    TmdbService provideTmdbService(Retrofit retrofit) {
        return retrofit.create(TmdbService.class);
    }
}
