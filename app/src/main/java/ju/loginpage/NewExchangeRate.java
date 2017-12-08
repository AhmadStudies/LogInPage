package ju.loginpage;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class NewExchangeRate extends AppCompatActivity {
    Spinner sp1;
    Spinner sp2;
    Button buy;
    Button sell;
    Button close1;
    EditText textBoxRate;
    TextView timeShow;
    ArrayList<String> mUnitsArrayList = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exchange_rate);
        sp1 = (Spinner)findViewById(R.id.spinner1);
        sp2 = (Spinner)findViewById(R.id.spinner2);
        buy = (Button)findViewById(R.id.button1Save);
        sell = (Button)findViewById(R.id.button1Cancel);
        close1 = (Button)findViewById(R.id.button1Close);
        textBoxRate = (EditText)findViewById(R.id.editTextRate);
        timeShow = (TextView)findViewById(R.id.textViewTime);
        final BuyDatabase bdb = new BuyDatabase(this);
        final SellDatabase sdb = new SellDatabase(this);
        //----------------------------------
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mUnitsArrayList);
        final Database db = new Database(this);
        db.openDB();
        mUnitsArrayList.clear();
        Cursor c = db.getAllValues();
        while (c.moveToNext()){
            String unit = c.getString(1);
            mUnitsArrayList.add(unit);
        }
        db.close();
        sp1.setAdapter(adapter);
        sp2.setAdapter(adapter);

        close1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textBoxRate.getText().toString().equals("")){
                    Toast.makeText(NewExchangeRate.this, "insert the rate in to text box please.", Toast.LENGTH_SHORT).show();
                }else {
                    bdb.openDB2();
                    long addCondition = bdb.add2(sp1.getSelectedItem().toString(), sp2.getSelectedItem().toString(), textBoxRate.getText().toString());
                    if(addCondition != 0){
                        textBoxRate.setText("");
                        Toast.makeText(NewExchangeRate.this, "all records added.", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(NewExchangeRate.this, "Failed no record added.", Toast.LENGTH_SHORT).show();
                    }

                    bdb.close2();
                }

            }
        });

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textBoxRate.getText().toString().equals("")){
                    Toast.makeText(NewExchangeRate.this, "insert the rate in to text box please.", Toast.LENGTH_SHORT).show();
                }else {
                    sdb.sopenDB2();
                    long addCondition = sdb.sadd2(sp1.getSelectedItem().toString(), sp2.getSelectedItem().toString(), textBoxRate.getText().toString());
                    if(addCondition != 0){
                        textBoxRate.setText("");
                        Toast.makeText(NewExchangeRate.this, "all sell records added.", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(NewExchangeRate.this, "Failed no sell record added.", Toast.LENGTH_SHORT).show();
                    }

                    sdb.sclose2();
                }

            }
        });

    }



}
