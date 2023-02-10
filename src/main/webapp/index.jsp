<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PO</title>
</head>
<body>
	<h2>Kreiranje porudzbenice</h2>
	<form action="webshop" method="post">
		Proizvod:<br>
		<input type="text" name="product"><br>
		Jedinicna cena:<br>
		<input type="text" name="unit_price"><br>
		Kolicina:<br>
		<input type="text" name="qty"><br>
		
		<label for="UoM">Jedinica mere:</label>

			<select name="UoM" id="UoM">
			
  				<option value="pc(s)">pc(s)</option>
 				 <option value="kg">kg</option>
 				 <option value="m">m</option>
 			</select><br>
		
		Adresa za isporuku:<br>
		<input type="text" name="delivery_address"><br>
		Telefon:<br>
		<input type="text" name="phone"><br>
		Mejl adresa:<br>
		<input type="text" name="mail"><br>
		<label for="PO_status">Status porudzbenice:</label>

			<select name="PO_status" id="PO_status">
			
  				<option value="ORDERED">NARUCENO</option>
 				 <option value="SENT">POSLATO</option>
 				 <option value="DELIVERED">ISPORUCENO</option>
 				 <option value="CLOSED">ZATVORENO</option>
 			</select><br>
		<input type="submit" name="action" value="Kreiraj porudzbenicu">
	</form>	
	<br>
	<form action="webshop" method="get">
		ID porudzbenice:<br>
		<input type="text" name="id"><br>
		<input type="submit" name="action" value="Prikazi">
	</form>
	<br>
	<form action="webshop" method="delete">
		ID porudzbenice:<br>
		<input type="text" name="id"><br>
		<input type="submit" name="action" value="Izbrisi">
	</form>
	<br>
	<form action="webshop" method="put">
		ID porudzbenice:<br>
		<input type="text" name="id"><br>
		<input type="submit" name="action" value="Azuriraj">
	</form>
</body>
</html>