package appmoviles.com.appsmoviles20191;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private RecyclerView lista_amigos;
    private Button btn_agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista_amigos = findViewById(R.id.lista_amigos);
        btn_agregar = findViewById(R.id.btn_agregar);

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AgregarAmigoActivity.class);
                startActivity(i);
            }
        });

    }
}
