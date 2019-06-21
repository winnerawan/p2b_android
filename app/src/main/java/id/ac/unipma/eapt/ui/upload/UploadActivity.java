package id.ac.unipma.eapt.ui.upload;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import id.ac.unipma.eapt.R;
import id.ac.unipma.eapt.data.network.model.Bank;
import id.ac.unipma.eapt.ui.base.BaseActivity;
import id.ac.unipma.eapt.utils.AppLogger;

import javax.inject.Inject;
import java.io.File;
import java.util.List;

public class UploadActivity extends BaseActivity implements UploadView {

    @Inject
    UploadMvpPresenter<UploadView> presenter;

    @BindView(R.id.ic_camera)
    ImageView icCamera;
    @BindView(R.id.croppedImageView)
    ImageView croppedImageView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.txtToolbar)
    TextView mTxtToolbar;

    @BindView(R.id.no_ref)
    EditText txtNoRef;

    @BindView(R.id.spinner_banks)
    Spinner spBanks;

    private File fileImage;

    private int bank_id;

    private BankAdapter bankAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();

        spBanks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bank_id = bankAdapter.userTypes.get(position).getId();
                AppLogger.e("bank = "+bank_id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                bank_id = 1;
            }
        });
    }

    @Override
    protected void setUp() {
        presenter.loadBanks();
        setSupportActionBar(mToolbar);
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

    }

    @OnClick(R.id.btnImage)
    void loadImage() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);

    }

    @OnClick(R.id.btnPostData)
    void postImage() {
        presenter.sendProofImage(bank_id, txtNoRef.getText().toString(), fileImage, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                icCamera.setVisibility(View.GONE);
                croppedImageView.setVisibility(View.VISIBLE);
                croppedImageView.setImageURI(resultUri);

                fileImage = new File(resultUri.getPath());

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }

    @Override
    public void showBanks(List<Bank> banks) {
        bankAdapter = new BankAdapter(this, R.layout.item_spinner_bank, R.id.txt_account_type, banks);
        spBanks.setAdapter(bankAdapter);
    }

    @Override
    public void back() {
        finish();
    }
}
