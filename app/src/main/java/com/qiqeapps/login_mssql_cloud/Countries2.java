package com.qiqeapps.login_mssql_cloud;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.sql.Connection;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import android.widget.AdapterView.OnItemClickListener;

public class Countries2 extends AppCompatActivity {

    TextView lblheader;
    Typeface font;
    Button btnviewall, btnview;
    ListView lstcountry;
    EditText edtid;
    Connection connect;
    ResultSet rs;
    ConnectionClass connectionclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries2);

        try {
            connectionclass = new ConnectionClass();
            connect = connectionclass.CONN();
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }

        lblheader = (TextView) findViewById(R.id.lblheader);
        lstcountry = (ListView) findViewById(R.id.lstcountry);
        btnviewall = (Button) findViewById(R.id.btnviewall);
        btnview = (Button) findViewById(R.id.btnview);
        edtid = (EditText) findViewById(R.id.edtid);

        btnviewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PreparedStatement statement = connect.prepareStatement("EXEC viewAllCountries");
                    final ArrayList list = new ArrayList();
                    rs = statement.executeQuery();
                    while (rs.next()) {
                        list.add(rs.getString("CountryName"));
                    }
                    ArrayAdapter adapter = new ArrayAdapter(Countries2.this,
                            android.R.layout.simple_list_item_1, list);
                    lstcountry.setAdapter(adapter);
                } catch (SQLException e) {
                    Toast.makeText(Countries2.this, e.getMessage().toString(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PreparedStatement statement = connect.prepareStatement("EXEC viewCountry '"+edtid.getText().toString()+"'");
                    final ArrayList list = new ArrayList();
                    rs = statement.executeQuery();
                    while (rs.next()) {
                        list.add(rs.getString("CountryName"));
                    }
                    ArrayAdapter adapter = new ArrayAdapter(Countries2.this,
                            android.R.layout.simple_list_item_1, list);
                    lstcountry.setAdapter(adapter);
                } catch (SQLException e) {
                    Toast.makeText(Countries2.this, e.getMessage().toString(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        lstcountry.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String item = lstcountry.getItemAtPosition(position).toString();
                Toast.makeText(Countries2.this, item + " selected", Toast.LENGTH_LONG).show();
            }
        });
    }
}
