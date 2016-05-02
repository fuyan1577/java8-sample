package test;

/**
 * Created by ivan on 5/2/16.
 */
public class ConverterTest {
	public static void main(String[] args) {
		Converter<String, Integer> converter = from -> Integer.valueOf(from);
		System.out.println(converter.convert("12"));

		Converter<String, Integer> converter1 = Integer::valueOf;
		System.out.println(converter1.convert("123"));
	}
}

