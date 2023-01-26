package it.cni.programenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import java.util.ArrayList

class CreaMenu : AppCompatActivity() {

    private lateinit var myDbRef : DatabaseReference

    private lateinit var btn_creamenu : Button
    private lateinit var btn_salvamenu : Button
    private lateinit var btn_menuV : Button
    private lateinit var btn_menuB : Button
    private lateinit var btn_menus : Button

    private lateinit var RecyclerView: RecyclerView
    private lateinit var creaMenuAdapter : CreaMenuAdapter
    private lateinit var menuList : ArrayList<Menu>
    


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crea_menu)
    }
}