package com.example.siakadmobile

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.siakadmobile.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var auth: FirebaseAuth? = null
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logout.setOnClickListener(this)
        binding.save.setOnClickListener(this)
        binding.showdata.setOnClickListener(this)

        auth = FirebaseAuth.getInstance()

        // Optional: Display user info
        val user = auth?.currentUser
        user?.let {
            Toast.makeText(this, "Welcome ${it.displayName}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isEmpty(s: String): Boolean {
        return TextUtils.isEmpty(s)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.save -> {
                val getUserID = auth!!.currentUser!!.uid
                val database = FirebaseDatabase.getInstance()
                val getNIM: String = binding.nim.getText().toString()
                val getNama: String = binding.nama.getText().toString()
                val getJurusan: String = binding.jurusan.getText().toString()

                val getReference: DatabaseReference
                getReference = database.reference

                if (isEmpty(getNIM) || isEmpty(getNama) || isEmpty(getJurusan)) {
                    Toast.makeText(this@MainActivity, "Data tidak boleh ada yang kosong",
                        Toast.LENGTH_SHORT).show()
                } else {
                    getReference.child("Admin").child(getUserID).child("Mahasiswa").push()
                        .setValue(data_mahasiswa(getNIM, getNama, getJurusan))
                        .addOnCompleteListener(this) {
                            binding.nim.setText("")
                            binding.nama.setText("")
                            binding.jurusan.setText("")
                            Toast.makeText(this@MainActivity, "Data Tersimpan", Toast.LENGTH_SHORT).show()
                        }
                }
            }

            R.id.logout -> {
                auth?.signOut()

                Toast.makeText(this@MainActivity, "Logout Berhasil", Toast.LENGTH_SHORT).show()

                val intent = Intent(applicationContext, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }

            R.id.showdata -> {
                startActivity(Intent(this@MainActivity, MyListData::class.java))
            }
        }
    }
}