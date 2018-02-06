package es.miguelms.calculaletradni;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.format;
import static java.lang.String.valueOf;

/**
 * Miguel Martínez Serrano - miguelms.es
 * */
public class MainActivity extends AppCompatActivity {

    private TextView txLetraDni;
    private EditText txNumerosDni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btCalcular = findViewById(R.id.btCalcular);
        txLetraDni = findViewById(R.id.txLetraDni);
        txNumerosDni = findViewById(R.id.txNumerosDNI);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularLetraDNI();
            }
        });

    }

    private void calcularLetraDNI() {
        if(txNumerosDni.getText().toString().length() == 8){
            int valor = Integer.valueOf(txNumerosDni.getText().toString());
            int resultado = valor%23;
            String caracteresDivision = "TRWAGMYFPDXBNJZSQVHLCKE";
            String letraDNI = valueOf(caracteresDivision.charAt(resultado));
            Toast.makeText(this, "La letra es " + letraDNI, Toast.LENGTH_SHORT).show();
            txLetraDni.setText(format("%s %s", txNumerosDni.getText().toString(), letraDNI));
            ocultaTeclado();
        }else{
            Toast.makeText(getApplicationContext(),
                    R.string.error_longitud_caracteres,Toast.LENGTH_LONG).show();
        }

    }

    private void ocultaTeclado(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null)
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
