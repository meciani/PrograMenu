package it.cni.programenu

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var btn_creaMe : Button
    private lateinit var btn_listaSp : Button





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_creaMe = findViewById(R.id.btn_creaM)
        btn_listaSp = findViewById(R.id.btn_listS)


        btn_creaMe.setOnClickListener {
            val intent = Intent(this,CreaMenu::class.java)
            finish()
            startActivity(intent)
        }

        btn_listaSp.setOnClickListener {
            val intent = Intent(this,ListaSpesa::class.java)
            finish()
            startActivity(intent)
        }



    }
}