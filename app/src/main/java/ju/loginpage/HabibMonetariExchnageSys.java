package ju.loginpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HabibMonetariExchnageSys extends AppCompatActivity {
    private Button settingButton;
    private Button logout;
    private Button save;
    private Button close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habib_monetari_exchnage_sys);
        settingButton = (Button)findViewById(R.id.buttonSettings);
        logout = (Button)findViewById(R.id.buttonLogout);
        save = (Button)findViewById(R.id.buttonSave);
        close = (Button)findViewById(R.id.buttonCloseExchange);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent yourIntent3 = new Intent(HabibMonetariExchnageSys.this, MainActivity.class);
                startActivity(yourIntent3);
            }
        });
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent yourIntent2 = new Intent(HabibMonetariExchnageSys.this, Settings.class);
                startActivity(yourIntent2);
            }
        });
    }
}
