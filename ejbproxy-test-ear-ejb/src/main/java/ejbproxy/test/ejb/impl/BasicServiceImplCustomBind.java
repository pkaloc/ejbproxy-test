package ejbproxy.test.ejb.impl;

import ejbproxy.test.ejb.api.BasicService;
import org.jboss.annotation.ejb.RemoteBinding;

import javax.ejb.Stateless;
import java.util.Random;

@RemoteBinding(jndiBinding = "BasicServiceImpl/Custom")
@Stateless
public class BasicServiceImplCustomBind implements BasicService {
	public void doComputation(String compId) throws Exception {
		System.out.println("BasicServiceImplCustomBind#doComputation");
	}

	public Object returnResult() {
		Object result = new Random().nextInt();
		System.out
				.println("BasicServiceImplCustomBind#returnResult: " + result);
		return result;
	}
	
	public void throwException(String compId) {
		throw new RuntimeException(new NullPointerException("Embedded null pointer exception!"));
	}
}
