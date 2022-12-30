package com.example.cryptchat;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OneToOneChat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OneToOneChat extends Fragment {

    FirebaseAuth auth;
    RecyclerView mainUserREcyclerView;
    UserAdapter adapter;
    FirebaseDatabase databse;
    ArrayList<Users> usersArrayList;
    ImageView img_logout;

    private long backPressedTime;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OneToOneChat() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OneToOneChat.
     */
    // TODO: Rename and change types and number of parameters
    public static OneToOneChat newInstance(String param1, String param2) {
        OneToOneChat fragment = new OneToOneChat();
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

            auth = FirebaseAuth.getInstance();
            databse = FirebaseDatabase.getInstance();

            usersArrayList = new ArrayList<>();

            DatabaseReference reference = databse.getReference().child("user");

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                        Users users = dataSnapshot.getValue(Users.class);
//                    Users users = new Users();
//                    users.setUid(dataSnapshot.child("uid").child("uid"));
//                    users.setName(dataSnapshot.child("name").toString());
//                    users.setNumber(dataSnapshot.child("number").toString());
                        usersArrayList.add(users);
//                    System.out.println("********************");
//                    System.out.println(dataSnapshot.child("uid").toString());
//                    System.out.println(dataSnapshot.child("name").toString());
//                    System.out.println(dataSnapshot.child("number").toString());
//                    System.out.println("********************");
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

//        img_logout = findViewById(R.id.img_logout);
            mainUserREcyclerView = (RecyclerView) getView().findViewById(R.id.mainUserRecyclerView);
            mainUserREcyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new UserAdapter((HomeActivity) getContext(), usersArrayList);
            mainUserREcyclerView.setAdapter(adapter);



//        img_logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog dialog = new Dialog(HomeActivity.this, R.style.Dialoge);
//
//                dialog.setContentView(R.layout.dialog_logout);
//
//                TextView yes_btn, no_btn;
//
//                yes_btn = dialog.findViewById(R.id.yes_btn);
//                no_btn = dialog.findViewById(R.id.no_btn);
//
//                yes_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        FirebaseAuth.getInstance().signOut();
//                        startActivity(new Intent(HomeActivity.this, RegistrationActivity.class));
//                    }
//                });
//
//                no_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//
//                dialog.show();
//            }
//        });

            if(auth.getCurrentUser()==null)
            {
                startActivity(new Intent(getContext(), MainActivity.class));
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one_to_one_chat, container, false);
    }
}