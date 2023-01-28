package it.cni.programenu

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import java.util.*

class CreaMenu : AppCompatActivity() {

    private lateinit var myDbRef: DatabaseReference

    private lateinit var btn_creamenu: Button
    private lateinit var btn_salvamenu: Button
    private lateinit var btn_menuV: Button
    private lateinit var btn_menuB: Button
    private lateinit var btn_menuS: Button
    private lateinit var btn_svuota: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var creaMenuAdapter: CreaMenuAdapter
    private lateinit var menuList: ArrayList<Menu>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crea_menu)

        myDbRef = FirebaseDatabase.getInstance().reference
        val creatorUid = FirebaseAuth.getInstance().currentUser?.uid

        btn_creamenu = findViewById(R.id.btn_mcrea)
        btn_salvamenu = findViewById(R.id.btn_msalva)
        btn_menuV = findViewById(R.id.btn_mv)
        btn_menuB = findViewById(R.id.btn_mb)
        btn_menuS = findViewById(R.id.btn_ms)
        btn_svuota = findViewById(R.id.btn_msvuota)

        recyclerView = findViewById(R.id.crearecyclerView)
        menuList = ArrayList()
        creaMenuAdapter = CreaMenuAdapter(this, menuList)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = creaMenuAdapter

        val dataCorrente = Calendar.getInstance()
        val dataFutura = Calendar.getInstance()
        dataFutura.add(Calendar.DATE, 7)

        val dataCorrenteString = dataCorrente.time.toString()
        val dataFuturaString = dataFutura.time.toString()
        val gson = Gson()

        btn_creamenu.setOnClickListener {
            val intent = Intent(this, PersonalizzaMenu::class.java)
            finish()
            startActivity(intent)
        }

        btn_svuota.setOnClickListener {

            menuList.clear()
            creaMenuAdapter.notifyDataSetChanged()

        }

        btn_salvamenu.setOnClickListener {
            if (!menuList.isEmpty()) {

                for (i in 0 until menuList.size) {
                    val menu = menuList[i]
                    //val menuID = myDbRef.push().key
                    val menuJ = gson.toJson(menu)
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
            } else {
                Toast.makeText(this, "Lista vuota Aggiungere menu", Toast.LENGTH_SHORT).show()
            }
        }

        btn_menuV.setOnClickListener {
            var veloce = Menu(
                creatorUid.toString(),
                "Menu Veloce",
                "dal " + dataCorrenteString + " al " + dataFuturaString,
                "Giorno 1:\n" +
                        "Colazione: avena con latte di mandorle, frutta fresca e noci\n" +
                        "Pranzo: insalata di pollo alla griglia con verdure miste\n" +
                        "Cena: zuppa di verdure con fagioli neri\n" +
                        "\n" +
                        "Giorno 2:\n" +
                        "Colazione: frittata di spinaci con pane integrale tostato\n" +
                        "Pranzo: insalata di tonno con verdure miste\n" +
                        "Cena: riso integrale con verdure e pollo alla griglia\n" +
                        "\n" +
                        "Giorno 3:\n" +
                        "Colazione: yogurt con muesli e frutta fresca\n" +
                        "Pranzo: zuppa di pomodoro con pane integrale\n" +
                        "Cena: pollo alla griglia con verdure al vapore\n" +
                        "\n" +
                        "Giorno 4:\n" +
                        "Colazione: uova strapazzate con pane integrale tostato\n" +
                        "Pranzo: insalata di quinoa con verdure miste e pollo alla griglia\n" +
                        "Cena: zuppa di verdure con fagioli neri\n" +
                        "\n" +
                        "Giorno 5:\n" +
                        "Colazione: avena con latte di mandorle, frutta fresca e noci\n" +
                        "Pranzo: insalata di pollo alla griglia con verdure miste\n" +
                        "Cena: riso integrale con verdure e pollo alla griglia\n" +
                        "\n" +
                        "Giorno 6:\n" +
                        "Colazione: frittata di spinaci con pane integrale tostato\n" +
                        "Pranzo: insalata di tonno con verdure miste\n" +
                        "Cena: zuppa di verdure con fagioli neri\n" +
                        "\n" +
                        "Giorno 7:\n" +
                        "Colazione: yogurt con muesli e frutta fresca\n" +
                        "Pranzo: zuppa di pomodoro con pane integrale\n" +
                        "Cena: pollo alla griglia con verdure al vapore",
                "Avena,latte,Pollo,Verdure,Pane Integrale,Tonno,Fagioli,yogurt,muslei,frutta pasta"
            )

            menuList.add(veloce)
            creaMenuAdapter.notifyDataSetChanged()


        }

        btn_menuB.setOnClickListener {
            val base = Menu(
                creatorUid.toString(),
                "Menu Base",
                "dal " + dataCorrenteString + " al " + dataFuturaString,
                "Giorno 1:\n" +
                        "Colazione: frittata di verdure e avocado\n" +
                        "Pranzo: insalata di quinoa con pollo alla griglia e verdure\n" +
                        "Cena: pesce al forno con patate e verdure\n" +
                        "\n" +
                        "Giorno 2:\n" +
                        "Colazione: yogurt greco con muesli e frutti di bosco\n" +
                        "Pranzo: zuppa di verdure con crostini di pane integrale\n" +
                        "Cena: riso integrale con pollo e verdure\n" +
                        "\n" +
                        "Giorno 3:\n" +
                        "Colazione: avena con latte di mandorle e frutti di bosco\n" +
                        "Pranzo: insalata di tonno con ceci e verdure\n" +
                        "Cena: spaghetti di zucchine con pomodorini e basilico\n" +
                        "\n" +
                        "Giorno 4:\n" +
                        "Colazione: uova strapazzate con spinaci e pomodorini\n" +
                        "Pranzo: zuppa di pomodoro con crostini di pane integrale\n" +
                        "Cena: polpette di manzo con purè di patate e verdure\n" +
                        "\n" +
                        "Giorno 5:\n" +
                        "Colazione: smoothie con frutta e verdura\n" +
                        "Pranzo: insalata di pollo con verdure e avocado\n" +
                        "Cena: pesce alla griglia con riso integrale e verdure\n" +
                        "\n" +
                        "Giorno 6:\n" +
                        "Colazione: pane tostato integrale con formaggio fresco e pomodorini\n" +
                        "Pranzo: zuppa di verdure con crostini di pane integrale\n" +
                        "Cena: riso integrale con pollo e verdure\n" +
                        "\n" +
                        "Giorno 7:\n" +
                        "Colazione: frittata di verdure e avocado\n" +
                        "Pranzo: insalata di quinoa con pollo alla griglia e verdure\n" +
                        "Cena: pesce al forno con patate e verdure",
                "Avena,latte,Pollo,Verdure,Pane Integrale,Tonno,Fagioli,yogurt\n" + "" +
                        "muslei,frutta pasta,pesce"
            )

            menuList.add(base)
            creaMenuAdapter.notifyDataSetChanged()

        }

        btn_menuS.setOnClickListener {
            val sofisticato = Menu(
                creatorUid.toString(),
                "Menu Sofisticato",
                "dal " + dataCorrenteString + " al " + dataFuturaString,
                "Giorno 1:\n" +
                        "Colazione: Uova Benedict con salmone affumicato e spinaci\n" +
                        "Pranzo: Riso venere con gamberetti e zucchine\n" +
                        "Cena: Filetto di manzo con patate al rosmarino e verdure di stagione\n" +
                        "\n" +
                        "Giorno 2:\n" +
                        "Colazione: Crepes con formaggio fresco e frutti di bosco\n" +
                        "Pranzo: Zuppa di cipolle con crostini al formaggio\n" +
                        "Cena: Risotto al limone con pollo e asparagi\n" +
                        "\n" +
                        "Giorno 3:\n" +
                        "Colazione: Focaccia di cipolla con uova e pancetta\n" +
                        "Pranzo: Insalata di quinoa con pesce spada e avocado\n" +
                        "Cena: Sformato di melanzane con mozzarella e pomodorini\n" +
                        "\n" +
                        "Giorno 4:\n" +
                        "Colazione: Crostata di frutta fresca con crema pasticcera\n" +
                        "Pranzo: Zuppa di pomodoro con crostini di pane nero\n" +
                        "Cena: Riso integrale con gamberi e verdure\n" +
                        "\n" +
                        "Giorno 5:\n" +
                        "Colazione: Omelette con funghi e formaggio\n" +
                        "Pranzo: Insalata di spinaci con salmone affumicato e noci\n" +
                        "Cena: Arrosto di vitello con patate al rosmarino e verdure di stagione\n" +
                        "\n" +
                        "Giorno 6:\n" +
                        "Colazione: Pane tostato integrale con burrata e pomodorini\n" +
                        "Pranzo: Zuppa di verdure con crostini di pane integrale\n" +
                        "Cena: Riso integrale con pollo e verdure\n" +
                        "\n" +
                        "Giorno 7:\n" +
                        "Colazione: Frittata di verdure e avocado\n" +
                        "Pranzo: Insalata di quinoa con pollo alla griglia e verdure\n" +
                        "Cena: Pesce al forno con patate e verdure",
                "Avena,latte,Pollo,Verdure,Pane Integrale,Tonno,Fagioli,yogurt\n" + "" +
                        "muslei,frutta pasta,pesce"
            )

            menuList.add(sofisticato)
            creaMenuAdapter.notifyDataSetChanged()

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