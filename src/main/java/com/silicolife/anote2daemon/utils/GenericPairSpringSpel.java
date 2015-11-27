package com.silicolife.anote2daemon.utils;

import org.springframework.stereotype.Component;

@Component
public class GenericPairSpringSpel<X,Y>{
	
	private X x;
	private Y y;
	
	private GenericPairSpringSpel(X x, Y y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public GenericPairSpringSpel() {
		super();
	}

	public GenericPairSpringSpel<X,Y> getGenericPairSpringSpel(X x, Y y){
		return new GenericPairSpringSpel<X, Y>(x,y);
	}

	public X getX() {
		return x;
	}
	
	public Y getY() {
		return y;
	}
}
