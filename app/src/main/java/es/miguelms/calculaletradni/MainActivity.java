package es.miguelms.calculaletradni;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.valueOf;

/**
 * @author Miguel Martínez Serrano - miguelms.es
 *
 **/
public class MainActivity extends AppCompatActivity {

    private TextView txLetraDni;
    private EditText txNumerosDni;
    private View btCopiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btCalcular = findViewById(R.id.btCalcular);
        btCopiar = findViewById(R.id.btCopiar);
        
        txLetraDni = findViewById(R.id.txLetraDni);
        txNumerosDni = findViewById(R.id.txNumerosDNI);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularLetraDNI();
            }
        });

        btCopiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copiarAlPortapapeles();
            }
        });
        
        
    }

    private void copiarAlPortapapeles() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("texto", txLetraDni.getText().toString());
        if (clipboard != null) {
            clipboard.setPrimaryClip(clip);
        }
    }

    private void calcularLetraDNI() {
        if(txNumerosDni.getText().toString().length() == 8){
            String caracteresDivision = "TRWAGMYFPDXBNJZSQVHLCKE";

            int valor = Integer.valueOf(txNumerosDni.getText().toString());
            int resultado = valor%23;

            String letraDNI = valueOf(caracteresDivision.charAt(resultado));
            Toast.makeText(this, "La letra es " + letraDNI, Toast.LENGTH_SHORT).show();
            txLetraDni.setText(String.format("%s %s", txNumerosDni.getText().toString(), letraDNI));
            ocultaTeclado();
            btCopiar.setVisibility(View.VISIBLE);
        }else{
            Toast.makeText(getApplicationContext(),
                    R.string.error_longitud_caracteres,Toast.LENGTH_LONG).show();
        }

    }

    private void ocultaTeclado(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm;
            imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null)
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
