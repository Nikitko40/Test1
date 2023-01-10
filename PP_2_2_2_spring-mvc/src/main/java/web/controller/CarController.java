package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping()
    public String printCars(@RequestParam(value = "count", defaultValue = "5") int numberOfCars, Model model) {

        List<Car> carList = new ArrayList<>();
        carList.add(new Car("Toyota", 1993, 5));
        carList.add(new Car("Nissan", 2019, 5));
        carList.add(new Car("Toyota", 2006, 3));
        carList.add(new Car("Porsche", 1967, 3));
        carList.add(new Car("Subaru", 2007, 5));
        carList = CarService.getCarList(carList, numberOfCars);
        model.addAttribute("Title", "List of cars");
        model.addAttribute("carList", carList);
        return "cars";
    }

}
