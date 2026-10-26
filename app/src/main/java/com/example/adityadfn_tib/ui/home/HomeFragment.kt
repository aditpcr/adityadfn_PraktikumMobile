
package com.example.adityadfn_tib.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.adityadfn_tib.Home.Pertemuan9.NinthActivity
import com.example.adityadfn_tib.LoginActivity
import com.example.adityadfn_tib.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity)
            .setSupportActionBar(binding.toolbar)

        (requireActivity() as AppCompatActivity)
            .supportActionBar?.title = "Home"

        binding.snackbtn.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Item dihapus",
                Snackbar.LENGTH_LONG
            ).setAction("BATAL") {
                startActivity(
                    Intent(requireContext(), LoginActivity::class.java)
                )
            }.show()
        }

        // Alert Dialog
        binding.alertbtn.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Hapus data")
                .setMessage("Data yang dihapus tidak bisa dikembalikan.")
                .setNegativeButton("Batal", null)
                .setPositiveButton("Hapus") { dialog, _ ->
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }


        binding.pertemuan5btn.setOnClickListener {
            startActivity(
                Intent(requireContext(), LimaActivity::class.java)
            )
        }

        binding.pertemuan6btn.setOnClickListener {
            startActivity(
                Intent(requireContext(), SixthActivity::class.java)
            )
        }

        binding.btnToNinth.setOnClickListener {
            startActivity(
                Intent(requireContext(), NinthActivity::class.java)
            )
        }

        binding.logoutbtn.setOnClickListener {
            val sharedPref = requireContext().getSharedPreferences(
                "user_pref",
                Context.MODE_PRIVATE
            )

            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    sharedPref.edit().clear().apply()
                    dialog.dismiss()

                    val intent = Intent(
                        requireContext(),
                        LoginActivity::class.java
                    )
                    startActivity(intent)
                    requireActivity().finish()
                }
                .setNegativeButton("Batal", null)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
