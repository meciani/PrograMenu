package it.cni.programenu

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CreaMenuAdapter (val context : Context, val menuList: ArrayList<Menu>):
    RecyclerView.Adapter<CreaMenuAdapter.MenuViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.layout_menu,parent,false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        var currentMenu = menuList[position]
        holder.txtTitolo.text = currentMenu.nome
        holder.txtData.text = currentMenu.data.toString()
        holder.txtDescrizone.text = currentMenu.descrizione

        holder.itemView.setOnClickListener {

            val intent = Intent(context,VisualizzaMenu::class.java)

            intent.putExtra("titolo",currentMenu.nome)
            intent.putExtra("data",currentMenu.data)
            //intent.putExtra("uid",currentMenu.creatorId)
            intent.putExtra("piatto",currentMenu.descrizione)


            context.startActivity(intent)
        }

    }


    override fun getItemCount(): Int {
        return menuList.size
    }

    class MenuViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val txtTitolo = itemView.findViewById<TextView>(R.id.menu_name)
        val txtData = itemView.findViewById<TextView>(R.id.menu_date)
        val txtDescrizone = itemView.findViewById<TextView>(R.id.menu_description)


    }




}