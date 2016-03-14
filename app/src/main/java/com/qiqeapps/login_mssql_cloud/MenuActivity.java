package com.qiqeapps.login_mssql_cloud;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void sentenciasBasicas(View v) {
        Intent i = new Intent(MenuActivity.this, AddProducts.class);
        startActivity(i);
    }

    public void loadListView(View v) {
        Intent i = new Intent(MenuActivity.this, countrylist.class);
        startActivity(i);
    }

    public void storedProcedures(View v) {
        Intent i = new Intent(MenuActivity.this, Countries2.class);
        startActivity(i);
    }
}
