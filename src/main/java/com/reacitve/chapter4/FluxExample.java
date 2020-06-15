package com.reacitve.chapter4;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class FluxExample {
	public static void main(String[] args) {
		Flux<String> stream1 = Flux.just("Hello", "world");
		Flux<Integer> stream2 = Flux.fromArray(new Integer[]{1, 2, 3});
		Flux<Integer> stream3 = Flux.fromIterable(Arrays.asList(9, 8, 7));

		Flux<Integer> stream4 = Flux.range(2010, 9);

		stream4.toStream().forEach(integer -> System.out.println(integer));

		Mono<String> stream5 = Mono.just("One");
		Mono<String> stream6 = Mono.justOrEmpty(null);
		Mono<String> stream7 = Mono.justOrEmpty(Optional.empty());

		Flux.just("A", "B", "C")
				.subscribe(
						data -> log.info("onNext : {}", data),
						err -> {
						},
						() -> log.info("onComplete")

				);

		Flux.range(1, 100)
				.subscribe(
						data -> log.info("onNext : {}", data),
						err -> {
						},
						() -> log.info("onComplete"),
						subscription -> {
							subscription.request(4);
							subscription.cancel();
						}
				);

		Subscriber<String> subscriber = new Subscriber<String>() {
			volatile Subscription subscription;

			@Override
			public void onSubscribe(Subscription s) {
				subscription = s;
				log.info("initial reqeust for 1 element");
				subscription.request(1);
			}

			@Override
			public void onNext(String s) {
				log.info("onNext: {} ", s);
				log.info("requesting 1 more element");

				subscription.request(1);
			}

			@Override
			public void onError(Throwable t) {
				log.warn("onError: {}", t.getMessage());
			}

			@Override
			public void onComplete() {
				log.info("onCompleate");
			}
		};

		Flux<String> stream = Flux.just("Hello", "World", "!");
		stream.subscribe(subscriber);


	}
}
