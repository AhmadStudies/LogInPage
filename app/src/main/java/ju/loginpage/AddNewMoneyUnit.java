package ju.loginpage;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddNewMoneyUnit extends AppCompatActivity {
    Spinner sp;
    EditText moneyUnTextBox;
    Button btAdd, btDelete, bTNMUclose;
    ArrayList<String> mUnitsArrayList = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_money_unit);

        sp = (Spinner)findViewById(R.id.spinnerAddUnit);
        moneyUnTextBox = (EditText)findViewById(R.id.editTextAddUnit);
        btAdd = (Button)findViewById(R.id.buttonAddUnit);
        btDelete = (Button)findViewById(R.id.buttonDeleteUnit);
        bTNMUclose = (Button)findViewById(R.id.buttonAddNMUclose);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mUnitsArrayList);
        final Database db = new Database(this);
        //------------------------------------------------------------------------------------------
        db.openDB();
        mUnitsArrayList.clear();
        Cursor c = db.getAllValues();
        while (c.moveToNext()){
            String unit = c.getString(1);
            mUnitsArrayList.add(unit);
        }
        db.close();
        sp.setAdapter(adapter);
        //------------------------------------------------------------------------------------------
        bTNMUclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(moneyUnTextBox.getText().toString().equals("")){
                    Toast.makeText(AddNewMoneyUnit.this, "insert a UNIT in to text box please.", Toast.LENGTH_SHORT).show();
                }else {
                    db.openDB();
                    long result = db.add(moneyUnTextBox.getText().toString());
                    if(result != 0){
                        moneyUnTextBox.setText("");
                        mUnitsArrayList.clear();
                        Cursor c = db.getAllValues();
                        while (c.moveToNext()){
                            String unit = c.getString(1);
                            mUnitsArrayList.add(unit);
                        }
                        sp.setAdapter(adapter);
                    }else {
                        Toast.makeText(AddNewMoneyUnit.this, "Failure", Toast.LENGTH_SHORT).show();
                    }
                    db.close();

                }
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.openDB();

                String getTex = moneyUnTextBox.getText().toString();
                long hold = db.deleteUnit(getTex);
                if(hold == 0){
                    Toast.makeText(AddNewMoneyUnit.this, "I Failed", Toast.LENGTH_SHORT).show();
                }
                else {
                    moneyUnTextBox.setText("");
                    mUnitsArrayList.clear();
                    Cursor c = db.getAllValues();
                    while (c.moveToNext()){
                        String unit = c.getString(1);
                        mUnitsArrayList.add(unit);
                    }
                    sp.setAdapter(adapter);
                    Toast.makeText(AddNewMoneyUnit.this, "the element deleted", Toast.LENGTH_SHORT).show();
                }

                db.close();
            }
        });
    }
}
