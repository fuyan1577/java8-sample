package test;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by ivan on 5/2/16.
 */
public class MethodInvokeTest {
	@Data
	public static class Car {
		private String model;

		//Supplier接口不能有任何参数
		public static Car Create(final Supplier<Car> supplier) {
			return supplier.get();
		}

		public static void collide(final Car car) {
			System.out.println("Collided: " + car);
		}

		public void follow(final Car another) {
			System.out.println("Follow the " + another);
		}

		public void repair() {
			System.out.println("Repaired " + this);
		}

		@Override
		public String toString() {
			return "Model: " + model;
		}
	}

	@Data
	public static class Car2 {
		private String model;

		public Car2(String model) {
			this.model = model;
		}

		@Override
		public String toString() {
			return "Model: " + model;
		}
	}

	public interface CarFactory<T extends Car2> {
		Car2 create(String modelName);
	}

	public static void main(String[] args) {
		Car car = Car.Create(Car::new);
		car.setModel("BMW");

		List<Car> cars = Arrays.asList(car);

		cars.forEach(Car::collide);

		cars.forEach(Car::repair);

		//below statement will compile error
		//cars.forEach(Car::follow);

		cars.forEach(car::follow);
		//below statements will compile error
		//cars.forEach(car::repair);
		//cars.forEach(car::collide);

		CarFactory carFactory = Car2::new;
		System.out.println(carFactory.create("BMW"));
	}

}
