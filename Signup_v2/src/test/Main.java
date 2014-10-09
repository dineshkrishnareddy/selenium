package test;

 
public class Main {
    public static void main(String[] args) {
    	try{
    		Runtime.getRuntime().exec("cmd /c start testng.bat");
    	}catch (Exception e) {
    		System.out.println("Exception");
		}
    }
}