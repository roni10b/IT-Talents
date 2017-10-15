package ivan.app.com.testappfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView welcome;
    private Button logout;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        }
        firebaseUser = firebaseAuth.getCurrentUser();
        welcome = (TextView)findViewById(R.id.home_welcome);
        logout = (Button)findViewById(R.id.home_logout);
        welcome.setText(welcome.getText() + firebaseUser.getEmail());
    }

    @Override
    public void onClick(View v) {
        if (v == logout) {
            logoutUser();
        }
    }

    private void logoutUser() {
        firebaseAuth.signOut();
//        firebaseUser = null;
//        Toast.makeText(HomeActivity.this, firebaseUser.getEmail().toString(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
    }
}
