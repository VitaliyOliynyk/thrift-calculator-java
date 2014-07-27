package eu.vitaliy.thrifttutorial;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.discoveryservice.DiscoveryService;

import java.util.concurrent.Callable;

public class ThriftClient {

    private String discoveryHost;
    private short discoveryPort;
    private TProtocol protocol;

    public ThriftClient(String discoveryHost, short discoveryPort) {
        this.discoveryHost = discoveryHost;
        this.discoveryPort = discoveryPort;
    }

    public <Result> Result doInThrift(DoInThriftWork<Result> doInThriftWork) {
        TTransport transport = new TSocket(discoveryHost, discoveryPort);
        protocol = new TBinaryProtocol(transport);
        try {
            transport.open();
            Result result = doInThriftWork.doInThrift(protocol);
            return result;
        } catch (TException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                transport.close();
            } catch (Exception e) {
                ;
            }
        }

        throw new IllegalStateException();
    }
}
