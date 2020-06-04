package com.reacitve.chapter2.observer;

public class ConcreteObserverA implements Observer<String> {
	@Override
	public void observe(String event) {
		System.out.println("Observer A: " + event);
	}

}

class ConcreteObserverB implements Observer<String> {
	@Override
	public void observe(String event) {
		System.out.println("Observer B: " + event);
	}
}
