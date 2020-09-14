package com.app.kk.kk.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
class Persion implements Comparable{
	private String name;
	private int age;
	Persion(String name,int age){
		this.name=name;
		this.age=age;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persion other = (Persion) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Persion [name=" + name + ", age=" + age + "]";
	}
	@Override 
	public  int compareTo(Object obj) {
		// TODO Auto-generated method stub
		Persion y=(Persion)obj;
		return this.age<y.getAge()?-1:this.age>y.getAge()?+1:0;
	}
	
	
	
}



 class StreamCollectorsGroupBy {
	 
	 private class PrivateClass{
		String test="data";
		public void m1() {}
	 } 
	 private class PrivateClzz extends PrivateClass{
		 
		 PrivateClzz c=new PrivateClzz();
		 
	 }
	public static int age=20;
	public static List<Persion>  persions(){
		return Arrays.asList(new Persion("anil",26),new Persion("naveen", 27),new Persion("anil",25),new Persion("vishanu",20));
	}
	public void testStatic() {
		persions();
		System.out.println(age);
	}
	
	public static void main(String[] args) {
		Supplier<Stream<String>> data=()->Stream.of("Ram","Shyam","Rahul","Kalue","Ram","Kalue","Rahul","Ramkumar");
		
		System.out.println(data.get().collect(Collectors.groupingBy(x->x.charAt(0))));
		Map<Character, List<String>> gData=
		data.get().collect(Collectors.groupingBy(x->x.charAt(0)));
		
		Map<Character, List<String>> gDataOr=
				data.get().collect(Collectors.groupingBy(x->x.charAt(0),Collectors.toList()));
		System.out.println(gData);
		System.out.println(data.get().collect(Collectors.groupingBy(Function.identity(),Collectors.counting())));
		Map<String,Long> chData=data.get().map(arrCh->arrCh.split("")).flatMap(x->Arrays.stream(x)).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		System.out.println(chData);
		System.out.println(data.get().collect(Collectors.partitioningBy(s->s.equals("Ram"))));
		Map<Boolean,List<String>> dataP=data.get().collect(Collectors.partitioningBy(x->x.length()==5));
		System.out.println(dataP);
		gData.forEach((key,value)->System.out.println("key:"+key+" value :"+value.stream().filter(x->x.length()>3).collect(Collectors.joining(","," [ "," ]"))));//[ Rahul,Rahul,Ramkumar ]
		
		Collector<Persion,StringJoiner,String> c=Collector.of(()->new StringJoiner("|"),(j,p)->j.add(p.getName().toUpperCase()), (j1,j2)->j1.merge(j2),StringJoiner::toString);
		
		 //data.get().reduce(accumulator)
		
		// if we get persion who even age or odd age 
		System.out.println(persions().stream().collect(Collectors.partitioningBy(x->x.getAge()%2==0)));
		
	    // need to get named by ages 
		System.out.println(persions().parallelStream().collect(Collectors.groupingBy(p->p.getName(),Collectors.mapping(Persion::getAge,Collectors.toList()))));
		
		// find out count with respect to name 
		Map<String,Long> countByName=persions().stream().collect(Collectors.groupingBy(p->p.getName(),Collectors.counting()));
		
		System.out.println("Count By Name :"+countByName);
		
		// if we want to count as in int only then what need to do
		Map<String,Integer> countByNameInt=persions().stream().collect(Collectors.groupingBy(p->p.getName(),Collectors.collectingAndThen(Collectors.counting(), value->value.intValue())));
		System.out.println(countByNameInt);
		Map<String,Integer> countByNameInt2=persions().stream().collect(Collectors.groupingBy(p->p.getName(),Collectors.collectingAndThen(Collectors.counting(),Long::intValue)));
		System.out.println("Integer formate: "+countByNameInt2);
		Map<String,String> countByNameStr=persions().stream().collect(Collectors.groupingBy(Persion::getName,Collectors.collectingAndThen(Collectors.counting(),v->v.toString())));
		System.out.println("string formate:"+countByNameStr);
		// MaxBy
		System.out.println(persions().stream().collect(Collectors.maxBy((Persion::compareTo))));
		// we want to print name only for elder 
		//System.out.println(persions().stream().collect(Collectors.maxBy(Persion::compareTo)).get().getName());
		System.out.println(persions().stream().collect(Collectors.maxBy(Comparator.comparing(Persion::getName))));
		//get older persion name 
//		System.out.println(persions().stream().collect(Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Persion::getAge)),persion->persion.map(Persion::getName).orElse(""))));
		
		String data22="my name is Anil Pal";
		String res=Stream.of(data22.split(" ")).map(s->s.length()%2==0?s:new String(new StringBuilder(s).reverse())).collect(Collectors.joining(" "));
		System.out.println(res);
		
		Map<Character,Integer> countInInt=data22.codePoints().mapToObj(x->(char)x).collect(Collectors.groupingBy(Function.identity(),Collectors.collectingAndThen(Collectors.counting(),value->value.intValue())));
   }
}


