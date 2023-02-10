package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DAOOrder {

	private DataSource ds;

	public DAOOrder(){
		try {
			InitialContext cxt = new InitialContext();
			ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/mysql" ); 
			if ( ds == null ) { 
			} 		
		} catch (NamingException e) {
		}
	}
	private static String CREATEORDER = "INSERT INTO webshop (product, unit_price, qty, UoM, delivery_address, phone, mail, PO_status) VALUES(?, ?,?,?,?,?,?,?)";
	private static String DELETEORDER = "DELETE FROM webshop WHERE id = ?";
	private static String GETORDER = "SELECT * FROM webshop WHERE id = ? ";
	private static String UPDATEORDER = "UPDATE student SET product = ?, unit_price = ?, qty=?, UoM = ?, delivery_address = ?, phone = ?, mail = ?, PO_status = ? WHERE id = ?";

	public void createOrder (PO po) {
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con=ds.getConnection();
			pstm=con.prepareStatement(CREATEORDER);

			pstm.setString(1, po.getProduct());
			pstm.setDouble(2, po.getUnit_price());
			pstm.setDouble(3, po.getQty());
			pstm.setString(4, po.getUoM());
			pstm.setString(5, po.getDelivery_address());
			pstm.setString(6, po.getPhone());
			pstm.setString(7, po.getMail());
			pstm.setString(8, po.getPO_status());

			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();		
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteOrder(int id) {
		Connection con=null;
		PreparedStatement pstm=null;

		try {	
			con=ds.getConnection();
			pstm=con.prepareStatement(DELETEORDER);

			pstm.setInt(1, id);
			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}	
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateOrder(PO po){
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(UPDATEORDER);

			pstm.setString(1, po.getProduct());
			pstm.setDouble(2, po.getUnit_price());
			pstm.setDouble(3, po.getQty());
			pstm.setString(4, po.getUoM());
			pstm.setString(5, po.getDelivery_address());
			pstm.setString(6, po.getPhone());
			pstm.setString(7, po.getMail());
			pstm.setString(8, po.getPO_status());
			pstm.setInt(9, po.getId());

			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public PO getOrder(int id){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		PO po = null;
				
          try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETORDER);
			pstm.setInt(1, id);
			pstm.execute();
			rs = pstm.getResultSet();

			if(rs.next()){ 
				po = new PO();
				
				po.setId(rs.getInt("id"));
				po.setProduct(rs.getString("product"));
				po.setUnit_price(rs.getDouble("unit_price"));
				po.setQty(rs.getDouble("qty"));	
				po.setUoM(rs.getString("UoM"));				
				po.setDelivery_address(rs.getString("delivery_address"));			
				po.setPhone(rs.getString("phone"));			
				po.setMail(rs.getString("mail"));			
				po.setPO_status(rs.getString("PO_status"));			
				
				return po;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
}
