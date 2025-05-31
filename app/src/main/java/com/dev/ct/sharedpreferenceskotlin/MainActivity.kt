package com.dev.ct.sharedpreferenceskotlin

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dev.ct.sharedpreferenceskotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // SharedPreferences nesnesi tanımlanıyor
    private  lateinit var  sharedPreferences : SharedPreferences
    // SharedPreferences'tan gelen yaş bilgisi burada tutulacak
    var ageFromPref : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // SharedPreferences nesnesi başlatılıyor
        sharedPreferences = this.getSharedPreferences("com.dev.ct.sharedpreferenceskotlin", Context.MODE_PRIVATE)

        // Daha önce kaydedilen yaş bilgisi alınıyor, yoksa -1 dönüyor
        ageFromPref = sharedPreferences.getInt("age",-1)
        // Eğer kayıtlı yaş yoksa boş göster, varsa yaş bilgisini göster
        if (ageFromPref == -1){
            binding.textView.text ="Your Age:"
        }
        else{
            binding.textView.text = "Your Age: ${ageFromPref}"
        }
    }
    // Save butonuna tıklandığında çağrılır
    fun save(view: View){
        // EditText'ten gelen yaş bilgisi alınır ve Int'e dönüştürülür
        val myAge = binding.editTextText.text.toString().toIntOrNull()
        if (myAge != null){
            // TextView'e yaş bilgisi yazdırılır
            binding.textView.text ="Your Age: ${myAge}"
            // Yaş bilgisi SharedPreferences ile kalıcı olarak saklanır
            sharedPreferences.edit().putInt("age",myAge).apply()
        }
    }

    // Delete butonuna tıklandığında çağrılır
    fun delete(view: View){
        // SharedPreferences'tan mevcut yaş bilgisi kontrol edilir
        ageFromPref = sharedPreferences.getInt("age",-1)
        // Eğer yaş bilgisi varsa silinir ve ekrandan kaldırılır
        if (ageFromPref != -1){
            sharedPreferences.edit().remove("age").apply()
            binding.textView.text = "Your Age:"
        }

    }


}