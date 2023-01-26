package it.cni.programenu

import java.io.Serializable
import java.util.Calendar

class Menu (val creatorUid:String,val nome: String,val data: String,val descrizione: String,val ingredienti:String):
    Serializable {
    constructor() : this("", "", "","","")

}

