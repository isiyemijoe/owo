package com.joe.owo;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;

import de.hdodenhof.circleimageview.CircleImageView;
import models.Project;

public class add_project extends AppCompatActivity {
    private Toolbar toolbar;
    CircleImageView featuredImage;
    TextInputLayout project_title_tv, short_descip_tv, goal_tv, startDate_tv, endDate_tv;
   PowerSpinnerView category;
    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    MaterialButton add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));

        project_title_tv = findViewById(R.id.campaign_title_tv);
        short_descip_tv = findViewById(R.id.campaign_description_tv);
        goal_tv = findViewById(R.id.campaign_goal_tv);
        category = findViewById(R.id.category_spinner);
        startDate_tv = findViewById(R.id.campaign_startdate_tv);
        endDate_tv = findViewById(R.id.campaign_enddate_tv);
        add_btn = findViewById(R.id.add_project_btn);
        mAuth = FirebaseAuth.getInstance();


        Project project = new Project();
        final String[] sCategory = {"Uncategorized"};
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        assert user != null;
        String username = user.getUid();
        String userId = user.getUid();


        category.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<String>() {
            @Override
            public void onItemSelected(int i, String s) {

            }
        });


add_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String title = project_title_tv.getEditText().getText().toString();
        String desc = short_descip_tv.getEditText().getText().toString();
        String goal = goal_tv.getEditText().getText().toString();
        String startDate = startDate_tv.getEditText().getText().toString();
        String endDate = endDate_tv.getEditText().getText().toString();
        String balance = "0";
        project.setCategory(sCategory[0]);
        project.setBalance(balance);
        project.setCurrentBal("0");
        project.setTotalAmount(goal);
        project.setDescription(desc);
        project.setEndDate(endDate);
        project.setGoal(goal);
        project.setTitle(title);
        project.setId(title + username);
        project.setOwner(user.getEmail());
        project.setStartDate(startDate);

        mDatabase.child("Users").child(userId).child("projects").child(title + username).setValue(project);
        Toast.makeText(add_project.this,"Project Added", Toast.LENGTH_LONG ).show();
        Intent intent = new Intent(add_project.this, Dashboard.class);
        startActivity(intent);
        finish();
    }
});



    }

}
