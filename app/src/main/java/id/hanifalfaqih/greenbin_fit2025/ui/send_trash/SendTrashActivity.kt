package com.example.gemcangren.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import id.hanifalfaqih.greenbin_fit2025.databinding.ActivitySendTrashBinding
import id.hanifalfaqih.greenbin_fit2025.viewmodel.TransactionViewModel

class SendTrashActivity : AppCompatActivity() {
    private lateinit var previewView: PreviewView
    private lateinit var cameraProvider: ProcessCameraProvider
    private val scanner by lazy { BarcodeScanning.getClient() }
    private var latestScannedValue: String? = null
    private lateinit var viewModel: TransactionViewModel

    private lateinit var binding: ActivitySendTrashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendTrashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        previewView = binding.previewView

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[TransactionViewModel::class.java]

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1001)
        }

        binding.scanQrButton.setOnClickListener {
            val value = latestScannedValue
            if (value == "eTuwWHvPBxedPF29PfMH4kLK6") {
                Toast.makeText(this, "HIT API", Toast.LENGTH_SHORT).show()
            } else if (value != null && value != "eTuwWHvPBxedPF29PfMH4kLK6") {
                Toast.makeText(this, "QR tidak valid", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Belum ada QR terdeteksi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

            val imageAnalysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also {
                    it.setAnalyzer(ContextCompat.getMainExecutor(this)) { imageProxy ->
                        processImageProxy(imageProxy)
                    }
                }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun processImageProxy(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image ?: run {
            imageProxy.close()
            return
        }

        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

        scanner.process(image)
            .addOnSuccessListener { barcodes ->
                for (barcode in barcodes) {
                    barcode.rawValue?.let { value ->
                        latestScannedValue = value
                    }
                }
            }
            .addOnFailureListener {
                Log.e("QRScanner", "Scanning failed", it)
                latestScannedValue = null
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1001 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startCamera()
        } else {
            Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}