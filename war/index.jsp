<h3>Voeg lid toe</h3>
<form action="/sport" methode="get">
	Roepnaam : <input type="text" name="roepnaam"><br>
	Tussenvoegsels : <input type="text" name="tussenvoegsels"><br>
	Achternaam : <input type="text" name="achternaam"><br>
	e-mail : <input type="text" name="email"><br>
	Geboortedatum (dag-maand-jaar): <input type="text" name="geboortedatum"><br>
	Spelernummer : <input type="number" name="spelernummer"><br>
	<input type="submit" name="verzend_nieuw_lid_knop" value="verstuur">
</form>
<%
	if(request.getAttribute("err") != null){
		%>
		<h3>Er is iets fout gegaan controlleer de gegevens en probeer opnieuw.</h3>
		<%
	}
%>
<h3>Voeg team toe</h3>
<form action="/sport" methode="get">
	Teamncode : <input type="text" name="teamcode"><br>
	Teamnaam : <input type="text" name="teamnaam"><br>
	<input type="submit" name="verzend_nieuw_team_knop" value="verstuur">
</form>