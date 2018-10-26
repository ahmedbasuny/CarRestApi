package com.evolvice.task.evolvicecar.car;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evolvice.task.evolvicecar.car.exception.CarDataNotValidException;
import com.evolvice.task.evolvicecar.car.exception.CarNotFoundException;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepo;
	
	public List<Car> findAll() {
		return carRepo.findAll();
	}
	
	public Car save(Car car) {
		validate(car);
		return carRepo.save(car);
	}
	
	public Car find(Integer id) {
		return carRepo.findById(id)
				.orElseThrow(() -> new CarNotFoundException(id));
	}
	
	public void delete(Integer id) {
		if(find(id).getId() == id) {
			carRepo.deleteById(id);
		} else {
			throw new CarNotFoundException(id);
		}
		
	}
	
	public boolean validate(Car car) {
		if (car.getBrand().trim().equals("") || car.getModel().trim().equals("") || car.getProductionYear().trim().equals("")) {
			throw new CarDataNotValidException();
		}
		return true;
	}
}
