package ejbproxy.test.ejb.api;

import javax.ejb.Remote;

@Remote
public interface EchoService {
	String echo(String msg);
}
