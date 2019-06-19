package com.example.databasefirebse;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    RecyclerView recyclerView;
    Button editComplaint;
    FloatingActionButton fab;
    ArrayList<Model> models;
    FirebaseFirestore db;
    FirebaseUser firebaseUser;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        recyclerView=findViewById(R.id.recyclerView);
        editComplaint=findViewById(R.id.editComplaintBtn);
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

        //ch1.setChecked(true);
        checkbox();

        fab=findViewById(R.id.floatingBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,EditComplaint.class));
            }
        });

        models=new ArrayList<Model>();
        db=FirebaseFirestore.getInstance();
        editComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,EditComplaint.class));
                finish();
            }
        });
        loadData();
        recyclerViewSetUp();
    }

    private void checkbox() {

    }

    private void loadData() {
        db.collection(firebaseUser.getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot documentSnapshot:task.getResult()){
                            Model model=new Model(documentSnapshot.getString("roomDetail"),
                                    documentSnapshot.getString("complaint"));
                            models.add(model);
                        }
                        adapter=new Adapter(getApplicationContext(),models);
                        recyclerView.setAdapter(adapter);
                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Profile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void recyclerViewSetUp() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
