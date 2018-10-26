package com.evolvice.task.evolvicecar;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.evolvice.task.evolvicecar.car.Car;
import com.evolvice.task.evolvicecar.car.CarService;

@SpringBootApplication
public class EvolviceCarApplication implements CommandLineRunner {
	
	@Autowired
	private CarService carService;
	
	public static void main(String[] args) {
		SpringApplication.run(EvolviceCarApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Car[] cars = { 
				new Car("BMW", "M4 Cabrio", "2014", "M4 Cabrio (2979 cm³, 431 PS) 2014"),
				new Car("Honda", "Civic Tourer 1.6 i-DTEC", "2015", "Civic Tourer 1.6 i-DTEC (1597 cm³, 120 PS) 2015"),
				new Car("Volkswagen", "Golf Alltrack 2.0 TDI BMT 4MOTION", "2017", "Golf Alltrack 2.0 TDI BMT 4MOTION (1968 cm³, 184 PS) 2017"),
				new Car("Audi", "A3", "2018", "A3 Audi 2018"),
				new Car("Jaguar", "XF", "2018", "Portfolio 4dr Sedan (3.0L 6cyl S/C 8A)")};
		Arrays.asList(cars).forEach(car -> carService.save(car));
	}
}
