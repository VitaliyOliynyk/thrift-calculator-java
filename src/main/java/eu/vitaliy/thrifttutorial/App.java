package eu.vitaliy.thrifttutorial;

import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import thrift.calculator.CalculatorService;
import thrift.discoveryservice.ServiceNotFoundException;

/**
 * Hello world!
 *
 */
public class App 
{

    public static final String DISCOVERY_HOST = "localhost";
    public static final short DISCOVERY_PORT = 10001;
    public static final String CALCULATOR_SERVICE_NAME = "calculator";

    public static void main( String[] args ) throws TTransportException {
        DiscoveryServiceClient discoveryServiceClient = new DiscoveryServiceClient(DISCOVERY_HOST, DISCOVERY_PORT);
        short port = 0;
        try {
            port = discoveryServiceClient.getInfo(CALCULATOR_SERVICE_NAME).getPort();
        } catch (ServiceNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (TException e) {
            System.out.println("Error:" + e.getMessage());
            e.printStackTrace();
        }
        //
        TServerSocket serverTransport = new TServerSocket(port);
        CalculatorHandler calculatorHandler = new CalculatorHandler();
        CalculatorService.Processor<CalculatorHandler> processor = new CalculatorService.Processor<CalculatorHandler>(calculatorHandler);
        TServer server = new TThreadPoolServer(
                new TThreadPoolServer.Args(serverTransport).processor(processor));
        System.out.println("Starting server on port " + port + " ...");
        server.serve();
    }
}
