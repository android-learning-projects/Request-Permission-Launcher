package com.persmission

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.persmission.databinding.ActivityMainBinding

/**
 * Test commit
 */
class MainActivity : AppCompatActivity() {

    val TAG = javaClass.name

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonSingle.setOnClickListener {
            requestSinglePermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
        }
        binding.buttonMultiple.setOnClickListener {
            requestMultiplePermissionLauncher.launch(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
            )
        }

    }


    // Register the permissions callback, which handles the user's response to the
// system permissions dialog. Save the return value, an instance of
// ActivityResultLauncher. You can use either a val, as shown in this snippet,
// or a lateinit var in your onAttach() or onCreate() method.
    private val requestMultiplePermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            Log.d(TAG, ": $result")
            binding.textViewMultiple.text =
                "READ_EXTERNAL_STORAGE: ${result[Manifest.permission.READ_EXTERNAL_STORAGE]} & CAMERA: ${result[Manifest.permission.CAMERA]}"
            Log.d(
                TAG,
                ": CAMERA: ${result[Manifest.permission.CAMERA]}, STORAGE: ${result[Manifest.permission.READ_EXTERNAL_STORAGE]}"
            )
        }

    // Register the permissions callback, which handles the user's response to the
    // system permissions dialog. Save the return value, an instance of
    // ActivityResultLauncher. You can use either a val, as shown in this snippet,
    // or a lateinit var in your onAttach() or onCreate() method.
    private val requestSinglePermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            Log.d(TAG, "READ_CONTACTS: $isGranted")
            binding.textViewSingle.text = "READ_CONTACTS: $isGranted"
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
            } else {
                // Explain to the user that the feature is unavailable because the
                // features requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
            }
        }
}