package id.ac.unipma.eapt.ui.main;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import id.ac.unipma.eapt.R;
import id.ac.unipma.eapt.ui.account.AccountFragment;
import id.ac.unipma.eapt.ui.base.BaseActivity;
import id.ac.unipma.eapt.ui.helper.NonSwipeableViewPager;
import id.ac.unipma.eapt.ui.home.HomeFragment;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MainView, ViewPager.OnPageChangeListener, BubbleNavigationChangeListener {

    @Inject
    MainMvpPresenter<MainView> presenter;

    @BindView(R.id.navigation)
    BubbleNavigationLinearView mNavigation;

    @BindView(R.id.viewpager)
    NonSwipeableViewPager mViewpager;

    private MainPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(id.ac.unipma.eapt.R.layout.activity_main);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();
    }


    @Override
    protected void setUp() {
        setupNavigationView();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mNavigation.setCurrentActiveItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onNavigationChanged(View view, int position) {
        mViewpager.setCurrentItem(position);
    }

    private void setupNavigationView() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance());
        fragments.add(AccountFragment.newInstance());
        adapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        mViewpager.setAdapter(adapter);
        mViewpager.setOnPageChangeListener(this);

        mNavigation.setNavigationChangeListener(this);
    }
}
