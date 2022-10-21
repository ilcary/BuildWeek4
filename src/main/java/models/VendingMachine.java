package models;

import javax.persistence.Entity;

@Entity
public class VendingMachine extends Seller{
	
	Boolean workingBoolean;

	public VendingMachine() {
	}

	public VendingMachine(String sellerName) {
		super(sellerName);
		this.workingBoolean=true;
		this.typeSeller = TypeSeller.VENDING_MACHINE;
	}

	public Boolean getWorkingBoolean() {
		return workingBoolean;
	}

	public void setWorkingBoolean(Boolean workingBoolean) {
		this.workingBoolean = workingBoolean;
	}
	
	@Override
	public String toString() {
		return this.getSellerName()+" of type "+this.getTypeSeller();
	}
	
	

}
