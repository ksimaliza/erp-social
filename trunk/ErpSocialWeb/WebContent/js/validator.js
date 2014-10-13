function isNumberKey(evt) {
	
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	//37  39
	
	if (charCode > 31 && (charCode < 48 || charCode > 57))
	{
		if(charCode==37||charCode==39)
			return true;
		return false;
	}	
	return true;
}

function isDecimalKey(evt) {
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    //37  39
    if (charCode > 31 && (charCode < 48 || charCode > 57))
    {
            if(charCode==37||charCode==39||charCode==46||charCode==44)
                    return true;
            return false;
    }
    return true;
}

function isSpace(e)
{
	t=(document.all)?e.keyCode:e.which;
	return (t!=32);	
}

function isCedula(valor) {
	cedula = valor.value;
	array =cedula.split('');
	var num=array.length;
	if(num==10)
	{
		total=0;
		digito=(array[9]*1);
		var i;
		for(i=0;i<(num-1);i++)
		{
			if((i%2)!=0)
			{
				total=total+(array[i]*1);
			}
			else
			{
				mult=array[i]*2;
				if(mult>9)
					total=total+(mult-9);
				else
					total=total+mult;
			}
		}
		decena=total/10;
		decena=Math.floor(decena);
		decena=(decena+1)*10;
		final1=(decena-total);
		if((final1==10 && digito==0)||(final1==digito))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	else
	{
		alert('El número de la cédula debe tener 10 digitos');
		return false;
	}
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