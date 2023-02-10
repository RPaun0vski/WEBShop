package model;

public class PO {
	
	private int id;
	private double unit_price, qty;
	private String product, UoM, delivery_address, phone, mail, PO_status;
	
	
	
	public PO() {
		super();
	}

	public PO(int id, String product, double unit_price, double qty, String uom, String delivery_address, String phone,
			String mail, String PO_status) {
		super();
		this.id = id;
		this.product = product;
		this.unit_price = unit_price;
		this.qty = qty;
		this.UoM = uom;
		this.delivery_address = delivery_address;
		this.phone = phone;
		this.mail = mail;
		this.PO_status = PO_status;
	}

	public String getPO_status() {
		return PO_status;
	}

	public void setPO_status(String pO_status) {
		switch (pO_status) {
		case "ORDERED":
			PO_status = pO_status;
			break;
		case "SENT":
			PO_status = pO_status;
			break;
		case "DELIVERED":
			PO_status = pO_status;
			break;
		case "CLOSED":
			PO_status = pO_status;
			break;

		default: 
			break;
		}
		
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getUoM() {
		return UoM;
	}

	public void setUoM(String uoM) {
		UoM = uoM;
	}

	public String getDelivery_address() {
		return delivery_address;
	}

	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "PO [id=" + id + ", unit_price=" + unit_price + ", qty=" + qty + ", product=" + product + ", UoM=" + UoM
				+ ", delivery_address=" + delivery_address + ", phone=" + phone + ", mail=" + mail + "]";
	}
	
	}
