package it.cni.programenu

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Adapter
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var btn_creaMe : Button
    private lateinit var btn_listaSp : Button
    private lateinit var mainReclyerView : RecyclerView
    private lateinit var mainAdapter : MainAdapter
    private lateinit var mainList : ArrayList<Menu>
    private lateinit var mAuth: FirebaseAuth
    private lateinit var myDbRef : FirebaseDatabase




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

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.logout){
            mAuth.signOut()
            val intent = Intent(this,CreateAccount::class.java)
            finish()
            startActivity(intent)
            return true
        }
        return true
    }





}