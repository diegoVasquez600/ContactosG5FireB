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
import android.widget.TextView;
import android.widget.Toast;

public class DetalleActivity extends AppCompatActivity {
    private TextView tv_detalle_nombres,
            tv_detalle_celular,
            tv_detalle_numero_fijo,
            tv_detalle_numero_trabajo,
            tv_detalle_correo;
    private Button btn_detalle_editar,
            btn_detalle_eliminar;
    private ContactoModel model;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference contactosRef;
    private final String Coleccion = "Contactos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Toolbar toolbar = findViewById(R.id.toolbar_detalle);
        setSupportActionBar(toolbar);

        tv_detalle_nombres = findViewById(R.id.tv_detalle_nombres);
        tv_detalle_celular = findViewById(R.id.tv_detalle_celular);
        tv_detalle_numero_fijo = findViewById(R.id.tv_detalle_numero_fijo);
        tv_detalle_numero_trabajo = findViewById(R.id.tv_detalle_numero_trabajo);
        tv_detalle_correo = findViewById(R.id.tv_detalle_correo);
        btn_detalle_editar = findViewById(R.id.btn_detalle_editar);
        btn_detalle_eliminar = findViewById(R.id.btn_detalle_eliminar);
        contactosRef = database.getReference(Coleccion);

        String id = getIntent().getStringExtra("id");
        contactosRef.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                model = dataSnapshot.getValue(ContactoModel.class);
                String nombre_completo = model.get_nombre() + "\n" +model.get_apellido();
                tv_detalle_nombres.setText(nombre_completo);
                tv_detalle_celular.setText(model.get_numeroCelular());
                tv_detalle_numero_fijo.setText(model.get_numeroFijo());
                tv_detalle_numero_trabajo.setText(model.get_numeroTrabajo());
                tv_detalle_correo.setText(model.get_correoElectronico());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DetalleActivity.this, "Error " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



        btn_detalle_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar snackbar = Snackbar.make(view, "¿Está seguro de eliminar a " + model.get_nombre() + " ?", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("¡Sisas!", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /*
                        adapter.openWrite();
                        int deleted = adapter.delete(model.get_id());
                        adapter.close();

                        if (deleted>0){
                            Intent lista = new Intent(DetalleActivity.this, MainActivity.class);
                            startActivity(lista);
                            finish();
                        }else{
                            Snackbar.make(view, "Lo siento, no se pudo eliminar, intenta mas tarde, Kathe fue la que dijo eso.", Snackbar.LENGTH_LONG).show();
                        }
                        */
                    }
                });
                snackbar.show();
            }
        });

        btn_detalle_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editar = new Intent(DetalleActivity.this, EditarActivity.class);
                editar.putExtra("id", model.get_id());
                startActivity(editar);
                finish();
            }
        });
    }
}