package com.example.databasefirebse;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditComplaint extends AppCompatActivity {

    EditText roomNo,complaint;
    Button submit;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_complaint);
        roomNo=findViewById(R.id.roomDetails);
        complaint=findViewById(R.id.complaintDetails);
        submit=findViewById(R.id.submitComplaintBtn);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        firebaseFirestore=FirebaseFirestore.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitComplaint();
                progressDialog.show();
            }
        });
    }

    private void submitComplaint() {
        final int otp=(int)(Math.random()*9000)+1000;
        Map<String, Object> user =new HashMap<>();
        user.put("roomDetail",roomNo.getText().toString());
        user.put("complaint",complaint.getText().toString());
        user.put("status","");
        user.put("otp",otp);

        firebaseFirestore.collection(firebaseUser.getEmail())
                .add(user)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()){
                            progressDialog.dismiss();
                            AlertDialog.Builder builder=new AlertDialog.Builder(EditComplaint.this);
                            builder.setTitle("Otp ")
                                    .setMessage("Your otp no is : "+otp)
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            startActivity(new Intent(EditComplaint.this,Profile.class));
                                            finish();
                                        }
                                    })
                                    .create()
                                    .show();
                        }
                        else {
                            Toast.makeText(EditComplaint.this, "File Not Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
