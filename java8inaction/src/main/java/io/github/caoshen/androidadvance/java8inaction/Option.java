package io.github.caoshen.androidadvance.java8inaction;

import java.util.Optional;
import java.util.function.Function;

import io.github.caoshen.androidadvance.java8inaction.optional.Car;
import io.github.caoshen.androidadvance.java8inaction.optional.Insurance;
import io.github.caoshen.androidadvance.java8inaction.optional.Person;

public class Option {

    public static void main(String[] args) {
        Optional<Car> empty = Optional.empty();
        Car car = new Car();
        // not empty car
        Optional<Car> car1 = Optional.of(car);
        // may be empty car or not
        Optional<Car> car2 = Optional.ofNullable(car);

        Insurance insurance = new Insurance();
        Optional<Insurance> insurance1 = Optional.ofNullable(insurance);
        Optional<String> optName = insurance1.map(Insurance::getName);
    }

    public String getCarInsuranceName(Person person) {
        return Optional.ofNullable(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("unknown");
    }
}