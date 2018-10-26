package com.evolvice.task.evolvicecar.car;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class CarResourceAssembler implements ResourceAssembler<Car, Resource<Car>>{
	@Override
	public Resource<Car> toResource(Car car) {
		return new Resource<Car>(car, 
				linkTo(methodOn(CarController.class).getCar(car.getId())).withSelfRel(),
				linkTo(methodOn(CarController.class).getAll()).withRel("cars"));
	}
}
