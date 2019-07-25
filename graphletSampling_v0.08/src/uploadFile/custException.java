package uploadFile;

public class custException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5171049155769240128L;
	String str1;
	custException(String str2) {
		str1=str2;
	}
	public String getMessage(){ 
		return str1;
	}
}