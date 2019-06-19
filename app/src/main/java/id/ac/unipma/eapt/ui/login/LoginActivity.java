package id.ac.unipma.eapt.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ac.unipma.eapt.R;
import id.ac.unipma.eapt.ui.base.BaseActivity;
import id.ac.unipma.eapt.ui.main.MainActivity;
import id.ac.unipma.eapt.ui.register.RegisterActivity;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements LoginView {

    @Inject
    LoginMvpPresenter<LoginView> presenter;

    @BindView(R.id.txt_email)
    EditText txtEmail;
    @BindView(R.id.txt_pass)
    EditText txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(id.ac.unipma.eapt.R.layout.activity_login);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();
    }

    @OnClick(R.id.btn_sign_in)
    void signIn() {
        //presenter.signIn(txtEmail.getText().toString(), txtPass.getText().toString());
        gotoMainActivity();
    }

    @OnClick(R.id.register)
    void regist() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void gotoMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
