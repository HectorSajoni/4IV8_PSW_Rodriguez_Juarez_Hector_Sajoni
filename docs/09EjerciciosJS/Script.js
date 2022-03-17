function ejercicio1()
{
    var capital = document.problema1.input1.value;
    resultado = parseFloat(capital);
    var resultado = resultado + resultado*0.02;
    document.problema1.output1.value = resultado;
}
function ejercicio2()
{
    var sueldo = document.problema2.input1.value;
    resultado = parseFloat(sueldo);
    var resultado = resultado + resultado*0.3;
    document.problema2.output1.value = resultado;
}
function ejercicio3()
{
    var compra = document.problema3.input1.value;
    resultado = parseFloat(compra);
    var resultado = resultado - resultado*0.15;
    document.problema3.output1.value = resultado;
}
function ejercicio4()
{
    var parcial = parseFloat(document.problema4.input1.value);
    var examen = parseFloat(document.problema4.input2.value);
    var trabajo = parseFloat(document.problema4.input3.value);
    var resultado = parcial*0.55 + examen*0.30 + trabajo*.15;
    document.problema4.output1.value = resultado;
}
function ejercicio5()
{
    var hombres = parseFloat(document.problema5.input1.value);
    var mujeres = parseFloat(document.problema5.input2.value);
    var total = hombres + mujeres;
    var promhombres = (hombres/total)*100;
    var prommujeres = (mujeres/total)*100;
    document.problema5.output1.value = promhombres;
    document.problema5.output2.value = prommujeres;
}
function ejercicio6()
{
    const fechaActual = new Date();
    var añoActual = fechaActual.getFullYear();
    var mesActual = fechaActual.getMonth() + 1;
    var diaActual = fechaActual.getDate();

    var fechaNacimiento = document.problema6.fecha.value;
    var fechaDiv = fechaNacimiento.split('-');

    var añoNacimiento = fechaDiv[0];
    var mesNacimiento = fechaDiv[1];
    var diaNacimiento = fechaDiv[2];
    if(mesActual >= mesNacimiento)
    {
        if(diaActual >= diaNacimiento)
        {
           document.problema6.output1.value = añoActual - añoNacimiento; 
        }
    }
    else
    {
        document.problema6.output1.value = añoActual - añoNacimiento - 1 ;  
    }

    if(diaActual >= diaNacimiento)
    {
        if(mesActual >= mesNacimiento)
        {
            document.problema6.output2.value = mesActual - mesNacimiento;
        }
        else
        {
            document.problema6.output2.value = 12 - (mesNacimiento - mesActual); 
        }
        document.problema6.output3.value = diaActual - diaNacimiento;
    }
    else
    {
        if(mesActual >= mesNacimiento)
        {
            document.problema6.output2.value = mesActual - mesNacimiento - 1;
        }
        else
        {
            document.problema6.output2.value = 11 - (mesNacimiento - mesActual); 
        }

        if(mesNacimiento = 1 || 2 || 4 || 6 || 8 || 9 || 11)
        {
            document.problema6.output3.value = 31 - diaNacimiento + diaActual;
        }
        else if(mesNacimiento = 5 || 7 || 10 || 12)
        {
            document.problema6.output3.value = 30 - diaNacimiento + diaActual;
        }
        else if(mesNacimiento = 3)
        {
            document.problema6.output3.value = 28 - diaNacimiento + diaActual;
        }  
    }
}