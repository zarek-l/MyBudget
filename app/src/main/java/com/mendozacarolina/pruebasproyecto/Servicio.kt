package com.mendozacarolina.pruebasproyecto

data class Servicio (var imagenServicio:String, var nombreServicio:String, var fechaSuscripcion:String, var montoSuscripcion:Int){
    constructor():this("", "", "", 0)
}
