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

//function toUpperCase(object) {
//	try {
//		if (object) {
//			var value = object.value;
//			if (value) {
//				object.value = value.toUpperCase();
//			}
//		}
//	} catch (exception) {
//	}
//}
//
//
//function toLowerCase(object) {
//	try {
//		if (object) {
//			var value = object.value;
//			if (value) {
//				object.value = value.toLowerCase();
//			}
//		}
//	} catch (exception) {
//	}
//}
