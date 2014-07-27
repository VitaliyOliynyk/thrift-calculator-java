package eu.vitaliy.thrifttutorial;

import org.apache.thrift.TException;
import thrift.calculator.CalculatorService;
import thrift.calculator.Operation;
import thrift.calculator.Work;

/**
 * User: xaoc
 * Date: 24.07.14
 * Time: 00:35
 */
public class CalculatorHandler implements CalculatorService.Iface {
    @Override
    public int calculate(Work zadanie) throws TException {
        Operation operacja = zadanie.getOperacja();
        switch (operacja) {

            case ADD:
                return zadanie.getArg1() + zadanie.getArg2();
            case SUBTRACT:
                return zadanie.getArg1() - zadanie.getArg2();
            case MULTIPLY:
                return zadanie.getArg1() * zadanie.getArg2();
            case DIVIDE:
                return zadanie.getArg1() / zadanie.getArg2();
        }
        return 0;
    }
}
