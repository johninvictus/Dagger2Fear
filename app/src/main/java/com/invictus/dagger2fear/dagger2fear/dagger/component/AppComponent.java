package com.invictus.dagger2fear.dagger2fear.dagger.component;

import android.app.Application;

import com.invictus.dagger2fear.dagger2fear.Dagger2Fear;
import com.invictus.dagger2fear.dagger2fear.dagger.modules.ActivityBuilderModule;
import com.invictus.dagger2fear.dagger2fear.dagger.modules.AppModule;
import com.invictus.dagger2fear.dagger2fear.dagger.modules.activities_module.MainActivityModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by invictus on 4/28/18.
 */

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilderModule.class

})
public interface AppComponent extends AndroidInjector<Dagger2Fear> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(Dagger2Fear app);
}
