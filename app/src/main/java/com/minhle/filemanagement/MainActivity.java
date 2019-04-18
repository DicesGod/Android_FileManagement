package com.minhle.filemanagement;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.minhle.filemanagement.R;

import java.util.ArrayList;
import java.util.Collections;

import model.Country;
import model.FileCountryManagment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener, DialogInterface.OnClickListener {


    EditText countryName;
    EditText countryCapital;
    Button buttonAdd;
    Button buttonSort;
    ListView listViewCountry;
    ArrayList<Country> listOfCountries;
    ArrayAdapter<Country> myAdapter;
    AlertDialog.Builder alertDialog;
    int currentPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        countryName = findViewById(R.id.editTextName);
        countryCapital = findViewById(R.id.editTextCapital);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSort = findViewById(R.id.buttonSort);
        listViewCountry = findViewById(R.id.listViewCountry);
        buttonSort.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);

        //1 Create data source
//        listOfCountries = new ArrayList<>();
//        listOfCountries.add(new Country("France", "Paris"));
//        listOfCountries.add(new Country("Maroc", "Rabat"));
//        listOfCountries.add(new Country("Algeria", "Algiers"));
//        listViewCountry.setOnItemClickListener(this);
//        listViewCountry.setOnItemLongClickListener(this);

        listOfCountries = FileCountryManagment.readFile(this,"countries.txt");
        //2 Create and Initialize the adapter

//        myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfCountries);
        myAdapter = new ArrayAdapter<>(this,R.layout.one_item, listOfCountries);

        //3 set the adapter to the ListView
        listViewCountry.setAdapter(myAdapter);
        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Delete");
        alertDialog.setMessage("Do you want to delete (Y/N) ");
        alertDialog.setPositiveButton("Yes",this);
        alertDialog.setNegativeButton("No",this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonAdd:
                listOfCountries.add(new Country(countryName.getText().toString(),countryCapital.getText().toString()));
                myAdapter.notifyDataSetChanged();
                break;
            case R.id.buttonSort:
                Collections.sort(listOfCountries);
                myAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String cName = listOfCountries.get(position).getCountryName();
        String cCapital = listOfCountries.get(position).getCountryCapital();
        countryName.setText(cName);
        countryCapital.setText(cCapital);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

//        listOfCountries.remove(position);
//        myAdapter.notifyDataSetChanged();
        //true :  manage a long click + a click
        //false : manage a long click
        currentPosition = position;
        AlertDialog dialogBox = alertDialog.create();
        dialogBox.show();
        return false;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case Dialog.BUTTON_POSITIVE:
                listOfCountries.remove(currentPosition);
                myAdapter.notifyDataSetChanged();
                break;

            case Dialog.BUTTON_NEGATIVE:
                break;

        }

    }
}
