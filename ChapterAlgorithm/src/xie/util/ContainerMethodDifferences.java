package xie.util;
import static xie.util.Print.*;
import java.util.*;
import java.lang.reflect.*;
public class ContainerMethodDifferences {
	static Set<String> methodSet(Class<?> type){
		Set<String> result = new TreeSet<String>();
		for(Method m : type.getMethods()){
			result.add(m.getName());
		}
		return result;
	}
	static void interfaces(Class<?> type){
		println("Interfaces in " + type.getName() + ":");
		List<String> result = new ArrayList<String>();
		for(Class<?> c : type.getInterfaces()){
			result.add(c.getSimpleName());
		}
		println(result);
	}
	static Set<String> object = methodSet(Object.class);
	static {
		object.add("clone");
	}
	static void difference(Class<?> superset , Class<?> subset){
		println(superset.getSimpleName() + " extends " + subset.getSimpleName() + " , adds: ");
		Set<String> comp = Sets.difference(methodSet(superset), methodSet(subset));
		comp.removeAll(object);	//不显示Object的方法
		println(comp);
		interfaces(superset);
	}
	public static void main(String[] args) {
		println("Collection: " + methodSet(Collection.class));
		interfaces(Collection.class);
		difference(Set.class,Collection.class);
		difference(HashSet.class, Set.class);
		difference(LinkedList.class, HashSet.class);
		difference(TreeSet.class, Set.class);
		difference(List.class, Collection.class);
		difference(ArrayList.class, List.class);
		difference(LinkedList.class, List.class);
		difference(Queue.class, Collection.class);
		difference(PriorityQueue.class, Queue.class);
		println("Map:" + methodSet(Map.class));
		difference(HashMap.class, Map.class);
		difference(LinkedHashMap.class, HashMap.class);
		difference(SortedMap.class, Map.class);
		difference(TreeMap.class, Map.class);
	}
}
