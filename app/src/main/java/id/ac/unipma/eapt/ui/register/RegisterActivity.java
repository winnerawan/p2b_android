package id.ac.unipma.eapt.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ac.unipma.eapt.R;
import id.ac.unipma.eapt.ui.base.BaseActivity;
import id.ac.unipma.eapt.ui.login.LoginActivity;

import javax.inject.Inject;
import java.util.List;

public class RegisterActivity extends BaseActivity implements RegisterView {

    @Inject
    RegisterMvpPresenter<RegisterView> presenter;

    @BindView(R.id.spinner_user_type)
    Spinner spAccountType;
    private AccountTypeAdapter accountTypeAdapter;
    private int account_type_id;

    @BindView(R.id.txt_email)
    EditText txtEmail;
    @BindView(R.id.txt_pass)
    EditText txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(id.ac.unipma.eapt.R.layout.activity_register);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();

        spAccountType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                account_type_id = accountTypeAdapter.userTypes.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                account_type_id = 1;
            }
        });

    }

    @OnClick(R.id.btn_sign_up)
    void register() {
        presenter.register(txtEmail.getText().toString(), txtPass.getText().toString(), account_type_id);
    }

    @OnClick(R.id.login)
    void login() {
        gotoLogin();
    }

    @Override
    protected void setUp() {
        presenter.showAccountType();
    }

    @Override
    public void showType(List<AccountType> accountTypes) {
        accountTypeAdapter = new AccountTypeAdapter(this, R.layout.item_spinner, R.id.txt_account_type, accountTypes);
        spAccountType.setAdapter(accountTypeAdapter);

    }

    @Override
    public void gotoLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
