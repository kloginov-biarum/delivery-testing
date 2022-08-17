import java.util.Locale;

public class DeliveryCost {
    public double deliveryCostCalc(double distance, boolean big, boolean fragile, String load ){
        double minCost = 400;
        double finalCost = minCost;
        double distanceValue = distance;
        boolean bigValue = big;
        boolean fragileValue = fragile;
        String loadValue = load;

        if (distanceValue>30) {
            finalCost+=200;
        }
        else if(distanceValue<=30 & distanceValue>10){
            finalCost+=200;
        }
        else if (distanceValue<=10 & distanceValue>2){
            finalCost+=100;
        }
        else if (distanceValue<=2 & distanceValue>=0){
            finalCost+=50;
        }
        else{
            throw new IllegalArgumentException("Расстояние не может быть больше нуля, физика против!");
        }
        if (bigValue==true){
            finalCost+=200;
        }
        else if(bigValue==false){
            finalCost+=100;
        }
        else {
            throw new IllegalArgumentException("Некорректное значение аргумента big. Допустимо true  или true");
        }
        if (fragileValue==true){
            if (distanceValue>30){
                throw new IllegalArgumentException("Хрупкие грузы нельзя возить далее 50 км");
            }
            finalCost+=300;
        }
        else if (fragileValue==false){
        }
        else {
            throw new IllegalArgumentException("Некорректное значение аргумента fragile. Допустимо true  или false");
        }
        loadValue.toLowerCase(Locale.ROOT);
        switch (loadValue){
            case ("высокая"):
                finalCost*=1.6;
                break;
            case ("очень высокая"):
                finalCost*=1.4;
                break;
            case ("повышенная"):
                finalCost*=1.2;
                break;
        }
        if (finalCost < minCost){
            finalCost = minCost;
        }
        return finalCost;
    }
}
