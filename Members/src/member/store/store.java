package member.store;

import java.util.ArrayList;
import member.info.info;


public class store {

/*	private static store instance = new store(); 
	
	private store() {}
	
	public static store getInstance() {
		return instance; 
				
	}*/
	info in = new info();

	public info Description(int id, String name, String address, double donation)
	{

		in.setId(id);
		in.setAddress(address);
		in.setName(name);
		in.setDonation(donation);
		return in;
	}
	
	
}
