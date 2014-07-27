package eu.vitaliy.thrifttutorial;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import thrift.calculator.CalculatorService;

/**
 * Hello world!
 *
 */
public class App 
{

    public static final int PORT = 12345;

    public static void main( String[] args ) throws TTransportException {
        TServerSocket serverTransport = new TServerSocket(PORT);
        CalculatorHandler calculatorHandler = new CalculatorHandler();
        CalculatorService.Processor<CalculatorHandler> processor = new CalculatorService.Processor<CalculatorHandler>(calculatorHandler);
        TServer server = new TThreadPoolServer(
                new TThreadPoolServer.Args(serverTransport).processor(processor));
        System.out.println("Starting server on port "+PORT+" ...");
        server.serve();
    }
}
