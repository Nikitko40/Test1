package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private List<Car> carList = new ArrayList<>();

    public static List<Car> getCarList(List<Car> list, int number) {
        if (number == 0 || number >= 5) return list;
        return list.stream().limit(number).collect(Collectors.toList());
    }

}
