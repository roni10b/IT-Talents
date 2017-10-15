package ivan.app.com.testappfirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText email, psw;
    private Button reg;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.reg_email);
        psw = (EditText) findViewById(R.id.reg_pass);
        reg = (Button) findViewById(R.id.reg_register);
    }
    
    private void registerUser() {
        String userMail = email.getText().toString();
        String userPsw = psw.getText().toString();
        final ProgressDialog dialog = ProgressDialog.show(RegisterActivity.this, "Please wait", "Processing..", true);
        firebaseAuth.createUserWithEmailAndPassword(userMail, userPsw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                dialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("email", email.getText().toString());
                    intent.putExtra("psw", psw.getText().toString());
                    setResult(1, intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "not added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//
    @Override
    public void onClick(View v) {
        if (v == reg) {
            registerUser();
        }
    }
}
