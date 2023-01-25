package it.cni.programenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CreaMenu : AppCompatActivity() {

    private lateinit var btn_creamenu : Button
    private lateinit var btn_menuV : Button
    private lateinit var btn_menuB : Button
    private lateinit var btn_menus : Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crea_menu)
    }
}