package ivan.app.com.testappfirebase;

import android.app.Instrumentation;
import android.content.Intent;
import android.preference.PreferenceManager;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText email, psw;
    private Button log, reg;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.log_email);
        psw = (EditText) findViewById(R.id.log_pass);
        log = (Button) findViewById(R.id.log_login);
        reg = (Button) findViewById(R.id.log_register);
    }

    @Override
    public void onClick(View v) {
        if (v == reg) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivityForResult(intent, 3);
        }
        if (v == log) {
            logUser();
        }
    }

    private void logUser() {
        String m = email.getText().toString();
        String p = psw.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(m,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid user", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1) {
            String mail = data.getStringExtra("email").toString();
            String pas = data.getStringExtra("psw").toString();
            email.setText(mail);
            psw.setText(pas);
        }
    }
}
