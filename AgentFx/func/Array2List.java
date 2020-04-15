package AgentFx.func;
import java.util.*;

public class Array2List{
	public static String[] A2Ls (ArrayList<String> arrays, int size){
		String[] list = arrays.toArray(new String[size]);
		return list;
	}
}