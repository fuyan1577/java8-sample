package test;

/**
 * Created by ivan on 5/2/16.
 */
@FunctionalInterface
public interface Converter<F, T> {
	T convert(F from);
}