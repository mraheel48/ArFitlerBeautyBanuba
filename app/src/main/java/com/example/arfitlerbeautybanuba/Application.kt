package com.example.arfitlerbeautybanuba

import com.banuba.sdk.manager.BanubaSdkManager

class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()

        // It crashes if token is empty string with
        //
        // RuntimeException:
        //  Unable to create application com.banuba.sdk.samples.SamplesApp:
        //  java.lang.RuntimeException: Can't parse client token.
        //
        //  Please, contact Banuba for obtain a correct client token.

        try {
            BanubaSdkManager.initialize(this, BANUBA_CLIENT_TOKEN)
        } catch (ex: RuntimeException) {
            ex.printStackTrace()
        }

    }
}