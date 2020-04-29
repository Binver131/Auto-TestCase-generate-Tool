package autotestcasegeneratetool;

import org.eclipse.ui.IActionFilter;
/**
 * Œﬁ”√¿‡
 * 
 * */
public class Person implements IActionFilter { 
	 
	   private String firstName = "John"; 
	   private String lastName = "Doe"; 
	   protected int age = 37; 
	   public Person[] children = new Person[0]; 
	   public Person parent = null; 
	   public Person(String firstName, String lastName, int age) { 
	       this.firstName = firstName; 
	       this.lastName = lastName; 
	       this.age = age; 
	   } 
	   public Person(String firstName, String lastName, int age, Person[] children) { 
	       this(firstName, lastName, age); 
	       this.children = children; 
	       for (int i = 0; i < children.length; i++) { 
	           children[i].parent = this; 
	       } 
	   } 
	   public String getFirstName() { 
	       return this.firstName; 
	   } 
	   public String getLastName() { 
	       return this.lastName; 
	   } 
	   public static Person[] example() { 
	       return new Person[] { 
	               new Person("Dan", "Rubel", 38, new Person[] { 
	                       new Person("Beth", "Rubel", 8), 
	                       new Person("David", "Rubel", 3) }), 
	               new Person("Eric", "Clayberg", 39, new Person[] { 
	                       new Person("Lauren", "Clayberg", 6), 
	                       new Person("Lee", "Clayberg", 4) }), 
	               new Person("Mike", "Taylor", 52) }; 
	   } 
	   public String toString() { 
	       return firstName + " " + lastName; 
	   } 
	   public boolean testAttribute(Object target, String name, String value) { 
	 
	       if (target instanceof Person) { 
	           Person person = (Person) target; 
	           if (name.equals("firstName") && value.equals(person.getFirstName())) { 
	               return true; 
	           } 
	 
	           if (name.equals("lastName") && value.equals(person.getLastName())) { 
	               return true; 
	           } 
	       } 
	       return false; 
	   } 
	}
