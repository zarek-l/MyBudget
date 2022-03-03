package com.mendozacarolina.pruebasproyecto

data class Usuario (var usuario:String, var nombre:String, var correo: String, var tipoPlan: String){
    constructor():this("","", "", "")
}
