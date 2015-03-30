package es.upm.dit.isst.factGest;

import java.security.MessageDigest;

public class Seguridad {

	public static String hashPass(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(password.getBytes());

			byte bytes[] = md.digest();

			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < bytes.length; i++) {
				String hex = Integer.toHexString(0xff & bytes[i]);
				if (hex.length() == 1){
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
