package com.mendozacarolina.pruebasproyecto

data class Usuario (var usuario:String, var nombreCompleto:String, var correo: String, var tipoPlan: String){
    constructor():this("","", "", "")
}
