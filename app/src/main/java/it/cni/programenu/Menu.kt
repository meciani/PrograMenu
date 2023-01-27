package it.cni.programenu

import java.io.Serializable
import java.util.Calendar

class Menu (var creatorUid:String,var nome: String,var data: String,var descrizione: String,var ingredienti:String):
    Serializable {
    constructor() : this("", "", "","","")

}

