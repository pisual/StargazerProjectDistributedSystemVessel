package com.stargazerproject.order;

public interface Entity<T>{

	public String toString();

	public T clone() throws CloneNotSupportedException;

}
