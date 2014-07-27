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

    public static final String DISCOVERY_HOST = "localhost";
    public static final short DISCOVERY_PORT = 10001;

    public static void main( String[] args ) throws TTransportException {
        DiscoveryServiceClient discoveryServiceClient = new DiscoveryServiceClient(DISCOVERY_HOST, DISCOVERY_PORT);
        short port = discoveryServiceClient.getPort();
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
