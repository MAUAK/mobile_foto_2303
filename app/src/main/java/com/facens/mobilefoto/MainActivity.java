package com.facens.mobilefoto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Criando variáveis privadas de imagem e botão
    private ImageView imageViewFoto;
    private Button btnGeo;

    //Reescrevendo o código para abrir o layout do activity main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Está achando o botão do design e declarando ele como a variável botão e verificando a permissão da localização
        btnGeo = (Button) findViewById(R.id.btn_gps);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123) ;

        //Vendo se o botão foi clicado
        btnGeo.setOnClickListener(new View.OnClickListener(){
            //Reescrevendo o código para criar um método de quando clicar, pegar uma variavel tipo gpstracker (outra classe) e uma de localização
            @Override
            public void onClick(View view){
                GPStracker g = new GPStracker (getApplication());
                Location l = g.getLocation();

                //Verificando se a localização não for nula, criar duas variaveis double  para pegar a latitude e longitude da variavel location
                if(l != null){
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LATITUDE:" +lat+"/nLONGITUDE:"+lon,Toast.LENGTH_LONG).show();
                }
            }
        });
        //Checando se a permissão da camera no android manifest foi habilitada
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 0);
        }
        //Está achando a imagem do design e declarando ela como a variável imagem
        imageViewFoto = (ImageView) findViewById(R.id.imageView2);
        findViewById(R.id.btn_pic).setOnClickListener(new View.OnClickListener(){
            //Reescrevendo o código para assim que clicar, chamar o método de tirar foto
            @Override
            public void onClick(View view) {
                tirarFoto();
            }
            //Fechando o método de chamar a ação do botão quando clicado
        });
    }
    //Criando o método de tirar foto e  tirando a foto da camera do celular
    private void tirarFoto(){
        Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }
    //Reescrevendo o código
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        //
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imagem);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}