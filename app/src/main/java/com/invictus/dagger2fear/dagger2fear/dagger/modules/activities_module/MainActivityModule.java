package com.invictus.dagger2fear.dagger2fear.dagger.modules.activities_module;

import com.invictus.dagger2fear.dagger2fear.dagger.modules.fragments_module.MainFragmentModule;
import com.invictus.dagger2fear.dagger2fear.view.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by invictus on 4/28/18.
 */

@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = MainFragmentModule.class)
    abstract MainActivity contributeMainActivity();
}
