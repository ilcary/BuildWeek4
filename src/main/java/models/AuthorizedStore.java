package models;

import javax.persistence.Entity;

@Entity
public class AuthorizedStore extends Seller{
	
	
	

	public AuthorizedStore() {
	}

	public AuthorizedStore(String sellerName) {
		super(sellerName);
		this.typeSeller = TypeSeller.AUTHORIZED_STORE;
	}

	
	
}
