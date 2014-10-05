/**
 * Permitir ingresar solo numeros enteros
 */
function soloEnteros(object, e) {
	
	var tecla = e.keyCode ? e.keyCode : e.which;
	if (tecla==8 || tecla==9 ){
		return true;
	} else {
		var patron =/[0-9]/;
		var te = String.fromCharCode(tecla);
		return patron.test(te);		
	}
}

function soloDecimales(object, event) {
	/*<![CDATA[*/
		//obtenemos la tecla pulsada
		var valueKey=String.fromCharCode(event.which);
		//obtenemos el valor ascii de la tecla pulsada
		var keycode=event.which;
		
		// Si NO pulsamos un numero, un punto, la tecla suprimir
		// la tecla backspace o el simobolo "-" (45), cancelamos la pulsacion
		if(valueKey.search('[0-9|\.]')!=0 && keycode!=8 && keycode!=46 && keycode!=45) {
			// anulamos la pulsacion de la tecla
			return false;
		};
	/*]]>*/
}

function toUpperCase(object) {
	try {
		if (object) {
			var value = object.value;
			if (value) {
				object.value = value.toUpperCase();
			}
		}
	} catch (exception) {
	}
}


function toLowerCase(object) {
	try {
		if (object) {
			var value = object.value;
			if (value) {
				object.value = value.toLowerCase();
			}
		}
	} catch (exception) {
	}
}

function ButtonAyuda_onclick()
{
	window.open("Ayuda.pdf",null,"top=160,left=260,height=800,width=950");
	return;
}	

