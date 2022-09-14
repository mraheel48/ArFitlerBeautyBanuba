package com.example.arfitlerbeautybanuba

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.banuba.sdk.effect_player.Effect
import com.banuba.sdk.effect_player.EffectManager
import com.banuba.sdk.manager.BanubaSdkManager
import com.banuba.sdk.manager.BanubaSdkTouchListener
import com.banuba.sdk.scene.FaceMorphing
import com.banuba.sdk.scene.MorphingType
import com.example.arfitlerbeautybanuba.databinding.ActivityCameraPreviewBinding

class CameraPreviewActivity : AppCompatActivity() {

    companion object {

        /*  private const val MASK_NAME = "TrollGrandma"
          private const val MASK_NAME = "PineappleGlasses"
          private const val MASK_NAME = "DebugWireframe"
          private const val MASK_NAME = "Beauty"
          private const val MASK_NAME = "HairGradient_Avocado"*/

        private const val MASK_NAME = "TrollGrandma"

        private const val REQUEST_CODE_APPLY_MASK_PERMISSION = 1001

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

    private val banubaSdkManager by lazy(LazyThreadSafetyMode.NONE) {
        BanubaSdkManager(applicationContext)
    }

    private val maskUri by lazy(LazyThreadSafetyMode.NONE) {
        Uri.parse(BanubaSdkManager.getResourcesBase())
            .buildUpon()
            .appendPath("effects")
            .appendPath(MASK_NAME)
            .build()
    }

    private var shouldApply = false
    private var effect: Effect? = null
    lateinit var binding: ActivityCameraPreviewBinding


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Set custom OnTouchListener to change mask style.
        binding.surfaceView.setOnTouchListener(
            BanubaSdkTouchListener(
                this,
                banubaSdkManager.effectPlayer
            )
        )

        banubaSdkManager.attachSurface(binding.surfaceView)

        binding.showMaskButton.setOnClickListener {
            shouldApply = !shouldApply
            updateUIState()
            if (shouldApply) {
                // The mask is loaded asynchronously and applied

                effect = banubaSdkManager.loadEffect(
                    BanubaSdkManager.getResourcesBase() + "/effects/Makeup",
                    false
                )

                var bacImg: String = BanubaSdkManager.getResourcesBase() + "/effects/watermark.png"

                Log.d("myPath", "${bacImg}")
//                effect?.evalJs("Background.texture('bacImg')", null);
                //effect?.evalJs("Background.transparency(1)", null);
//                effect?.evalJs("Background.blur(1)", null);
                //effect?.evalJs("Background.clear()", null);
//                effect?.evalJs("Hair.color('0.39 0.14 0.14 0.8')", null);
//                effect?.evalJs("Hair.color('0.19 0.06 0.25', '0.09 0.25 0.38')", null);
//                effect?.evalJs("Hair.strands('0.80 0.40 0.40 1.0', '0.83 0.40 0.40 1.0', '0.85 0.75 0.75 1.0', '0.87 0.60 0.60 1.0', '0.99 0.65 0.65 1.0')", null);

                /* effect?.evalJs("Teeth.whitening(1)", null);
                effect?.evalJs("FaceMorph.eyes(0.6)", null);
                effect?.evalJs("FaceMorph.face(0.5)", null);
                effect?.evalJs("FaceMorph.nose(1)", null);
                effect?.evalJs("FaceMorph.lips(1)", null);*/

//                effect?.evalJs("Skin.softening(1)", null);
                //Skin.color("R G B A") - set skin color in R G B A format (separated with space). Each value should be in a rage from 0 to 1 (including decimal),
//                effect?.evalJs("Skin.color('0.8 0.6 0.1 0.4')", null);

                //effect = banubaSdkManager.loadEffect(maskUri.toString(),true)

//                effect?.evalJs("Eyes.color('0 0.2 0.8 0.64')", null);
                //effect?.evalJs("Eyes.flare(1)", null);
//                effect?.evalJs("Eyes.whitening(1)", null);
//                effect?.evalJs("EyeBagsRemoval.enable()", null);

//                effect?.evalJs("Makeup.highlighter('0.75 0.74 0.74 0.4')", null);
//                effect?.evalJs("Makeup.highlighter('0.75 0.74 0.74 0.4')", null);

                // set skin color
//                effect?.evalJs("Skin.color('0.73 0.39 0.08 0.3')", null);
                // set softening strength (skin smoothing)
//                effect?.evalJs("Skin.softening(1)", null);
//                effect?.evalJs("Makeup.blushes('0.7 0.1 0.2 0.5')", null);
//                effect?.evalJs("Brows.color('0.172 0.125 0.105 0.732')", null);
//                effect?.evalJs("Makeup.eyeliner('0 0 0')", null);
//                effect?.evalJs("Makeup.eyeshadow('0.6 0.5 1 0.6')", null);
//                effect?.evalJs("Makeup.lashes('0 0 0')", null);


//                effect?.evalJs("Lips.matt('0.85 0.43 0.5 0.8')", null);
//                effect?.evalJs("Lips.shiny('1 0 0.49 1')", null);
//                effect?.evalJs("Lips.glitter('0.552 0 0 1')", null);
               /* effect?.evalJs("Lips.color('1 0 0 1')", null);
                effect?.evalJs("Lips.brightness(1)", null);
                effect?.evalJs("Lips.saturation(1)", null);
                effect?.evalJs("Lips.shineIntensity(2)", null);
                effect?.evalJs("Lips.shineBleeding(1)", null);
                effect?.evalJs("Lips.shineScale(1)", null);
                effect?.evalJs("Lips.glitterGrain(1)", null);
                effect?.evalJs("Lips.glitterIntensity(1)", null);
                effect?.evalJs("Lips.glitterBleeding(1)", null);*/

                //Combine
                /*effect?.evalJs("Lips.matt('0.85 0.23 0.2 0.8')", null);
                effect?.evalJs("Makeup.eyeshadow('0.6 0.5 1 0.6')", null);
                effect?.evalJs("Eyes.color('0 0.2 0.8 0.64')", null);
                effect?.evalJs("Makeup.contour('0.3 0.1 0.1 0.2')", null);*/

                effect?.evalJs("Background.transparency(1)", null);


            } else {
                // The mask is unloaded
                banubaSdkManager.effectManager.loadAsync("")
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onStart() {
        super.onStart()
        banubaSdkManager.attachSurface(binding.surfaceView)

        if (allPermissionsGranted()) {
            banubaSdkManager.openCamera()
        } else {
            requestPermissions(REQUIRED_PERMISSIONS, REQUEST_CODE_APPLY_MASK_PERMISSION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        results: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, results)
        if (requireAllPermissionsGranted(permissions, results)) {
            banubaSdkManager.openCamera()
        } else {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        banubaSdkManager.effectPlayer.playbackPlay()
    }

    override fun onPause() {
        super.onPause()
        banubaSdkManager.effectPlayer.playbackPause()
    }

    override fun onStop() {
        super.onStop()
        banubaSdkManager.releaseSurface()
        banubaSdkManager.closeCamera()
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun updateUIState() {
        binding.showMaskButton.text = if (shouldApply) {
            getString(R.string.hide_mask)
        } else {
            getString(R.string.show_mask)
        }
    }
}