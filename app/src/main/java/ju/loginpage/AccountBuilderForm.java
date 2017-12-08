package ju.loginpage;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AccountBuilderForm extends AppCompatActivity {
    private Button bCancel;
    private EditText addAccountN;
    private EditText addNPass;
    private EditText confirmPass;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_builder_form);
        bCancel = (Button)findViewById(R.id.buttonCancel2);

        addAccountN = (EditText)findViewById(R.id.editTextAddAccount);
        addNPass = (EditText)findViewById(R.id.editTextAddNewPass);
        confirmPass = (EditText)findViewById(R.id.editTextConfirmPass);

        //-----------------------------------------------------------------------------
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void saveData(View view){
        LinkedList<SharedPreferences> ahmadd = new LinkedList<SharedPreferences>();
        int counter = 0;
        boolean holder = true, noMatchCondition = true;
        while(holder){
            ahmadd.add(getSharedPreferences("newAccountInfo" + counter, MODE_PRIVATE));
            String accN = ahmadd.get(counter).getString("accountName", "");
            if(addAccountN.getText().toString().equals(accN)){
                noMatchCondition = false;
                holder = false;
                myAlert3();
            }
            if(accN.equals("")){

                holder = false;
            }else {
                counter++;
            }

        }

        if(noMatchCondition){
            if(addNPass.getText().toString().equals(confirmPass.getText().toString())){
                SharedPreferences.Editor editor = ahmadd.get(counter).edit();
                editor.putString("accountName", addAccountN.getText().toString());
                editor.putString("newPass", addNPass.getText().toString());
                editor.apply();

                Toast.makeText(this, "all account entered data saved!", Toast.LENGTH_SHORT).show();
            }else{
                myAlert();
            }
        }


    }

    private void myAlert(){
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(AccountBuilderForm.this);
        myBuilder.setMessage("your entered password dose not match it's confirmation CHECK IT PLEASE.")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog myAlert2 = myBuilder.create();
        myAlert2.setTitle("Alert !!!");
        myAlert2.show();
    }

    private void myAlert3(){
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(AccountBuilderForm.this);
        myBuilder.setMessage("the account name your entered is available Change it please!!.")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog myAlert2 = myBuilder.create();
        myAlert2.setTitle("Alert !!!");
        myAlert2.show();
    }
}
