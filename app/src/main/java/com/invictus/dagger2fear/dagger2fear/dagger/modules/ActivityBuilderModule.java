package com.invictus.dagger2fear.dagger2fear.dagger.modules;

import com.invictus.dagger2fear.dagger2fear.dagger.modules.activities_module.MainActivityModule;

import dagger.Module;

/**
 * Created by invictus on 4/28/18.
 */
@Module(includes = {
        MainActivityModule.class
})
public class ActivityBuilderModule {
}
