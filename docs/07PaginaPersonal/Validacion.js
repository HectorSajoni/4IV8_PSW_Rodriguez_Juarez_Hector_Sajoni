function validar(formulario)
{
    if(formulario.nombre.value.length < 3)
    {
        alert("Escriba por lo menos 3 Caracteres para el nombre");
        formulario.nombre.focus();
        return false;
    }
    var checkOK = "QWERTYUIOPASDFGHJKLZXCVBNMÑ- "
    +"qwertyuiopasdfghjklñzxcvbnméáíúó";
    var checkStr = formulario.nombre.value;
    var allValido = true;
    for(var i = 0; i < checkStr.length; i++)
    {
        var ch = checkStr.charAt(i);
        for(var j = 0; j < checkOK.length; j++)
        {
            if(ch == checkOK.charAt(j))
            break;
        }
        if(j == checkOK.length){
            allValido = false;
            break;
        }
    }
    if(!allValido)
    {
        alert("Escriba unicamente letras en el campo de nombre");
        formulario.nombre.focus();
        return false;
    }
    var checkOK = "0123456789";
    var checkStr = formulario.telefono.value;
    var allValido = true;
    for(var i = 0; i < checkStr.length; i++)
    {
        var ch = checkStr.charAt(i);
        for(var j = 0; j < checkOK.length; j++)
        {
            if(ch == checkOK.charAt(j))
            break;
        }
        if(j == checkOK.length){
            allValido = false;
            break;
        }
    }
    if(!allValido)
    {
        alert("Escriba unicamente numeros en el campo de telefono");
        formulario.telefono.focus();
        return false;
    }
    if(formulario.telefono.value.length < 10)
    {
        alert("El número de teléfono debe tener por lo menos 10 dígitos");
        formulario.nombre.focus();
        return false;
    }
    var txt = formulario.email.value;
    /*var b = /^[^@\s]+[^@\.\s]+(\.[^@\.\s]+)+$/;       /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3,4})+$/
    alert("Email "+ (b.test(txt)?" ":"no")+" valido");
    return b.test;*/
    if (/^([\da-z_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/.test(formulario.email.value))
    {
        alert("perfecto! la dirección de email " + formulario.email.value + " es correcta y ha sido registrada.");
    } else 
    {
        alert("La dirección de email es incorrecta.");
    }
}   