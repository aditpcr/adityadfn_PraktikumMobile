package com.example.adityadfn_tib

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.adityadfn_tib.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.example.adityadfn_tib.Pertemuan5.LimaActivity
import com.example.adityadfn_tib.Pertemuan6.SixthActivity

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
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }

        binding.backbtn.setOnClickListener {
            finish()
        }

        binding.pertemuan5btn.setOnClickListener {
            val intent = Intent(this@MainActivity, LimaActivity::class.java)
            startActivity(intent)
        }
        binding.pertemuan6btn.setOnClickListener {
            val intent = Intent(this@MainActivity, SixthActivity::class.java)
            startActivity(intent)
        }
        binding.logoutbtn.setOnClickListener {
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()

                    val intent = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(intent)
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }
}