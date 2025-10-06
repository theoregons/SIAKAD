package com.example.siakadmobile

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.siakadmobile.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

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
                // Logic untuk save data
            }

            R.id.logout -> {
                // Sign out from Firebase
                auth?.signOut()

                Toast.makeText(this@MainActivity, "Logout Berhasil", Toast.LENGTH_SHORT).show()

                // Navigate back to login
                val intent = Intent(applicationContext, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }

            R.id.showdata -> {
                // Logic untuk show data
            }
        }
    }
}