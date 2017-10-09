package basilk.com.foodapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import basilk.com.foodapp.model.user;

public class SignIn extends AppCompatActivity {

    Button btnOK;
    EditText phoneTxt,passwordTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btnOK=(Button)findViewById(R.id.btnOk);
        phoneTxt=(EditText)findViewById(R.id.edtTxtphone);
        passwordTxt=(EditText)findViewById(R.id.edtTxtpassword);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("users");



        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phoneTxt.getText().toString()).exists()) {
                            user userobj = dataSnapshot.child(phoneTxt.getText().toString()).getValue(user.class);

                            if (userobj.getPassword().equals(passwordTxt.getText().toString())) {
                                Toast.makeText(SignIn.this, "Success", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(SignIn.this, "Wrong password!!", Toast.LENGTH_LONG).show();
                            }


                        }
                        else
                            Toast.makeText(SignIn.this, "user doesnt exists", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}
