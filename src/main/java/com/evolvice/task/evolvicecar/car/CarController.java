package com.evolvice.task.evolvicecar.car;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1")
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private CarResourceAssembler carAssembler;
	
	@RequestMapping("/cars")
	public Resources<Resource<Car>> getAll() {
		List<Resource<Car>> cars = carService.findAll().stream()
				.map(carAssembler::toResource).collect(Collectors.toList());
		return new Resources<>(cars, 
				linkTo(methodOn(CarController.class).getAll()).withSelfRel());
	}
	
	@RequestMapping("/cars/{id}")
	public Resource<Car> getCar(@PathVariable Integer id) {
		Car car = carService.find(id);
		return carAssembler.toResource(car);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/cars")
	ResponseEntity<?> saveNewCar(@RequestBody Car car) throws URISyntaxException {
		Resource<Car> carResource = carAssembler.toResource(carService.save(car));
		return ResponseEntity
			.created(new URI(carResource.getId().expand().getHref()))
			.body(carResource);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/cars/{id}")
	ResponseEntity<?> updateCar(@RequestBody Car newCar, @PathVariable Integer id) throws URISyntaxException {
		Car updatedCar = carService.find(id);
		updatedCar.setBrand(newCar.getBrand());
		updatedCar.setModel(newCar.getModel());
		updatedCar.setProductionYear(newCar.getProductionYear());
		updatedCar.setModelDetails(newCar.getModelDetails());
		Car car =  carService.save(updatedCar);
		Resource<Car> carResource = carAssembler.toResource(car);

		return ResponseEntity
			.created(new URI(carResource.getId().expand().getHref()))
			.body(carResource);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/cars/{id}")
	ResponseEntity<?> deleteCar(@PathVariable Integer id) {
		carService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
