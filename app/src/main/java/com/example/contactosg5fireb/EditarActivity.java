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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditarActivity extends AppCompatActivity {
    private EditText et_editar_nombre,
            et_editar_apellido,
            et_editar_celular,
            et_editar_numero_fijo,
            et_editar_numero_trabajo,
            et_editar_correo;
    private TextView tv_editar_nombres;
    private Button btn_editar_actualizar;
    private ContactoModel model;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference contactosRef;
    private final String Coleccion = "Contactos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        Toolbar toolbar = findViewById(R.id.toolbar_editar);
        setSupportActionBar(toolbar);

        tv_editar_nombres = findViewById(R.id.tv_editar_nombres);
        et_editar_nombre = findViewById(R.id.et_editar_nombre);
        et_editar_apellido = findViewById(R.id.et_editar_apellido);
        et_editar_celular = findViewById(R.id.et_editar_celular);
        et_editar_numero_fijo = findViewById(R.id.et_editar_fijo);
        et_editar_numero_trabajo = findViewById(R.id.et_editar_trabajo);
        et_editar_correo = findViewById(R.id.et_editar_correo);
        btn_editar_actualizar = findViewById(R.id.btn_editar_actualizar);
        contactosRef = database.getReference(Coleccion);

        String id = getIntent().getStringExtra("id");

        contactosRef.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                model = dataSnapshot.getValue(ContactoModel.class);
                String nombreC = model.get_nombre() + " " + model.get_apellido();
                tv_editar_nombres.setText(nombreC);
                et_editar_nombre.setText(model.get_nombre());
                et_editar_apellido.setText(model.get_apellido());
                et_editar_celular.setText(model.get_numeroCelular());
                et_editar_numero_fijo.setText(model.get_numeroFijo());
                et_editar_numero_trabajo.setText(model.get_numeroTrabajo());
                et_editar_correo.setText(model.get_correoElectronico());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EditarActivity.this, "Error " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        btn_editar_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre_defecto = "(Sin nombre)";
                String registro_nombre = et_editar_nombre.getText().toString();
                String registro_apellido = et_editar_apellido.getText().toString();
                String registro_numero_celular = et_editar_celular.getText().toString();
                String registro_numero_fijo = et_editar_numero_fijo.getText().toString();
                String registro_numero_trabajo = et_editar_numero_trabajo.getText().toString();
                String registro_correo_electronico = et_editar_correo.getText().toString();

                if(registro_nombre.isEmpty()){
                    registro_nombre = nombre_defecto;
                }

                model.set_nombre(registro_nombre);
                model.set_apellido(registro_apellido);
                model.set_numeroCelular(registro_numero_celular);
                model.set_numeroFijo(registro_numero_fijo);
                model.set_numeroTrabajo(registro_numero_trabajo);
                model.set_correoElectronico(registro_correo_electronico);

                /*
                adapter.openWrite();
                int update = adapter.updateModel(model);
                adapter.close();

                if (update>0){
                    Intent detalle = new Intent(EditarActivity.this, DetalleActivity.class);
                    model.set_id(update);
                    detalle.putExtra("model", model);
                    startActivity(detalle);
                    finish();
                }else{
                    Snackbar.make(view, "Registro no actualizado", Snackbar.LENGTH_SHORT).show();
                }
                */
            }
        });
    }

}
