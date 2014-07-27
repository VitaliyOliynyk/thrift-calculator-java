package eu.vitaliy.thrifttutorial;

import org.apache.thrift.protocol.TProtocol;
import thrift.discoveryservice.DiscoveryService;
import thrift.discoveryservice.ServiceInfo;

public class DiscoveryServiceClient extends ThriftClient {

    public DiscoveryServiceClient(String discoveryHost, short discoveryPort) {
        super(discoveryHost, discoveryPort);
    }

    public short getPort() {

        ServiceInfo calculatorServiceInfo = doInThrift(new DoInThriftWork<ServiceInfo>() {
            @Override
            public ServiceInfo doInThrift(TProtocol protocol) throws Exception{
                DiscoveryService.Client client = new DiscoveryService.Client(protocol);
                ServiceInfo calculatorServiceInfo = client.getInfo("calculator");
                return calculatorServiceInfo;
            }
        });

        return calculatorServiceInfo.getPort();
    }
}
