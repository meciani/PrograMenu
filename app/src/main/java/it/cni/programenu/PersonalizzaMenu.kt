package it.cni.programenu


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.Calendar

class PersonalizzaMenu : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var myDbRef: DatabaseReference

    private lateinit var btn_salva: Button
    private lateinit var etxt_nome: EditText
    private lateinit var etxt_data: EditText
    private lateinit var etxt_descrizione: EditText
    private lateinit var etxt_ingredienti: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personalizza_menu)

        mAuth = FirebaseAuth.getInstance()
        val creatorUid = mAuth.currentUser?.uid.toString()
        myDbRef = FirebaseDatabase.getInstance().reference

        etxt_nome = findViewById(R.id.etxt_pm_nome)
        etxt_data = findViewById(R.id.etxt_pm_data)
        etxt_descrizione = findViewById(R.id.etxt_pm_menu)
        etxt_ingredienti = findViewById(R.id.etxt_pm_ingredienti)
        btn_salva = findViewById(R.id.btn_salva_pm)


        val gson = Gson()

        btn_salva.setOnClickListener {

            val dataFo = SimpleDateFormat("dd/MM/yyyy")
            val datam = dataFo.parse(etxt_data.text.toString())
            val calendar = Calendar.getInstance()
            calendar.time = datam
            calendar.add(Calendar.DATE, 7)
            val dataFutura = calendar.time.toString()

            val nome = etxt_nome.text.toString()
            //val data = etxt_data.text.toString()
            val descrizione = etxt_descrizione.text.toString()
            val ingredienti = etxt_ingredienti.text.toString()
            val menuObject =
                Menu(creatorUid, nome, "da " + datam + "al " + dataFutura, descrizione, ingredienti)
            val menuJ = gson.toJson(menuObject)
            myDbRef.child("Menu").child(creatorUid!!).push().setValue(menuJ) { error, _ ->
                if (error == null) {
                    val intent = Intent(this, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Errore" + error, Toast.LENGTH_SHORT).show()
                }
            }


        }


    }


    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.back, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.back) {

            val intent = Intent(this, CreaMenu::class.java)
            finish()
            startActivity(intent)
            return true
        }
        return true
    }


}