package ju.loginpage;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;

public class ChangeExistingAccount extends AppCompatActivity {
    Button cancelExistingAcoutn;
    Button changeAccount;

    EditText oldAccountName;
    EditText oldPassword;
    EditText newAccountName;
    EditText newPassword;
    EditText confirmNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_existing_account);
        cancelExistingAcoutn = (Button)findViewById(R.id.buttonCancelC);
        changeAccount = (Button)findViewById(R.id.buttonSaveC);

        oldAccountName = (EditText)findViewById(R.id.editTextAddOldAccountNameC);
        oldPassword = (EditText)findViewById(R.id.editTextAddOldPasswordC);
        newAccountName = (EditText)findViewById(R.id.editTextAddNewAccountName);
        newPassword = (EditText)findViewById(R.id.editTextAddNewPasswordC);
        confirmNewPassword = (EditText)findViewById(R.id.editTextConfirmNewPasswordC);

        changeAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinkedList<SharedPreferences> ahmadd = new LinkedList<SharedPreferences>();
                int counter = 0;
                boolean holder = true;
                while(holder){
                    ahmadd.add(getSharedPreferences("newAccountInfo" + counter, MODE_PRIVATE));
                    String accN = ahmadd.get(counter).getString("accountName", "");
                    String accP = ahmadd.get(counter).getString("newPass", "");
                    if(accN.equals("")){
                        holder = false;
                        myAlert2();
                    }else if(equalizerMethod(oldAccountName.getText().toString(),oldPassword.getText().toString(), accN, accP)){
                        if(newPassword.getText().toString().equals(confirmNewPassword.getText().toString())){
                            SharedPreferences.Editor editor = ahmadd.get(counter).edit();
                            editor.putString("accountName", newAccountName.getText().toString());
                            editor.putString("newPass", newPassword.getText().toString());
                            editor.apply();
                            Toast.makeText(ChangeExistingAccount.this, "the old account CHANGED!!", Toast.LENGTH_SHORT).show();
                            holder = false;
                        }else{
                            myAlert();
                            holder = false;
                        }
                    }else {
                        counter++;
                    }

                }
            }
        });
        cancelExistingAcoutn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private boolean equalizerMethod(String oldAccN, String oldAccPass, String DBAccN, String DBAccPass){
        if(oldAccN.equals(DBAccN) && oldAccPass.equals(DBAccPass)){
            return true;
        }
        return false;
    }

    private void myAlert(){
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(ChangeExistingAccount.this);
        myBuilder.setMessage("your entered new password does not match it's confirmation CHECK IT PLEASE.")
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

    private void myAlert2(){
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(ChangeExistingAccount.this);
        myBuilder.setMessage("the old user name and password you entered is not available in the System.")
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
