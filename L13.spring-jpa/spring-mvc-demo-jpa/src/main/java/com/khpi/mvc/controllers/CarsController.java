package com.khpi.mvc.controllers;

import com.khpi.mvc.models.Account;
import com.khpi.mvc.models.Car;
import com.khpi.mvc.repositories.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CarsController
{
    @Autowired
    private CarsRepository carsRepository;

    @RequestMapping(path = "/cars", method = RequestMethod.GET)
    @ResponseBody
    public String getCarsByOwnerFirstName(
        @RequestParam(required = false, name = "fname") String firstName)
    {
        List<Car> cars = (firstName != null)
            ? carsRepository.findAllByOwners_FirstName(firstName)
            : carsRepository.findAll();

        String r = "<html><body>";
        for (Car car: cars)
            r += car.toString() + "<br>";
        return r + "</body></html>";

        //return cars.toString();
    }
}
