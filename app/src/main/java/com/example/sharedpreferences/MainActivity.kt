package com.example.sharedpreferences

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences:SharedPreferences
    private val sharedPrefName = "sharedPrefDepp"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences(sharedPrefName, MODE_PRIVATE)

        binding.btnSave.setOnClickListener {
            val id = Integer.parseInt(binding.etInputId.text.toString())
            val name = binding.etInputName.text.toString()
            val editor = sharedPreferences.edit()
            editor.putInt("id_key", id)
            editor.putString("name_key", name)
            editor.apply()
            Toast.makeText(this, "data saved", Toast.LENGTH_SHORT).show()
        }

        binding.btnView.setOnClickListener {
            val id = sharedPreferences.getInt("id_key", 0)
            val name = sharedPreferences.getString("name_key", "default name")
            binding.tvShowId.text = id.toString()
            binding.tvShowName.text = name
            if (id == 0 && name.equals("default name")){
                Toast.makeText(this, "data kosong", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "data ditampilkan", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnClear.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            binding.tvShowId.text = ""
            binding.tvShowName.text = ""
            Toast.makeText(this, "data dihapus", Toast.LENGTH_SHORT).show()
        }
    }
}