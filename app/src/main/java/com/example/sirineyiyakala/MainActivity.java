package com.example.sirineyiyakala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtTime,txtScore;
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9;
    int score;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score=0;

        txtTime=findViewById(R.id.txtTime);
        txtScore=findViewById(R.id.txtScore);
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        imageArray=new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        hideSirine();

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                txtTime.setText("Time: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                txtTime.setText("Finished!");
                handler.removeCallbacks(runnable);

                for(ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Devam Edilsin mi?");
                alert.setMessage("Emin misiniz?");

                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"OYUN BİTTİ!",Toast.LENGTH_LONG).show();
                    }
                });
                alert.setCancelable(false);
                alert.show();
            }
        }.start();
    }

    public void hideSirine() {
        handler=new Handler();

        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                Random random=new Random();
                int sayi=random.nextInt(9);
                imageArray[sayi].setVisibility(View.VISIBLE);

                handler.postDelayed(runnable,500);
            }
        };

        handler.post(runnable);

    }

    public void  sirineClick(View view){
        score++;
        txtScore.setText("Score: "+score);
    }
}