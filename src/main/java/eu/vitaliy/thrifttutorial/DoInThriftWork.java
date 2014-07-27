package eu.vitaliy.thrifttutorial;

import org.apache.thrift.protocol.TProtocol;

public interface DoInThriftWork<Result> {
      Result doInThrift(TProtocol protocol) throws Exception;
}
