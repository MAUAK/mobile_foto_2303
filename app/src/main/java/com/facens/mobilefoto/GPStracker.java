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

    //
    public GPStracker(Application c) {
        context = c;
    }

    //
    public Location getLocation() {
        //
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(context, "Não foi permitido", Toast.LENGTH_LONG).show();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnable = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        //
        if (isGPSEnable) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        } else {
            //
            Toast.makeText(context, "Por favor, habilitar GPS!", Toast.LENGTH_LONG).show();
        }
        //
        return null;
    }
    @Override
    public void onProviderDisabled(@NonNull String provider){ }
    //
    @Override
    public void onLocationChanged(@NonNull Location location){}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras){}
}


