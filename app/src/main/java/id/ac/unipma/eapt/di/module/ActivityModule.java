package id.ac.unipma.eapt.di.module;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import id.ac.unipma.eapt.di.PerActivity;
import id.ac.unipma.eapt.ui.account.AccountMvpPresenter;
import id.ac.unipma.eapt.ui.account.AccountPresenter;
import id.ac.unipma.eapt.ui.account.AccountView;
import id.ac.unipma.eapt.ui.detail.DetailMvpPresenter;
import id.ac.unipma.eapt.ui.home.HomeMvpPresenter;
import id.ac.unipma.eapt.ui.home.HomePresenter;
import id.ac.unipma.eapt.ui.home.HomeView;
import id.ac.unipma.eapt.ui.login.LoginMvpPresenter;
import id.ac.unipma.eapt.ui.login.LoginPresenter;
import id.ac.unipma.eapt.ui.login.LoginView;
import id.ac.unipma.eapt.ui.main.MainPagerAdapter;
import id.ac.unipma.eapt.ui.main.MainPresenter;
import id.ac.unipma.eapt.ui.detail.DetailView;
import id.ac.unipma.eapt.ui.main.MainView;
import dagger.Module;
import dagger.Provides;

import id.ac.unipma.eapt.di.ActivityContext;
import id.ac.unipma.eapt.ui.register.RegisterMvpPresenter;
import id.ac.unipma.eapt.ui.register.RegisterPresenter;
import id.ac.unipma.eapt.ui.register.RegisterView;
import id.ac.unipma.eapt.ui.splash.SplashMvpPresenter;
import id.ac.unipma.eapt.ui.splash.SplashView;
import id.ac.unipma.eapt.utils.rx.AppSchedulerProvider;
import id.ac.unipma.eapt.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import id.ac.unipma.eapt.ui.detail.DetailPresenter;
import id.ac.unipma.eapt.ui.main.MainMvpPresenter;
import id.ac.unipma.eapt.ui.splash.SplashPresenter;

import java.util.ArrayList;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }


    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashView> provideSplashPresenter(
            SplashPresenter<SplashView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainView> provideMainPresenter(
            MainPresenter<MainView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    DetailMvpPresenter<DetailView> provideDetailPresenter(
            DetailPresenter<DetailView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginView> provideLoginPresenter(
            LoginPresenter<LoginView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    RegisterMvpPresenter<RegisterView> provideRegisterPresenter(
            RegisterPresenter<RegisterView> presenter) {
        return presenter;
    }

    @Provides
    HomeMvpPresenter<HomeView> provideHomePresenter(
        HomePresenter<HomeView> presenter) {
        return presenter;
    }

    @Provides
    AccountMvpPresenter<AccountView> provideAccountPresenter(
            AccountPresenter<AccountView> presenter) {
        return presenter;
    }

//    @Provides
//    MainPagerAdapter provideMainPagerAdapter() {
//        return new MainPagerAdapter(mActivity.getSupportFragmentManager(), new ArrayList<>());
//    }
}
