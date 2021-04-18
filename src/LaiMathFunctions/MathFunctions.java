package LaiMathFunctions;

public interface MathFunctions {

    static double SquareRoot(double number, double error) {
        if(number < 0) return -1;
        if(number == 0) return 0;
        double result = number/2;
        double diff = error + 1;
        while(diff > error || diff < 0) {
            double current = 0.5 * (number/result + result);
            diff = result - current;
            result = current;
        }
        return result;
    }

    static double SquareRoot(double number) {                           //assume no square root inputs are smaller than 10^(-45).
        if(number > 1e-9) return SquareRoot(number, 1e-9);
        if(number > 1e-18) return SquareRoot(number, 1e-18);
        if(number > 1e-27) return SquareRoot(number, 1e-27);
        if(number > 1e-36) return SquareRoot(number, 1e-36);
        if(number > 1e-45) return SquareRoot(number, 1e-45);
        return -1;
    }

}
