package tool;

import java.util.Arrays;
import java.util.Vector;
/**
 * @author SanghyeonJung
 *
 */
public class VectorTool {
	
	// Dictionary
	// void change(Vector vector, int i1, int i2)
	// void remove(Vector vector, int... indexes)
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void change(Vector vector, int i1, int i2) {
		Object obj1 = vector.get(i1);
		Object obj2 = vector.get(i2);
		vector.set(i1, obj2);
		vector.set(i2, obj1);
	}
	
	@SuppressWarnings("rawtypes")
	public static void remove(Vector vector, int... indexes) {
		Arrays.sort(indexes);
		reverseArrayInt(indexes);
		for(int i : indexes) {vector.remove(i);}
	}
	
	private static void reverseArrayInt(int[] array) {
		int temp;
		for (int i = 0; i < array.length / 2; i++) {
			temp = array[i];
			array[i] = array[(array.length - 1) - i];
			array[(array.length - 1) - i] = temp;
		}
	}
}
