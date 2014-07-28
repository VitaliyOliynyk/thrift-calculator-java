package eu.vitaliy.thrifttutorial;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ThriftTemplate {

    private String host;
    private short port;

    public ThriftTemplate(String host, short port) {
        this.host = host;
        this.port = port;
    }

    public <Result> Result doInThrift(DoInThriftWork<Result> doInThriftWork) {
        TTransport transport = new TSocket(host, port);
        TProtocol protocol = new TBinaryProtocol(transport);
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
