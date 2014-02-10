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
