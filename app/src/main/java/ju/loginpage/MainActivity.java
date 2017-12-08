package ju.loginpage;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private Button Login;
    private Button Cancel;
    private int counter = 5;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = (EditText)findViewById(R.id.editTextName);
        Password = (EditText)findViewById(R.id.editTextPassword);
        Login = (Button)findViewById(R.id.buttonLogin);
        Cancel = (Button)findViewById(R.id.buttonCancel);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }
    private void validate(String userN, String userPass){
        LinkedList<SharedPreferences> ahmadd = new LinkedList<SharedPreferences>();
        boolean holder = true, noMatch = false;
        int countor = 0;
        while(holder){
            ahmadd.add(getSharedPreferences("newAccountInfo" + countor, MODE_PRIVATE));
            String accN = ahmadd.get(countor).getString("accountName", "");

            if(accN.equals("")){
                //Toast.makeText(this, accN + " is empty" + countor, Toast.LENGTH_SHORT).show();
                holder = false;
                noMatch = true;
            }else {
                String accName = ahmadd.get(countor).getString("accountName", "");
                String accPass = ahmadd.get(countor).getString("newPass", "");
                if(userN.equals(accName) && userPass.equals(accPass)){
                    Intent yourAction = new Intent(MainActivity.this, HabibMonetariExchnageSys.class);
                    startActivity(yourAction);
                    holder = false;
                    finish();
                }
                countor++;
            }
        }
        if(noMatch){
            counter--;
            alrt2();
            if(counter == 0){
                Login.setEnabled(false);
                alrt1();
            }
        }
    }

    private void alrt1(){
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
        myBuilder.setMessage("sorry you can not enter you are not the authorized user.")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog myAlert1 = myBuilder.create();
        myAlert1.setTitle("Alert !!!");
        myAlert1.show();
    }

    private void alrt2(){
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
        myBuilder.setMessage("your user name or password is wrong.")
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
