package eu.vitaliy.thrifttutorial;

import org.apache.thrift.TException;
import thrift.calculator.CalculatorService;
import thrift.calculator.InvalidOperation;
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
        log("Invoke java service CalculatorHandler.calculate(%s, %s, %s)", zadanie.getArg1(), zadanie.getArg2(), zadanie.getOperation());
        Operation operacja = zadanie.getOperation();
        switch (operacja) {

            case ADD:
                return zadanie.getArg1() + zadanie.getArg2();
            case SUBTRACT:
                return zadanie.getArg1() - zadanie.getArg2();
            case MULTIPLY:
                return zadanie.getArg1() * zadanie.getArg2();
            case DIVIDE:
                if(zadanie.getArg2() == 0) {
                    throw new InvalidOperation("Divide by 0!");
                }
                return zadanie.getArg1() / zadanie.getArg2();
        }
        return 0;
    }

    private void log(String format, Object ... args) {
        System.out.println(String.format(format, args));
    }
}
