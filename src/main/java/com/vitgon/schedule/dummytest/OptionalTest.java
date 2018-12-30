package com.vitgon.schedule.dummytest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 
 * For those cases when we want to avoid multiple NULL CHECKING
 * we should use Optional
 * 
 * @author User
 *
 */
public class OptionalTest {
	
	class Outer {
	    Nested nested;
	    Nested getNested() {
	        return nested;
	    }
	}
	class Nested {
	    Inner inner;
	    Inner getInner() {
	        return inner;
	    }
	}
	class Inner {
	    String foo;
	    String getFoo() {
	        return foo;
	    }
	}
	
	OptionalTest() {
		Outer outer = new Outer();
		Nested nested = new Nested();
		Inner inner = new Inner();
		inner.foo = "MyTestString";
		nested.inner = inner;
		outer.nested = nested;
		Optional.of(outer)
		    .map(Outer::getNested)
		    .map(Nested::getInner)
		    .map(Inner::getFoo)
		    .ifPresent(System.out::println);
	}
	
	public static void main(String... args) {
		new OptionalTest();
		
		
		// test if map contains key
		Map<Integer, Map<Integer, String>> outerMap = new HashMap<>();
		Map<Integer, String> innerMap = new HashMap<>();
		innerMap.put(56, "Management");
		outerMap.put(1, innerMap);
		
		
		Optional.of(outerMap)
			.map(x -> x.get(1))
			.map(x -> x.get(56))
			.ifPresent(x -> System.out.println(x));
		
		// So if we try to get by keys that don't exist we will not get NoSuchElementException
		String subject = Optional.of(outerMap)
			.map(x -> x.get(2))
			.map(x -> x.get(50))
			.orElse("No data");
		
		System.out.println(subject);
	}
}
