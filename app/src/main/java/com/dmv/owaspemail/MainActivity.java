package com.dmv.owaspemail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listaEmails;
    boolean[] leido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] fotosEmail = {
                R.drawable.pan,
                R.drawable.vj,
                R.drawable.nelson,
                R.drawable.claro,
                R.drawable.tigo_4_0,
                R.drawable.trump,
                R.drawable.calavera
        };
        String[] nombresEmail = {
                "Panadería la 80",
                "Victoria Justice (Mi ex)",
                "Nelson Augusto Benitez Montoya",
                "Claro",
                "Tigo UNE",
                "Donald Trump",
                "PHL"
        };
        String[] descripciones = {
                "Panaderia la 80 te invita a una semana de descuentos en todos los panes",
                "Por favor dame otra oportunidad... No volveré a cometer el mismo error",
                "Recuerden entregar las tareas a tiempo muchachos... No importa que me suban en link a classroom tarde, si  hicieron el último commit en github en dentro de la fecha se los valgo, sino, cerito",
                "Pásate a Claro con nuestros planes post pago. Por favor amigo, pásate. Por favor.Por favor.Por favor.Por favor.Por favor.Por favor.",
                "Pásate a Tigo UNE con nuestros planes post pago. Por favor amigo, pásate. Por favor.Por favor.Por favor.Por favor.Por favor.Por favor.",
                "Hey bro, no pensé que fueras tan eficiente... Quiero que a partir de hoy seas mi mano derecha, vamos a invadir venezuela pronto y juntos lograremos la victoria. Espero tu respuesta pronto.",
                "Los 'suministros' que tomamos prestados ya están en el negocio... Esos cabrones se ven realmente molestos y los estan buscando por todas partes JAJAJA. Es mejor que los pongas a producir antes de que los encuentren y se arme una grande... Huevos largos, fuera"
        };
        String[] horas = {
                "12:30 PM",
                "3:33 AM",
                "4:20 PM",
                "1:00 PM",
                "2:15PM",
                "6:15 AM",
                "2:30 PM"
        };
        Boolean[] reenviado = {
                true,
                false,
                true,
                true,
                true,
                false,
                false,
        };
        leido = new boolean[nombresEmail.length];

        ListAdapter emails = new ListAdapter(MainActivity.this, nombresEmail,horas,fotosEmail,descripciones, reenviado, leido);
        listaEmails = (ListView) findViewById(R.id.listaEmails);
        listaEmails.setAdapter(emails);

        listaEmails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                leido[position] = true;
                emails.notifyDataSetChanged();
                Intent SendInfo = new Intent(MainActivity.this, MailActivity.class);
                        SendInfo.putExtra("emisor", nombresEmail[position])
                        .putExtra("hora", horas[position])
                        .putExtra("foto", fotosEmail[position])
                        .putExtra("asunto", descripciones[position])
                        .putExtra("reenviado:", reenviado[position]);
                startActivity(SendInfo);
            }
        });

    }
}
