package com.joe.owo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.white.progressview.HorizontalProgressView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import models.Project;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Projects#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Projects extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    projectAdapter adapter;

    RecyclerView recyclerView;
    ArrayList<Project> mProjects;

    public Projects() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Projects.
     */
    // TODO: Rename and change types and number of parameters
    public static Projects newInstance(String param1, String param2) {
        Projects fragment = new Projects();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_projects, container, false);
        FloatingActionButton add_proj_fab = myView.findViewById(R.id.add_project_fab);
        add_proj_fab.setOnClickListener(this);
        recyclerView = myView.findViewById(R.id.project_recyclerview);
        mProjects = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(myView.getContext()));
        recyclerView.setAdapter(adapter);
        loadDate();
        return myView;
    }

    /*private void fetch() {
            Query query = FirebaseDatabase.getInstance()
                    .getReference()
                    .child("Users").child("projects");
            FirebaseRecyclerOptions<Project> options =
                    new FirebaseRecyclerOptions.Builder<Project>()
                            .setQuery(query, new SnapshotParser<Project>() {
                                @NonNull
                                @Override
                                public Project parseSnapshot(@NonNull DataSnapshot snapshot) {
                                    return new Project(snapshot.child("id").getValue().toString(),
                                            snapshot.child("description").getValue().toString(),
                                            snapshot.child("goal").getValue().toString(),
                                            snapshot.child("category").getValue().toString(),
                                            snapshot.child("startDate").getValue().toString(),
                                            snapshot.child("endDate").getValue().toString(),
                                            snapshot.child("totalAmount").getValue().toString(),
                                            snapshot.child("balance").getValue().toString(),
                                            snapshot.child("currentBal").getValue().toString(),
                                            snapshot.child("title").getValue().toString(),
                                            snapshot.child("owner").getValue().toString());
                                }
                            })
                            .build();

            adapter = new FirebaseRecyclerAdapter<Project, myViewholder>(options){

                @NonNull
                @Override
                public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.project_list_item, parent, false);

                    return new myViewholder(view);
                }

                @Override
                protected void onBindViewHolder(@NonNull myViewholder myViewholder, int i, @NonNull Project project) {
                    myViewholder.project_title_tv.setText(project.getTitle());
                    myViewholder.contributeAmount_tv.setText(project.getCurrentBal().toString());
                    myViewholder.totalAmount_tv.setText(project.getTotalAmount().toString());
                    myViewholder.projectOwner_tv.setText(project.getOwner());
                    double total = Double.parseDouble( project.getTotalAmount());
                    double contributed =Double.parseDouble(project.getCurrentBal());
                    double percentage = (contributed/total)*100;
                    myViewholder.progressView.setProgress((int)percentage);
                }

            };
            recyclerView.setAdapter(adapter);
        }
*/

public void loadDate(){
   FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("Users");

ref.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        mProjects = new ArrayList<>();
        for(DataSnapshot usershot: snapshot.getChildren()){
            DataSnapshot projecstShot = usershot.child("projects");
            for(DataSnapshot projectSnapshot :projecstShot.getChildren() ){
                Project project = new Project();
                project.setTitle( projectSnapshot.child("title").getValue(String.class));
                project.setStartDate( projectSnapshot.child("startDate").getValue(String.class));
                project.setEndDate( projectSnapshot.child("endDate").getValue(String.class));
                project.setId( projectSnapshot.child("id").getValue(String.class));
                project.setGoal( projectSnapshot.child("goal").getValue(String.class));
                project.setDescription( projectSnapshot.child("description").getValue(String.class));
                project.setCategory( projectSnapshot.child("category").getValue(String.class));
                project.setTotalAmount( projectSnapshot.child("totalAmount").getValue(String.class));
                project.setCurrentBal( projectSnapshot.child("currentBal").getValue(String.class));
                project.setOwner( projectSnapshot.child("owner").getValue(String.class));
           mProjects.add(project);
            }
        }
        adapter = new projectAdapter(mProjects,getContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});

}

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), add_project.class);
        startActivity(intent);
    }


     /*class myViewholder extends RecyclerView.ViewHolder {

        CircleImageView feautredImage;
        MaterialTextView project_title_tv, projectOwner_tv, contributeAmount_tv, totalAmount_tv;
        HorizontalProgressView progressView;

        public myViewholder(@NonNull View itemView) {
            super(itemView);

            feautredImage = itemView.findViewById(R.id.project_avatar);
            project_title_tv = itemView.findViewById(R.id.campaign_title_tv);
            projectOwner_tv = itemView.findViewById(R.id.project_ownername_tv);
            contributeAmount_tv = itemView.findViewById(R.id.project_contibute_amount);
            totalAmount_tv = itemView.findViewById(R.id.project_total_tv);
            progressView = itemView.findViewById(R.id.project_progress);
        }

        public void setProject_title_tv(String string) {
            project_title_tv.setText(string);
        }

        public void setContributeAmount_tv(String string) {
            contributeAmount_tv.setText(string);
        }

        public void setTotalAmount_tv(String string) {
            totalAmount_tv.setText(string);
        }
        }*/

    /*@Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }*/
}

