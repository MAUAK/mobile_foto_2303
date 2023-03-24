package com.facens.mobilefoto;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Toast;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class GPStracker implements LocationListener {


    Context context;

    //Criando método com parametro c, e dentro dele setando a variavel contexto como c
    public GPStracker(Application c) {
        context = c;
    }

    //Criando método para pegar a localização
    public Location getLocation() {
        //Checando permissão no android manifest sobre a localização do celular
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(context, "Não foi permitido", Toast.LENGTH_LONG).show();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnable = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        //se o gps estiver habilitado, vai setar o tempo de resposta do gps
        if (isGPSEnable) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        } else {
            //se não estiver habilitado, pedir para o usuario habilitar o gps
            Toast.makeText(context, "Por favor, habilitar GPS!", Toast.LENGTH_LONG).show();
        }
        //após as verificações, retornar nulo
        return null;
    }
    @Override
    public void onProviderDisabled(@NonNull String provider){ }
    //Reescreve o método para mudar a localização caso o usúario se mova desde a última verificação
    @Override
    public void onLocationChanged(@NonNull Location location){}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras){}
}


