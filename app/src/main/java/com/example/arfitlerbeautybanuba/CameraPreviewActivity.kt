package com.example.arfitlerbeautybanuba

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.banuba.sdk.effect_player.Effect
import com.banuba.sdk.manager.BanubaSdkManager
import com.banuba.sdk.manager.BanubaSdkTouchListener
import com.example.arfitlerbeautybanuba.databinding.ActivityCameraPreviewBinding

class CameraPreviewActivity : AppCompatActivity() {

    companion object {

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
        binding.surfaceView.setOnTouchListener(BanubaSdkTouchListener(this, banubaSdkManager.effectPlayer))

        binding.showMaskButton.setOnClickListener {
            shouldApply = !shouldApply
            updateUIState()
            if (shouldApply) {
                // The mask is loaded asynchronously and applied
                effect = banubaSdkManager.effectManager.loadAsync(maskUri.toString())
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