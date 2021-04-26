package LaiMathFunctions;

public interface MathFunctions {

    static double NthRoot(double number, int power, double error) {
        if(power==0) return 1;
        if(number==0) return 0;
        if(power<0 || number<0 && power%2==0) return -1;
        double result = Math.abs(number)> 4 ? number/2 : (number<0 ? -5 : 5);
        double diff = error + 1;
        while(Math.abs(diff) > error) {
            double current = result/power * (number/Math.pow(result, power) + power - 1);
            diff = result - current;
            result = current;
        }
        return result;
    }

    static double NthRoot(double number, int power) {                    //assume no inputs are smaller than 10^(-45).
        if(Math.abs(number) > 1e-3) return NthRoot(number, power,1e-3);
        if(Math.abs(number) > 1e-6) return NthRoot(number, power,1e-6);
        if(Math.abs(number) > 1e-9) return NthRoot(number, power,1e-9);
        if(Math.abs(number) > 1e-18) return NthRoot(number, power,1e-18);
        if(Math.abs(number) > 1e-27) return NthRoot(number, power,1e-27);
        if(Math.abs(number) > 1e-36) return NthRoot(number, power,1e-36);
        if(Math.abs(number) > 1e-45) return NthRoot(number, power,1e-45);
        return -1;
    }

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
