package test;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by ivan on 5/2/16.
 */
public class FunctionInterfaceTest {
	public static void main(String[] args) {
		Predicate<String> predicate = s -> s.length() > 0;
		System.out.println(predicate.test("ivan"));
		System.out.println(predicate.negate().test("ivan"));

		Function<String, Integer> function = Integer::valueOf;
		System.out.println(function.apply("12"));

		Consumer<String> consumer = s -> System.out.println(s);
		//no return value
		consumer.accept("ivan");

		Comparator<String> comparator = (s1, s2) -> s1.compareTo(s2);
		System.out.println(comparator.compare("ivan1", "ivan2"));
		System.out.println(comparator.reversed().compare("ivan1", "ivan2"));

		Optional<String> optional = Optional.of("ivan");
		System.out.println(optional.isPresent());
		System.out.println(optional.get());
		System.out.println(optional.orElse("empty"));
	}
}
