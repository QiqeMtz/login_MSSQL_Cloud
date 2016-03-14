package com.qiqeapps.login_mssql_cloud;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class countrylist extends AppCompatActivity {

    TextView lblheader;
    Typeface font;
    Button btn;
    ListView lstcountry;
    SimpleAdapter ADAhere;
    /*********** CONNECTION DATABASE VARIABLES **************/
    ConnectionClass connectionclass;
    String usernameS;
    String datets;
    String call, db, un, passwords;
    Connection connect;
    ResultSet rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countrylist);

        lblheader = (TextView) findViewById(R.id.lblheader);
        lstcountry = (ListView) findViewById(R.id.lstcountry);
        btn = (Button) findViewById(R.id.btnview);
        /************* CONNECTION DATABASE VARIABLES ***************/

        try {
            connectionclass = new ConnectionClass();
            connect = connectionclass.CONN();
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String querycmd = "select * from Countries";
                try {
                    Statement statement = connect.createStatement();
                    rs = statement.executeQuery(querycmd);
                    List<Map<String, String>> data = null;
                    data = new ArrayList<Map<String, String>>();
                    while (rs.next()) {
                        Map<String, String> datanum = new HashMap<String, String>();
                        datanum.put("A", rs.getString("CountryName"));
                        data.add(datanum);
                    }
                    String[] fromwhere = { "A" };
                    int[] viewswhere = { R.id.lblcountryname };
                    ADAhere = new SimpleAdapter(countrylist.this, data,
                            R.layout.lsttemplate2, fromwhere, viewswhere);
                    lstcountry.setAdapter(ADAhere);
                } catch (SQLException e) {
                    Toast.makeText(countrylist.this, e.getMessage().toString(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        lstcountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                HashMap<String, Object> obj = (HashMap<String, Object>) ADAhere
                        .getItem(position);
                String VehicleId = (String) obj.get("A");
                Toast.makeText(countrylist.this, VehicleId, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

}
