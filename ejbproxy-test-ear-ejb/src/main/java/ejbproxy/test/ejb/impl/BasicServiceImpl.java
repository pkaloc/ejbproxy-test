package ejbproxy.test.ejb.impl;

import ejbproxy.test.ejb.api.BasicService;

import javax.ejb.Stateless;
import java.util.Random;

@Stateless
public class BasicServiceImpl implements BasicService {
	public void doComputation(String compId) throws Exception {
		System.out.println("BasicServiceImpl#doComputation");
	}

	public Object returnResult() {
		Object result = new Random().nextInt();
		System.out.println("BasicServiceImpl#returnResult: " + result);
		return result;
	}

	public void throwException(String compId) {
		System.out.println("BasicServiceImpl#throwException");
		throw new IllegalArgumentException("The passed arg is invalid!");
	}
}
