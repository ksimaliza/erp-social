/**
 * 
 */
package ec.edu.uce.erp.common.test;

import org.junit.Test;

import ec.edu.uce.erp.common.util.EncriptacionUtil;

/**
 * @author 
 *
 */
public class TestEncriptacion {
	
//	@Test
	public void encriptarClave () {
		System.out.println("encriptarClave");
		String claveSinEncriptar = "inventario";
		String claveEncriptada = EncriptacionUtil.getInstancia().encriptar(claveSinEncriptar);
		System.out.println("claveEncriptada: " + claveEncriptada);
	}
	
	@Test
	public void desencriptarClave () {
		System.out.println("desencriptarClave");
		String claveSinEncriptar = "9UrwXVpZzrWu1vINLB6RAg==";
		String claveDesencriptada = EncriptacionUtil.getInstancia().desencriptar(claveSinEncriptar);
		System.out.println("claveDesencriptada: " + claveDesencriptada);
	}
	

}
