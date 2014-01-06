package pl.edu.agh.iso.common;


public final class GridsUtils {

	public static boolean isStringInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}
}
