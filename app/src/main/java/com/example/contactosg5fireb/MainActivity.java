package com.example.contactosg5fireb;

import android.content.Intent;
import android.os.Bundle;

import com.example.contactosg5fireb.adapters.ContactoAdapter;
import com.example.contactosg5fireb.models.ContactoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton btn_main_registro;
    private ListView lv_main_contactos;
    private ContactoModel model;
    private ArrayList<ContactoModel> list;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference contactosRef;
    private final String Coleccion = "Contactos";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        btn_main_registro = findViewById(R.id.btn_main_registro);
        lv_main_contactos = findViewById(R.id.lv_main_contactos);
        contactosRef = database.getReference(Coleccion);
        list = new ArrayList<>();

        contactosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot obj : dataSnapshot.getChildren()){
                    model = obj.getValue(ContactoModel.class);
                    list.add(model);
                }
                lv_main_contactos.setAdapter(new ContactoAdapter(MainActivity.this, list));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Error " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        lv_main_contactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                model = list.get(i);
                Intent detalle = new Intent(MainActivity.this, DetalleActivity.class);
                detalle.putExtra("id", model.get_id());
                startActivity(detalle);
            }
        });

        btn_main_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registro = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(registro);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
