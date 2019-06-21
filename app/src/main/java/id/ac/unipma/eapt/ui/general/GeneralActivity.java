package id.ac.unipma.eapt.ui.general;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;
import id.ac.unipma.eapt.R;
import id.ac.unipma.eapt.ui.base.BaseActivity;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class GeneralActivity extends BaseActivity implements GeneralView, DatePickerDialog.OnDateSetListener {

    @Inject
    GeneralMvpPresenter<GeneralView> presenter;

    @BindView(R.id.no_reg)
    EditText txtNoReg;

    @BindView(R.id.nik)
    EditText txtNik;

    @BindView(R.id.fullname)
    EditText txtFullname;

    @BindView(R.id.dob)
    TextView txtDob;


    @BindView(R.id.phone)
    TextView txtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();

        txtDob.setOnClickListener(view -> {
            new SpinnerDatePickerDialogBuilder()
                    .context(this)
                    .callback(this)
                    .spinnerTheme(R.style.NumberPickerStyle)
                    .showTitle(true)
                    .showDaySpinner(true)
                    .defaultDate(2017, 0, 1)
                    .maxDate(2005, 0, 1)
                    .minDate(1950, 0, 1)
                    .build()
                    .show();
        });
    }

    @OnClick(R.id.btnPostData)
    void post() {
        presenter.sendData(
                txtNoReg.getText().toString(),
                txtNik.getText().toString(),
                txtFullname.getText().toString(),
                txtDob.getText().toString(),
                txtPhone.getText().toString()
        );
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void back() {
        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        txtDob.setText(simpleDateFormat.format(calendar.getTime()));
    }
}
