package it.cni.programenu

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var btn_creaMe : Button
    private lateinit var btn_listaSp : Button
    private lateinit var mainReclyerView : RecyclerView
    private lateinit var mainAdapter : MainAdapter
    private lateinit var menuList : ArrayList<Menu>
    private lateinit var mAuth: FirebaseAuth
    private lateinit var myDbRef : DatabaseReference




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_creaMe = findViewById(R.id.btn_creaM)
        btn_listaSp = findViewById(R.id.btn_listS)
        mainReclyerView = findViewById(R.id.menuReciclerView)
        menuList = ArrayList()
        mainAdapter = MainAdapter(this,menuList)
        mainReclyerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mainReclyerView.adapter = mainAdapter

        mAuth = FirebaseAuth.getInstance()
        val creatorUid = mAuth.currentUser?.uid
        myDbRef = FirebaseDatabase.getInstance().reference

        val gson = Gson()

        myDbRef.child("Menu").child(creatorUid!!)
            .addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                menuList.clear()
                for (menuSnapshot in snapshot.children){
                    val menuJson = menuSnapshot.getValue(String::class.java)
                    val menu = gson.fromJson(menuJson,Menu::class.java)
                        menuList.add(menu!!)
                }
                mainAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

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