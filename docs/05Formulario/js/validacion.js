/*
Javascript es un lenguaje que posee un paradigma
orientado a objetos y a funciones, por tal motivo
presenta una particularidad la cual es

NO TIPADO

no existe int, double, float, String, etc

Dentro de JS, todo es VAR -> variable

De acuerdo al estandar de ES6 se manejan 3 tipos
de variables

VAR
LET  Es una variable de tipo protected
CONST

*/

function validar(formulario){

    if(formulario.nombre.value.length < 5){
        alert("Escriba por lo menos 5 Caracteres para el nombre");
        formulario.nombre.focus();
        return false;
    }

    var checkOK = "QWERTYUIOPASDFGHJKLZXCVBNMÑ"
    +"qwertyuiopasdfghjklñzxcvbnm";

    var checkStr = formulario.nombre.value;

    var allValido = true;

    for(var i = 0; i < checkStr.length; i++){
        var ch = checkStr.charAt(i);
        for(var j = 0; j < checkOK.length; j++){
            if(ch == checkOK.charAt(j))
            break;
        }
        if(j == checkOK.length){
            allValido = false;
            break;
        }
    }

    if(!allValido){
        alert("Escriba unicamente letras en el campo de nombre");
        formulario.nombre.focus();
        return false;
    }


    var checkOK = "0123456789";

    var checkStr = formulario.edad.value;

    var allValido = true;

    for(var i = 0; i < checkStr.length; i++){
        var ch = checkStr.charAt(i);
        for(var j = 0; j < checkOK.length; j++){
            if(ch == checkOK.charAt(j))
            break;
        }
        if(j == checkOK.length){
            allValido = false;
            break;
        }
    }

    if(!allValido){
        alert("Escriba unicamente numeros en el campo de edad");
        formulario.edad.focus();
        return false;
    }

    /*
    Es necesario que busquen como crear expresiones regulares
    para poder validar la entrada de datos
    */ 

    var txt = formulario.email.value;

    //crear mi expresion

    var b = /^[^@\s]+[^@\.\s]+(\.[^@\.\s]+)+$/;

    alert("Email "+ (b.test(txt)?" ":"no")+" valido");

    return b.test;

}