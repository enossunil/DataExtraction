package test;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String ar[]) {
		String constituencyNameCode = "Bhiwani-Mahendragarh-8";
		String constSplitArr[] = constituencyNameCode.split("-");
		
		System.out.println(constituencyNameCode.split("-[0-9]$")[0]);
		System.out.println(Integer.parseInt(constSplitArr[constSplitArr.length-1]));
	}
}
