<%@ page import="sport.*" %>
<%@ page import="java.util.ArrayList" %>
<% 
if (request.getAttribute("team") == null) {
    response.sendRedirect("/sport");
} else {  
     Team team = (Team) request.getAttribute("team");
     ArrayList<Lid> teamspelers = (ArrayList<Lid>) request.getAttribute("teamspelers");
     ArrayList<Lid> leden = (ArrayList<Lid>) request.getAttribute("leden");
%> 
    
<form action="/sport"  method="get">
    <input type="text" 
       name="teamcode" 
       value="<%= team.getTeamcode() %>">(teamcode)<br>
    <input type="text" 
       name="teamnaam" 
       value="<%= team.getTeamnaam() %>">(teamnaam)<br>
    <input type="submit" 
       name="wijzig_team_knop" 
       value="wijzig">
    <input type="submit" 
       name="verwijder_team_knop" 
       value="verwijder">
</form>
<br>
<h3>Leden:</h3>
<%  for (Lid lid: teamspelers) { %>
        <%= lid.getNaam() %>
<%  }
%>
<br>
<h3>Voeg Lid toe aan team</h3>
<form action="/sport"  method="get">
	<select name="spelerscode">
		<% for (Lid lid: leden) {
		%>
		<option value="<%=lid.getSpelerscode() %>"><%=lid.getNaam() %></option>
		<% } %>
	</select>
	<input type="hidden"
		name="teamcode"
		value="<%=team.getTeamcode() %>">
    <input type="submit" 
       name="voeg_lid_toe_aan_team_knop" 
       value="voegtoe">
</form>
<br>
<h3>Verwijder lid van team</h3>
<form action="/sport"  method="get">
	<select name="spelerscode">
		<% for (Lid lid: teamspelers) {
		%>
		<option value="<%=lid.getSpelerscode() %>"><%=lid.getNaam() %></option>
		<% } %>
	</select>
	<input type="hidden"
		name="teamcode"
		value="<%=team.getTeamcode() %>">
    <input type="submit" 
       name="verwijder_lid_van_team_knop" 
       value="verwijder">
</form>
<a href="/sport">Terug naar overzicht</a>
<% } %>