package eu.vitaliy.thrifttutorial;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import thrift.discoveryservice.DiscoveryService;
import thrift.discoveryservice.ServiceInfo;
import thrift.discoveryservice.ServiceNotFoundException;

public class DiscoveryServiceClient extends ThriftTemplate implements DiscoveryService.Iface{

    public DiscoveryServiceClient(String discoveryHost, short discoveryPort) {
        super(discoveryHost, discoveryPort);
    }

    @Override
    public ServiceInfo getInfo(final String serviceName) throws ServiceNotFoundException, TException {
        ServiceInfo calculatorServiceInfo = doInThrift(new DoInThriftWork<ServiceInfo>() {
            @Override
            public ServiceInfo doInThrift(TProtocol protocol) throws Exception{
                DiscoveryService.Client client = new DiscoveryService.Client(protocol);
                ServiceInfo calculatorServiceInfo = client.getInfo(serviceName);
                return calculatorServiceInfo;
            }
        });

        return calculatorServiceInfo;
    }
}
