package id.ac.unipma.eapt.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import id.ac.unipma.eapt.R;
import id.ac.unipma.eapt.di.component.ActivityComponent;
import id.ac.unipma.eapt.ui.base.BaseFragment;
import id.ac.unipma.eapt.utils.AppLogger;

import javax.inject.Inject;

public class HomeFragment extends BaseFragment implements HomeView {

    @Inject
    HomeMvpPresenter<HomeView> presenter;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
        }

        return view;
    }

    @Override
    protected void setUp(View view) {

    }
}
