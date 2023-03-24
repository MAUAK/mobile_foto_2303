package com.facens.mobilefoto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    //Criando uma variável de tempo e adicionando um novo valor a ela
    private final Timer timer = new Timer();
    TimerTask timerTask;

    //Reescrevendo o código para criar uma instância para aparecer a tela de intro primeiro
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Após o tempo que está em uma variável, ele chama um método para rodar a tela padrão (mainActivity)
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gotoMainActivity();
                    }
                });
            }
        };
        //Setando o tempo como 300 milisegundos
        timer.schedule(timerTask, 3000);
    }
    //Criando um método para chamar a classe da tela do mainActivity
    private void gotoMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}