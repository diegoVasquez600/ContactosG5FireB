package com.example.contactosg5fireb;

import android.content.Intent;
import android.os.Bundle;

import com.example.contactosg5fireb.adapters.ContactoAdapter;
import com.example.contactosg5fireb.models.ContactoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistroActivity extends AppCompatActivity {

    private EditText et_registro_nombre,
            et_registro_apellido,
            et_registro_numero_celular,
            et_registro_numero_fijo,
            et_registro_numero_trabajo,
            et_registro_correo_electronico;
    private FloatingActionButton btn_registro_guardar;
    private ContactoModel model;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference contactosRef;
    private final String Coleccion = "Contactos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Toolbar toolbar = findViewById(R.id.toolbar_registro);
        setSupportActionBar(toolbar);

        et_registro_nombre = findViewById(R.id.et_registro_nombre);
        et_registro_apellido = findViewById(R.id.et_registro_apellido);
        et_registro_numero_celular = findViewById(R.id.et_registro_numero_celular);
        et_registro_numero_fijo = findViewById(R.id.et_registro_numero_fijo);
        et_registro_numero_trabajo = findViewById(R.id.et_registro_numero_trabajo);
        et_registro_correo_electronico = findViewById(R.id.et_registro_correo_electronico);
        btn_registro_guardar = findViewById(R.id.btn_registro_guardar);
        model = new ContactoModel();
        contactosRef = database.getReference(Coleccion);

        btn_registro_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre_defecto = "(Sin nombre)";
                String registro_nombre = et_registro_nombre.getText().toString();
                String registro_apellido = et_registro_apellido.getText().toString();
                String registro_numero_celular = et_registro_numero_celular.getText().toString();
                String registro_numero_fijo = et_registro_numero_fijo.getText().toString();
                String registro_numero_trabajo = et_registro_numero_trabajo.getText().toString();
                String registro_correo_electronico = et_registro_correo_electronico.getText().toString();

                if(registro_nombre.isEmpty()){
                    registro_nombre = nombre_defecto;
                }

                String key = contactosRef.push().getKey();
                if(key != null){
                    model = new ContactoModel(key, registro_nombre, registro_apellido, registro_numero_celular, registro_numero_fijo, registro_numero_trabajo, registro_correo_electronico);
                    contactosRef.child(key).setValue(model);
                    Intent lista = new Intent(RegistroActivity.this, MainActivity.class);
                    startActivity(lista);
                }

                /*
                if (insert>0){
                    Snackbar.make(view, "Registro exitoso", Snackbar.LENGTH_SHORT).show();
                    Intent detalle = new Intent(RegistroActivity.this, DetalleActivity.class);
                    model.set_id(insert);
                    detalle.putExtra("model", model);
                    startActivity(detalle);
                    finish();
                }else{
                    Snackbar.make(view, "Registro no exitoso", Snackbar.LENGTH_SHORT).show();
                }
                */
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
