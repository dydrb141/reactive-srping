package com.reacitve.chapter2.observer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class ConcreteSubjectTest {

	@Test
	void registerObserver() {
		Subject<String> subject = new ConcreteSubject();
		Observer<String> observerA = Mockito.spy(new ConcreteObserverA());
		Observer<String> observerB = Mockito.spy(new ConcreteObserverB());

		subject.notifyObservers("No listners");

		subject.registerObserver(observerA);
		subject.notifyObservers("Message for A");

		subject.registerObserver(observerB);
		subject.notifyObservers("Message for A & B");

		subject.unregisterObserver(observerA);
		subject.notifyObservers("Message for B");

		subject.unregisterObserver(observerB);
		subject.notifyObservers("No listners");

		//then
		Mockito.verify(observerA, times(1)).observe("Message for A");
		Mockito.verify(observerA, times(1)).observe("Message for A & B");
		Mockito.verifyNoInteractions(observerA);

		Mockito.verify(observerA, times(1)).observe("Message for A & B");
		Mockito.verify(observerA, times(1)).observe("Messgae for B");
		Mockito.verifyNoMoreInteractions(observerB);


	}

	@Test
	void unregisterObserver() {
	}

	@Test
	void notifyObservers() {
	}
}