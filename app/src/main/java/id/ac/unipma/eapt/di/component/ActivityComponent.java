package id.ac.unipma.eapt.di.component;

import id.ac.unipma.eapt.ui.account.AccountFragment;
import id.ac.unipma.eapt.ui.detail.DetailActivity;
import id.ac.unipma.eapt.ui.home.HomeFragment;
import id.ac.unipma.eapt.ui.login.LoginActivity;
import id.ac.unipma.eapt.ui.main.MainActivity;
import id.ac.unipma.eapt.ui.register.RegisterActivity;
import id.ac.unipma.eapt.ui.splash.SplashActivity;
import dagger.Component;
import id.ac.unipma.eapt.di.PerActivity;
import id.ac.unipma.eapt.di.module.ActivityModule;
import id.ac.unipma.eapt.ui.student.StudentActivity;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);

    void inject(MainActivity mainActivity);

    void inject(DetailActivity detailActivity);

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity registerActivity);

    void inject(HomeFragment homeFragment);

    void inject(AccountFragment accountFragment);

    void inject(StudentActivity studentActivity);
}
