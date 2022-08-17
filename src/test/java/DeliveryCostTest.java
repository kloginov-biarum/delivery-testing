
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class DeliveryCostTest {

    DeliveryCost deliveryCost = new DeliveryCost();

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void deliveryCostCalcPositiveTest(double expected, double distance, boolean big, boolean fragile, String load) {
        assertEquals(expected, deliveryCost.deliveryCostCalc(distance, big,fragile, load));
    }

    static Stream<Arguments> dataProvider(){
        return Stream.of(
                arguments(1440, 45, true, false, "очень высокая"),
                arguments(700, 15, false, false, "обычная")
        );
    }


    @ParameterizedTest
    @MethodSource("dataProviderNegative")
    public void costCalcNegativeTest(double expected, double distance, boolean big, boolean fragile, String load) {
        assertNotEquals(expected, deliveryCost.deliveryCostCalc(distance, big,fragile, load));
    }

    static Stream <Arguments> dataProviderNegative() {
        return Stream.of(
                arguments(1500, 45, true, false, "очень высокая"),
                arguments(700, 13, false, true, "повышенная"),
                arguments(500, 7, true, true, "высокая"),
                arguments(1000, 1.5, false, false, "" )

        );
    }

    @ParameterizedTest
    @MethodSource("dataProviderException")
    public void costCalcExceptionTest(String exceptionMessage,double distance, boolean big, boolean fragile, String load){
        Throwable exceptionThatWasThrown = assertThrows(IllegalArgumentException.class, () -> {
            deliveryCost.deliveryCostCalc(distance, big,fragile, load);
        });
        assertTrue(exceptionThatWasThrown.getMessage().contains(exceptionMessage),"Actual message is: "
                + exceptionThatWasThrown.getMessage());
    }

    static Stream <Arguments>  dataProviderException(){
        return Stream.of(
                arguments("Расстояние не может быть меньше нуля, физика против!" ,-1,true, false, ""),
                arguments("Хрупкие грузы нельзя возить далее 30 км", 50, false, true, "высокая")
        );
    }
}