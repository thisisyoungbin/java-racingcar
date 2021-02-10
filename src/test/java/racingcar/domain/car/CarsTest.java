package racingcar.domain.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.car.util.MovingStrategy;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarsTest {

    @DisplayName("Cars 객체를 생성하는 기능을 테스트한다")
    @Test
    void testInitCars() {
        //given
        List<Car> carGroup = Arrays.asList(new Car("benz"), new Car("kia"));

        //when
        Cars cars = new Cars(carGroup);

        //then
        assertThat(cars.getCars()).isEqualTo(carGroup);
    }

    @DisplayName("중복되는 자동차이름이 있는 경우 예외를 발생시킨다.")
    @Test
    void testInitCarsIfExistDuplicatedCarNames() {
        //given
        List<Car> carGroup = Arrays.asList(new Car("benz"), new Car("kia"), new Car("benz"));

        //when //then
        assertThatThrownBy(() -> new Cars(carGroup))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Cars 객체의 최대 거리를 얻는 기능을 테스트한다")
    @Test
    void testExtractMaxPosition() {
        //given
        List<Car> carGroup = Arrays.asList(
                new Car("benz",3),
                new Car("kia", 2),
                new Car("bmw",0));
        Cars cars = new Cars(carGroup);

        //when
        int maxPosition = cars.extractMaxPosition();

        //then
        assertThat(maxPosition).isEqualTo(3);
    }

    @DisplayName("Cars 객체를 실행하는 기능을 테스트한다")
    @Test
    void testPlay() {
        //given
        List<Car> carGroup = Arrays.asList(new Car("benz"), new Car("kia"), new Car("bmw"));
        Cars cars = new Cars(carGroup);
        MovingStrategy fixedMovingStrategy = new FixedMovingStrategy(4);
        //when
        Cars movedCars = cars.play(fixedMovingStrategy);

        //then
        List<Car> carsValue = movedCars.getCars();
        assertThat(carsValue.get(0).toIntPosition()).isEqualTo(1);
        assertThat(carsValue.get(1).toIntPosition()).isEqualTo(1);
        assertThat(carsValue.get(2).toIntPosition()).isEqualTo(1);
    }
}