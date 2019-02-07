package fr.chalon.weekendentreamis;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import fr.chalon.weekendentreamis.database.entities.Sejour;
import fr.chalon.weekendentreamis.databinding.ActivityAddSejourActivityBinding;
import fr.chalon.weekendentreamis.helpers.FormHelper;
import fr.chalon.weekendentreamis.repository.SejourRepository;
import fr.chalon.weekendentreamis.viewmodels.SejourEditionViewModel;
import fr.chalon.weekendentreamis.viewmodels.SejourListViewModel;

public class SejourEditionActivity extends AppCompatActivity {

    private FormHelper formHelper;
    private DatePickerDialog datePickerDialog;
    private ActivityAddSejourActivityBinding binding;

    private boolean isUpdate = false;

    private SejourEditionViewModel sejourEditionViewModel;

    EditText nom;
    EditText dateDebut;
    EditText dateFin;
    Button btnDateDebut;
    Button btnDateFin;
    Button btnAddSejour;
    int mYear, mMonth, mDay;


    SejourRepository sejourRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sejour_activity);

        //vm = new SejourListViewModel();
        sejourRepository =new SejourRepository(this.getApplication());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_sejour_activity);


        sejourEditionViewModel = ViewModelProviders.of(this).get(SejourEditionViewModel.class);
        //sejourEditionViewModel = new SejourEditionViewModel();

        nom = findViewById(R.id.txtAddNomSejour);
        dateDebut = findViewById(R.id.dtpAddDateDebut);
        dateFin = findViewById(R.id.dtpAddDateFin);
        btnDateDebut= findViewById(R.id.btnAddDateDebut);
        btnDateFin = findViewById(R.id.btnAddDateFin);
        btnAddSejour = findViewById(R.id.btnAddFormSejour);

        long id = getIntent().getLongExtra("id", 0);



        btnDateDebut.setOnClickListener((v1 -> {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year, monthOfYear, dayOfMonth) ->
                            dateDebut.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year), mYear, mMonth, mDay);
            datePickerDialog.show();

        }));

        btnDateFin.setOnClickListener((v1 -> {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year, monthOfYear, dayOfMonth) ->
                            dateFin.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year), mYear, mMonth, mDay);
            datePickerDialog.show();

        }));

        this.sejourRepository.getSejourById(id).removeObservers(this);

        this.sejourRepository.getSejourById(id).observe(this, sejour -> {
            if(sejour != null){
                this.isUpdate =true;
                sejourEditionViewModel.setSejour(sejour);
            }else{
                sejourEditionViewModel.setSejour(new Sejour("","","",1));
            }
            binding.setViewModel(sejourEditionViewModel);
            binding.setEditClickListener(v -> onSubmit());
        });





    }

    boolean noCheckForm(){



        if(formHelper.isEmpty(this, nom, R.string.error_missing_nom)){
            nom.setText("");
            return true;
        }
        if(formHelper.isEmpty(this,dateDebut,R.string.error_missing_date_debut)){
            dateDebut.setText("");
            return true;
        }

        if(formHelper.isEmpty(this,dateFin,R.string.error_missing_date_fin)){
           dateFin.setText("");
            return true;
        }
        return false;
    }

    private void onSubmit(){
        if(noCheckForm()){
            Toast.makeText(this, R.string.edit_failed_error, Toast.LENGTH_SHORT).show();
            return;

        }

        if(this.isUpdate){
            this.sejourRepository.update(this.sejourEditionViewModel.getSejour());

        }else{
            this.sejourRepository.insert(this.sejourEditionViewModel.getSejour());

        }
        Toast t = Toast.makeText(this, R.string.sejour_ajouter, Toast.LENGTH_SHORT);
        t.show();
    }


}
