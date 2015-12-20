package ejbproxy.test.ejb.impl;

import ejbproxy.test.ejb.api.EchoService;

import javax.ejb.Stateless;
import java.util.Random;

@Stateless
public class EchoServiceImpl1 implements EchoService {
	private final Random random = new Random();
	public String echo(String msg) {
		if (random.nextInt() % 2 == 0) {
			System.out.println("EchoServiceImpl1: Returning null!");
			return null;
		} else {
			System.out.println("EchoServiceImpl1: Returning msg!");
			return msg;
		}
	}
}
