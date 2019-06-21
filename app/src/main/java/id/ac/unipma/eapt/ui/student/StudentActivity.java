package id.ac.unipma.eapt.ui.student;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;
import id.ac.unipma.eapt.R;
import id.ac.unipma.eapt.data.network.model.Program;
import id.ac.unipma.eapt.ui.base.BaseActivity;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class StudentActivity extends BaseActivity implements StudentView, DatePickerDialog.OnDateSetListener {

    @Inject
    StudentMvpPresenter<StudentView> presenter;

    @BindView(R.id.spinner_programs)
    Spinner spPrograms;

    @BindView(R.id.no_reg)
    EditText txtNoReg;

    @BindView(R.id.nim)
    EditText txtNim;

    @BindView(R.id.fullname)
    EditText txtFullname;


    @BindView(R.id.dob)
    TextView txtDob;

    private ProgramStudyAdapter programStudyAdapter;

    private int program_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
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

        spPrograms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                program_id = programStudyAdapter.userTypes.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                program_id = 1;
            }
        });
    }

    @Override
    protected void setUp() {
        presenter.getPrograms();
    }

    @OnClick(R.id.btnPostData)
    void post() {
        presenter.sendData(
                program_id,
                txtNoReg.getText().toString(),
                txtNim.getText().toString(),
                txtFullname.getText().toString(),
                txtDob.getText().toString()
        );
    }

    @Override
    public void showPrograms(List<Program> programs) {
        programStudyAdapter = new ProgramStudyAdapter(this, R.layout.item_spinner, R.id.txt_account_type, programs);
        spPrograms.setAdapter(programStudyAdapter);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        txtDob.setText(simpleDateFormat.format(calendar.getTime()));
    }

    @Override
    public void back() {
        finish();
    }
}
