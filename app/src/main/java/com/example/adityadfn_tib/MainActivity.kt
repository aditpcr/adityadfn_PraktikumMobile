package com.example.adityadfn_tib

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.adityadfn_tib.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getStringExtra("username")
        val pass = intent.getStringExtra("password")
        val umur = intent.getIntExtra("umur", 0)

        Log.e("Hasil", "Umur $umur")

        binding.txtUsername.text = user
        binding.txtPassword.text = pass

        binding.snackbtn.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Item dihapus",
                Snackbar.LENGTH_LONG
            )
                .setAction("BATAL") {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                .show()
        }

        binding.alertbtn.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Hapus data")
                .setMessage("Data yang dihapus tidak bisa dikembalikan.")
                .setNegativeButton("Batal", null)
                .setPositiveButton("Hapus") { dialog, _ ->
                    // proses hapus
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }
        binding.backbtn.setOnClickListener {
            finish()
        }
    }
}