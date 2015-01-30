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

function NumCheck(e, field) {
    key = e.keyCode ? e.keyCode : e.which;
    if (key == 8) return true;
    if (key > 47 && key < 58) {
      if (field.value == "") return true;
      regexp = /.[0-9]{2}$/;
      return !(regexp.test(field.value));
    }
    if (key == 46) {
      if (field.value == "") return false;
      regexp = /^[0-9]+$/;
      return regexp.test(field.value);
    }
    return false;
  }

PrimeFaces.locales['es'] = {
	    closeText: 'Cerrar',
	    prevText: 'Anterior',
	    nextText: 'Siguiente',
	    monthNames: ['Enero','Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
	    monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun','Jul','Ago','Sep','Oct','Nov','Dic'],
	    dayNames: ['Domingo','Lunes','Martes','Miércoles','Jueves','Viernes','Sábado'],
	    dayNamesShort: ['Dom','Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
	    dayNamesMin: ['D','L','M','X','J','V','S'],
	    weekHeader: 'Semana',
	    firstDay: 1,
	    isRTL: false,
	    showMonthAfterYear: false,
	    yearSuffix: '',
	    timeOnlyTitle: 'Sólo hora',
	    timeText: 'Tiempo',
	    hourText: 'Hora',
	    minuteText: 'Minuto',
	    secondText: 'Segundo',
	    currentText: 'Fecha actual',
	    ampm: false,
	    month: 'Mes',
	    week: 'Semana',
	    day: 'Día',
	    allDayText : 'Todo el día'
	};

