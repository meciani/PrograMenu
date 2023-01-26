package it.cni.programenu

import java.io.Serializable

class Utente (val id: String, val name: String, val email: String) : Serializable {
    constructor() : this("", "", "")
}