import java.util.*;
class SortMapUsingValue 
{
    public static <K,V extends Comparable<? super V>>  Map<K,V> sortByValue(Map<K,V> inMap){
      //Map.Entry<K,V> means single entry but we get Set of entrys
	  List< Map.Entry<K, V>> entrys= new LinkedList<>(inMap.entrySet());
	  Collections.sort(entrys,new Comparator<Map.Entry<K,V>>(){
	   @Override
	   public int compare(Map.Entry<K,V> o1,Map.Entry<K,V> o2){
	    return (o1.getValue().compareTo(o2.getValue()));
	   }
	  });
	  inMap.clear();
	 // HashMap<K,V> sortedMap=new LinkedHashMap<>();
	  for(Map.Entry<K, V> entry: entrys){
		  System.out.println("--> inside the foreach");
	   inMap.put(entry.getKey(),entry.getValue());
	  }
	  return inMap; 
	}
	 public static <K,V extends Comparable<? super V>>  Map<K,V> sortByValue1(Map<K,V> inMap){
      //Map.Entry<K,V> means single entry but we get Set of entrys
	  List< Map.Entry<K, V>> entrys= new LinkedList<>(inMap.entrySet());
	   Collections.sort(entrys,(e1,e2)->e1.getValue().compareTo(e2.getValue()));
	  
	  /*Collections.sort(entrys,new Comparator<Map.Entry<K,V>>(){
	   @Override
	   public int compare(Map.Entry<K,V> o1,Map.Entry<K,V> o2){
	    return (o1.getValue().compareTo(o2.getValue()));
	   }
	  });*/
	  inMap.clear();
	 // HashMap<K,V> sortedMap=new LinkedHashMap<>();
	  for(Map.Entry<K, V> entry: entrys){
		  System.out.println("--> inside the foreach");
	   inMap.put(entry.getKey(),entry.getValue());
	  }
	  return inMap; 
	}
	public static void main(String[] args) 
	{
		System.out.println("Hello World!");
		Map<Object,String> toSort=new LinkedHashMap<>();
		toSort.put("k1","v2");
		toSort.put(3,"v1");
		toSort.put(1,"v3");
		System.out.println(sortByValue1(toSort));
	}
}
