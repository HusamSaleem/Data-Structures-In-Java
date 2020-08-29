package DataStructures;
class player extends exa{
	player(){}
	
	public void getP() {
		System.out.println("a");
	}
}

class exa {
	exa(){};
	
	public void getP() {
		System.out.println("b");
	}
}
public class Generics {

	public static void main(String[] args) {
		
		System.out.println(nigma(25));
		player p = new player();
		exa e = new exa();
		
		String out;
		
		out = String.format("[%s]\n[%s]\n", p, e);
		System.out.println(out);

		
		int count = 0;
		long n = (long) Math.pow(2,64);
		while (n > 0) {
			//System.out.println("Potato");
			count++;
			n = n >> 1;
			System.out.println(n);
		}
		System.out.println(count);
	}
	
	public static int nigma(int value) {
		if (value <= 0) {
			return 1;
		}
		return value * nigma(value - 1);
	}
	
	public static <T extends Comparable<T>> void name(T t1, T t2) {
		if (t1.compareTo(t2) == 0) {
			System.out.println("Equal objects");
		} else if (t1.compareTo(t2) > 0) {
			System.out.println("Item: " + t1 + " is greater");
		} else {
			System.out.println("Item: " + t2 + " is greater");
		}
	}

}
