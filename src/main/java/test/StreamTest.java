package test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ivan on 5/2/16.
 */
public class StreamTest {
	@Data
	private static class Foo {
		private int fromId;
		private String name;
		private int toId;

	}

	public static void main(String[] args) {
		Foo foo = new Foo();
		foo.setFromId(1);
		foo.setName("name1");
		foo.setToId(2);

		List<Foo> fooList = ImmutableList.of(foo);
		Map<Integer, Foo> fooMap = Maps.uniqueIndex(fooList, obj -> obj.getFromId());
		System.out.println(fooMap);

		System.out.println(
				fooList.stream().collect(Collectors.toMap(Foo::getFromId, java.util.function.Function.identity())));

		Map<Integer, Integer> toIdMap = Maps.transformValues(fooMap, obj -> obj.getToId());
		System.out.println(toIdMap);

		System.out.println(toIdMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList()));

		Map<Integer, Foo> fooMap2 = Maps.transformValues(fooMap, obj -> {
			obj.setName("name3");
			return obj;
		});

		Map<Integer, Foo> fooMap3 = Maps.newHashMap();
		fooMap.forEach((id, obj) -> fooMap3.put(id, obj));
		System.out.println(fooMap2);

		Map<Integer, Foo> fooMap4 = fooMap2.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> {
			Foo result = new Foo();
			result.setFromId(e.getValue().getFromId());
			result.setName("ttt");
			return result;
		}));

		System.out.println(fooMap4);

		fooMap3.replaceAll((k, v) -> {
			v.setName("ivan");
			return v;
		});

		System.out.println(fooMap3);


		List<String> strList = ImmutableList.of("a1", "a2", "b1", "b2");

		strList.stream().filter( s -> s.startsWith("a")).forEach(System.out::println);

		strList.stream().sorted((s1, s2) -> s2.compareTo(s1)).forEach(System.out::println);

		strList.stream().map(s -> s.toUpperCase()).forEach(System.out::println);

		System.out.println(strList.stream().count());

		strList.stream().reduce(((s1, s2) -> s1 + "#" + s2)).ifPresent(System.out::println);

		Map<Integer, String> map = Maps.newHashMap();
		map.put(1, "a");
		map.put(2, "b");
		map.computeIfAbsent(1, v -> v + "_su");
		System.out.println(map.get(1));
		map.computeIfAbsent(3, v -> "_su");
		System.out.println(map.get(3));
	}
}
