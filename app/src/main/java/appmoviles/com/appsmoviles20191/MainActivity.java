package appmoviles.com.appsmoviles20191;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import appmoviles.com.appsmoviles20191.db.DBHandler;

public class MainActivity extends AppCompatActivity {

    private RecyclerView lista_amigos;
    private Button btn_agregar;

    DBHandler localdb;
    private AdapterAmigos adapterAmigos;
    FirebaseAuth auth;
    private Button btn_signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localdb = DBHandler.getInstance(this);
        auth = FirebaseAuth.getInstance();


        //Si no hay usuario loggeado
        if(auth.getCurrentUser() == null){

            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();

            return;
        }

        btn_signout = findViewById(R.id.btn_signout);
        lista_amigos = findViewById(R.id.lista_amigos);
        btn_agregar = findViewById(R.id.btn_agregar);
        adapterAmigos = new AdapterAmigos();
        lista_amigos.setLayoutManager(new LinearLayoutManager(this));
        lista_amigos.setAdapter(adapterAmigos);
        lista_amigos.setHasFixedSize(true);



        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AgregarAmigoActivity.class);
                startActivity(i);
            }
        });

        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterAmigos.showAllAmigos(localdb.getAllAmigos());
    }
}

