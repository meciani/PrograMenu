package it.cni.programenu

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

class ListaSpesa : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var myDbRef: DatabaseReference
    private lateinit var btn_condividi: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var listaSpesaAdapter: ListaSpesaAdapter
    private lateinit var menuList: ArrayList<Menu>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_spesa)

        myDbRef = FirebaseDatabase.getInstance().reference
        mAuth = FirebaseAuth.getInstance()
        val creatorUid = mAuth.currentUser?.uid

        btn_condividi = findViewById(R.id.btn_condividi)
        recyclerView = findViewById(R.id.spesa_recyclerview)
        menuList = ArrayList()
        listaSpesaAdapter = ListaSpesaAdapter(this, menuList)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = listaSpesaAdapter

        val gson = Gson()

        myDbRef.child("Menu").child(creatorUid!!)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    menuList.clear()
                    for (menuSnapshot in snapshot.children) {
                        val menuJson = menuSnapshot.getValue(String::class.java)
                        val menu = gson.fromJson(menuJson, Menu::class.java)
                        menuList.add(menu!!)
                    }
                    listaSpesaAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    //Toast.makeText(this, "Errore" + error, Toast.LENGTH_SHORT).show()
                }

            })

        btn_condividi.setOnClickListener {

            val intent_condividi = Intent(Intent.ACTION_SEND)
            intent_condividi.type = "text/plain"

            val data = StringBuilder()
            for (i in 0 until menuList.size) {
                val menu = menuList[i]
                val menuJ = gson.toJson(menu)
                val menuString = gson.toJson(menuJ)
                val menuStringPulita = menuString.replace("creatorUid", "")
                    .replace("" + creatorUid, "")
                    .replace("{", "")
                    .replace("/", "")

                data.append(menuStringPulita).append("\n")

            }

            intent_condividi.putExtra(Intent.EXTRA_TEXT, data.toString())
            startActivity(Intent.createChooser(intent_condividi, "Condividi con "))

        }


    }


    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.back, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.back) {

            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
            return true
        }
        return true
    }


}