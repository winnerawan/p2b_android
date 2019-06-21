package id.ac.unipma.eapt.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ac.unipma.eapt.R;
import id.ac.unipma.eapt.di.component.ActivityComponent;
import id.ac.unipma.eapt.ui.base.BaseFragment;
import id.ac.unipma.eapt.ui.general.GeneralActivity;
import id.ac.unipma.eapt.ui.login.LoginActivity;
import id.ac.unipma.eapt.ui.student.StudentActivity;
import id.ac.unipma.eapt.ui.upload.UploadActivity;
import id.ac.unipma.eapt.utils.AppLogger;

import javax.inject.Inject;

public class AccountFragment extends BaseFragment implements AccountView {

    @Inject
    AccountMvpPresenter<AccountView> presenter;

    @BindView(R.id.userEmail)
    TextView txtEmail;

    @BindView(R.id.userName)
    TextView txtName;
    @BindView(R.id.mobileNumber)
    TextView txtNimNik;

    public static AccountFragment newInstance() {

        Bundle args = new Bundle();

        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
        }

        return view;
    }

    @OnClick(R.id.logOut)
    void logout() {
        presenter.logout();
    }

    @OnClick(R.id.container_step1)
    void step1() {
        presenter.decideActivty();
    }

    @OnClick(R.id.container_step2)
    void step2() {
        presenter.decideActivty2();
    }

    @Override
    protected void setUp(View view) {
        presenter.getInfo();
        presenter.checkPayment();
        presenter.showEmail();
    }

    @Override
    public void gotoStudent() {
        startActivity(new Intent(getBaseActivity(), StudentActivity.class));
    }

    @Override
    public void gotoGeneral() {
        startActivity(new Intent(getBaseActivity(), GeneralActivity.class));
    }

    @Override
    public void gotoLogin() {
        startActivity(new Intent(getBaseActivity(), LoginActivity.class));
        if (getBaseActivity()!=null) {
            getBaseActivity().finish();
        }
    }

    @Override
    public void gotoPayment() {
        startActivity(new Intent(getBaseActivity(), UploadActivity.class));
    }

    @Override
    public void showEmail(String email) {
        txtEmail.setText(email);
    }

    @Override
    public void showInfo(String fullname, String nim) {
        txtName.setText(fullname);
        txtNimNik.setText(nim);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.checkPayment();
        presenter.getInfo();
    }
}
