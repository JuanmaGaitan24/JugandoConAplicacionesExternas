package com.example.jugandoconaplicacionesexternas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button btnLanzarWeb;
Button btnMapa;
Button btnMensaje;
Button btnEnviarMail;
Button btnLlamar;
EditText editTextWeb, editTextMail, editTextAsunto, editTextCuerpo;
String coordenadas = "38.299191,-5.2712442";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLanzarWeb = findViewById(R.id.buttonLanzarWeb);
        btnMapa = findViewById(R.id.buttonMapa);
        btnMensaje = findViewById(R.id.buttonMensaje);
        editTextWeb = findViewById(R.id.editTextWeb);
        editTextMail = findViewById(R.id.editTextMail);
        editTextAsunto = findViewById(R.id.editTextAsunto);
        editTextCuerpo = findViewById(R.id.editTextCuerpo);
        btnEnviarMail = findViewById(R.id.buttonEnviarMail);
        btnLlamar = findViewById(R.id.buttonLlamar);

        btnLanzarWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(editTextWeb.getText().toString()));

                Intent seleccionador = Intent.createChooser(intent, getString(R.string.nota_informativa));

                startActivity(seleccionador);
            }
        });

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:"+coordenadas));
                startActivity(intent);
            }
        });

        btnMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Buenos Dias");
                intent.setPackage("com.whatsapp");
                try {
                    startActivity(intent);
                }catch (ActivityNotFoundException e){
                    Toast.makeText(MainActivity.this, "Instala el whatsapp", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnEnviarMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{editTextMail.getText().toString()});
                intent.putExtra(Intent.EXTRA_SUBJECT, editTextAsunto.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, editTextCuerpo.getText().toString());
                intent.setType("message/rfc822");
                startActivity(intent);
            }
        });

        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.putExtra(Intent.EXTRA_PHONE_NUMBER, Uri.parse("tel:699658755"));
                startActivity(intent);

            }
        });

    }
}