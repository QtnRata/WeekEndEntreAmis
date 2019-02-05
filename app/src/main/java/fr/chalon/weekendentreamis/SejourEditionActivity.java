package fr.chalon.weekendentreamis;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModel;
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
import fr.chalon.weekendentreamis.viewmodels.SejourListViewModel;

public class SejourEditionActivity extends AppCompatActivity {

    private FormHelper formHelper;
    private DatePickerDialog datePickerDialog;
    private ActivityAddSejourActivityBinding binding;

    EditText nom;
    EditText dateDebut;
    EditText dateFin;
    Button btnDateDebut;
    Button btnDateFin;
    Button btnAddSejour;
    int mYear, mMonth, mDay;


    SejourListViewModel vm;
    SejourRepository sejourRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sejour_activity);

        vm = new SejourListViewModel();
        sejourRepository =new SejourRepository(this.getApplication());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_sejour_activity);
        binding.setViewModel(vm);

        nom = findViewById(R.id.txtAddNomSejour);
        dateDebut = findViewById(R.id.dtpAddDateDebut);
        dateFin = findViewById(R.id.dtpAddDateFin);
        btnDateDebut= findViewById(R.id.btnAddDateDebut);
        btnDateFin = findViewById(R.id.btnAddDateFin);
        btnAddSejour = findViewById(R.id.btnAddFormSejour);

        //btnDateDebut.setOnClickListener(this);
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
        //btnDateFin.setOnClickListener(this);
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
        //btnAddSejour.setOnClickListener(this);
        btnAddSejour.setOnClickListener((v1 -> {
            if(checkForm()){
                Sejour sejour = new Sejour(vm.getNom(),vm.getDateDebut(),vm.getDateFin(), 1);
                sejourRepository.insert(sejour);
                Toast t = Toast.makeText(this, R.string.sejour_ajouter, Toast.LENGTH_SHORT);
                t.show();
            }
        }));


    }

    boolean checkForm(){
        if(formHelper.isEmpty(nom)){
            nom.setError(getResources().getString(R.string.error_missing_nom));
            nom.requestFocus();
            return false;
        }
        if(formHelper.isEmpty(dateDebut)){
            dateDebut.setError(getResources().getString(R.string.error_missing_date_debut));
            dateDebut.requestFocus();
            return false;
        }

        if(formHelper.isEmpty(dateFin)){
           dateFin.setError(getResources().getString(R.string.error_missing_date_fin));
           dateFin.requestFocus();
            return false;
        }
        return true;
    }



}
