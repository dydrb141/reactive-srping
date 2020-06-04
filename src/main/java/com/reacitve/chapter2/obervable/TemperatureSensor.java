package com.reacitve.chapter2.obervable;

import com.reacitve.chapter2.template.Temperature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import rx.Observable;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

@Component
public class TemperatureSensor {
	private static final Logger log = LoggerFactory.getLogger(TemperatureSensor.class);

	private final Random random = new Random();

	private final Observable<Temperature> dataStrema = Observable
			.range(0, Integer.MAX_VALUE)
			.concatMap(ignore -> Observable.just(1).delay(random.nextInt(5000), TimeUnit.MILLISECONDS).map(ignore2 -> this.probe()))
			.publish()
			.refCount();

	private Temperature probe() {
		double actualTemp = 16 + random.nextGaussian() * 10;
		log.info("Asking sensor, sensor value: {}", actualTemp);

		return new Temperature(actualTemp);

	}
}
