package com.stargazerproject.model.order.shared;

public interface Entity<T>{

	public String toString();

	public T clone() throws CloneNotSupportedException;

}
