package global;

public class testSound {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
		//Global.getSound("theme.ogg");
		Global.getSound("BugleErsteAnruf.wav");
		}
		
		catch(Exception e){
			System.err.println(e);
		}
	}
}
