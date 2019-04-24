package com.lutherrichardson.finalproject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // declare our view variables
    private Button submitButton;
    private EditText nameInput;
    private EditText budgetInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    // create the user
    final User user = new User();

    // assign the views from the layout file to the corresponding vars
    submitButton = findViewById(R.id.SubmitButton);
    nameInput = findViewById(R.id.nameInput);
    budgetInput = findViewById(R.id.budgetInput);


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            // on click, update the textview
            String newText = nameInput.getText().toString();
            user.setNameOfUser(nameInput.getText().toString());
            user.setBankAccount(Double.parseDouble(budgetInput.getText().toString()));
            startShopping(user.getNameOfUser(), user.getBankAccount());
        }
    };
    submitButton.setOnClickListener(listener);
    }


    protected void startShopping(String userName, Double userBudget){
        Intent intent = new Intent(this, itemInputScreen.class);

        intent.putExtra("userName", userName);
        intent.putExtra("userBudget", userBudget);

        startActivity(intent);

    }


}





