function validar(formulario)
{
    var checkOK = "0123456789";
    var costoCarro = formulario.costoCarro.value;
    var todoBien = true;
    var cuotaInicial = formulario.cuotaInicial.value;

    for(var i = 0; i < costoCarro.length; i++)
    {
        var ch = costoCarro.charAt(i);
        for(var j = 0; j < checkOK.length; j++)
        {
            if(ch == checkOK.charAt(j))
            break;
        }
        if(j == checkOK.length)
        {
            todoBien = false;
            break;
        }
    }

    if(!todoBien)
    {
        alert("Escriba unicamente numeros en el campo de valor del precio del automóvil");
        formulario.costoCarro.focus();
        return false;
    }

    if(costoCarro < 100000)
    {
        alert("El carro no puede tener un valor menor a $100,000");
        formulario.costoCarro.focus();
        todoBien = false;
    }

    if(costoCarro > 1000000)
    {
        alert("El carro no puede tener un valor mayor a $1,000,000");
        formulario.costoCarro.focus();
        todoBien = false;
    }

    for(var i = 0; i < cuotaInicial.length; i++)
    {
        var ch = cuotaInicial.charAt(i);
        for(var j = 0; j < checkOK.length; j++)
        {
            if(ch == checkOK.charAt(j))
            break;
        }
        if(j == checkOK.length)
        {
            todoBien = false;
            break;
        }
    }

    if(!todoBien)
    {
        alert("Escriba unicamente numeros en el campo de cuota inicial");
        formulario.cuotaInicial.focus();
        return false;
    }

    if(cuotaInicial < costoCarro*0.1)
    {
        alert("El valor mínimo de la cuota inicial debe ser por lo menos un 10% del valor del automóvil");
        formulario.cuotaInicial.focus();
        todoBien = false;
    }

    if(todoBien)
    {
        const lugarMontoTotal = document.querySelector('#montoTotal');
        const montoTotal = document.createElement('label');
        montoTotal.innerHTML = costoCarro;
        lugarMontoTotal.appendChild(montoTotal);

        const lugarMontoPagado = document.querySelector('#montoPagado');
        const montoPagado = document.createElement('label');
        montoPagado.innerHTML = cuotaInicial;
        lugarMontoPagado.appendChild(montoPagado);

        const lugarMontoPendiente = document.querySelector('#montoPendiente');
        const montoPendiente = document.createElement('label');
        montoPendiente.innerHTML = costoCarro-cuotaInicial;
        lugarMontoPendiente.appendChild(montoPendiente);

        const lugarPlazoMeses = document.querySelector('#plazoMeses');
        const plazoMeses = document.createElement('label');
        plazoMeses.innerHTML = formulario.select.value;
        lugarPlazoMeses.appendChild(plazoMeses);

        const lugarInteres = document.querySelector('#tazaInteres');
        const interes = document.createElement('label');
        interes.innerHTML = "3.94%";
        lugarInteres.appendChild(interes);

        const lugarCapitalFuturo = document.querySelector('#capitalFuturo');
        const capitalFuturo = document.createElement('label');

        n=1.0394;
        for(i=0; i < plazoMeses; i++)
        {
            n=n*n;
        }
        capitalFuturo.innerHTML = costoCarro*n;
        lugarCapitalFuturo.appendChild(capitalFuturo);
    }
}