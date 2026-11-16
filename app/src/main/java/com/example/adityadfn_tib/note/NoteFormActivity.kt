
package com.example.adityadfn_tib.note

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.activity.enableEdgeToEdge
import kotlinx.coroutines.launch
import com.example.adityadfn_tib.data.AppDatabase
import com.example.adityadfn_tib.data.entity.NoteEntity
import com.example.adityadfn_tib.databinding.ActivityNoteFormBinding

class NoteFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(androidx.core.view.WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = AppDatabase.getInstance(this)

        binding.btnSaveNote.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val content = binding.etContent.text.toString().trim()

            if (title.isBlank() || content.isBlank()) {
                Toast.makeText(
                    this,
                    "Isi semua kolom!",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            binding.btnSaveNote.isEnabled = false

            lifecycleScope.launch {
                try {
                    kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
                        db.noteDao().insert(
                            NoteEntity(title = title, content = content)
                        )
                    }

                    finish()

                } catch (e: Exception) {
                    binding.btnSaveNote.isEnabled = true

                    Toast.makeText(
                        this@NoteFormActivity,
                        "Gagal menyimpan catatan",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
