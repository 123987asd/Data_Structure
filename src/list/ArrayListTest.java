package list;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {

	public static void main(String[] args) {
		List<String> list=new ArrayList<>();
		list.add(0, "aa");
		System.out.println(list.get(0));
		
	}
}
