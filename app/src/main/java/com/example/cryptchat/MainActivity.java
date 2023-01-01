package com.example.cryptchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity {

    private String data = "", number = "", name = "";
    char[]  dataCharacter;
    private String dbNumber;
    AES245Encryption encryption;
    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;
    MotionLayout motionLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        encryption = new AES245Encryption();
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        MotionLayout motionLayout = findViewById(R.id.motionLayout);
        motionLayout.startLayoutAnimation();
        motionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {

//                Toast.makeText(MainActivity.this, "Helllo", Toast.LENGTH_SHORT).show();

                //Reading the File
                try{
                    FileInputStream fin=openFileInput("user.txt");
                    int i=0;
                    while((i=fin.read())!=-1){
                        data += (char)i;
                    }
                    System.out.println("********************************************");
                    data = encryption.decrypt(data);
                    System.out.println("********************************************");

                    dataCharacter = data.toCharArray();



                    //Separating Name and number
                    for (int j = 0; j<10; j++){
                        number += dataCharacter[j];
                    }
                    if (number.length() == 10){
                        System.out.println("*********************iffffffffffffff***********************");
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }else{
                        System.out.println("********************else************************");
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);

                    }
                    fin.close();
                }catch(Exception e){
                    System.out.println("******************Exception**************************");
                    System.out.println(e);
                    Intent intent = new Intent(MainActivity.this, OtpAuthentication.class);
                    startActivity(intent);
                }






            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {

            }
        });
    }
}