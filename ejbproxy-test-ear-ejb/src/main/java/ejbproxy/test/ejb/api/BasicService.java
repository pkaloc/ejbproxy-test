package ejbproxy.test.ejb.api;

import javax.ejb.Remote;

@Remote
public interface BasicService {
	void doComputation(String compId) throws Exception;

	Object returnResult();
	
	void throwException(String compId);
}
