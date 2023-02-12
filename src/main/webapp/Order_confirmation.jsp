<%@ page import="org.example.model.PO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%
String msg=(String)request.getAttribute("msg");    
msg = msg!=null ? msg:"";

PO po =(PO)request.getAttribute("oc");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order confirmation</title>
</head>
<body>
	<h2>Potvrda porudzbine	</h2>
	<table align="center" width="80%" border="3" cellpadding="5">
		<!--  <caption>${requestScope.msg }</caption>-->
		<tr>
			
			<th>Naziv artikla</th>
			<th>Cena</th>
			<th>Kolicina</th>
			<th>Jed. mere</th>
			<th>Adresa za dostavu</th>
			<th>Kontakt telefon</th>
			<th>Mejl</th>
			<th>Status porudzbine</th>
		</tr>
		<tr>
			<td><%= po.getProduct() %></td>
			<td><%= po.getUnit_price() %></td>
			<td><%= po.getQty() %></td>
			<td><%= po.getUoM() %></td>
			<td><%= po.getDelivery_address() %></td>
			<td><%= po.getPhone() %></td>
			<td><%= po.getMail() %></td>
			<td><%= po.getPO_status() %></td>
		</tr>
		<!--  <tr>
			<td>${requestScope.student.ime }</td>
			<td>${requestScope.student.prezime }</td>
			<td>${requestScope.student.prosek }</td>
		</tr>-->
	
	</table>
	<%=msg %>
</body>
</html>