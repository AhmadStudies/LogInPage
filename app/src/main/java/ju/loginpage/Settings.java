package ju.loginpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {
    private Button returnButton;
    private Button closeButton;
    private Button accountBuilder;
    private Button changeExistingAcount;
    private Button addMoneyU;
    private Button newRate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        returnButton = (Button)findViewById(R.id.buttonReturnSettings);
        closeButton = (Button)findViewById(R.id.buttonCloseSettings);
        accountBuilder = (Button)findViewById(R.id.buttonCreateNewAc2);
        changeExistingAcount = (Button)findViewById(R.id.buttonChangeExistingAccount);
        addMoneyU = (Button)findViewById(R.id.buttonAddNewMoneyUnit);
        newRate = (Button)findViewById(R.id.buttonNewExchangeRate);

        newRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nrIntent = new Intent(Settings.this, NewExchangeRate.class);
                startActivity(nrIntent);
            }
        });
        addMoneyU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aIntent = new Intent(Settings.this, AddNewMoneyUnit.class);
                startActivity(aIntent);
            }
        });
        changeExistingAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent yourInten52 = new Intent(Settings.this, ChangeExistingAccount.class);
                startActivity(yourInten52);
            }
        });
        accountBuilder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(Settings.this, AccountBuilderForm.class);
                startActivity(newIntent);

            }
        });
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent yourIntent4 = new Intent(Settings.this, HabibMonetariExchnageSys.class);
                startActivity(yourIntent4);
            }
        });
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
