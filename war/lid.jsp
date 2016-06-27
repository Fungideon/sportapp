<%@ page import="sport.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<% 
if (request.getAttribute("lid") == null) {
    response.sendRedirect("/sport");
} else {  
     Lid lid = (Lid) request.getAttribute("lid");
     ArrayList<Team> teams = (ArrayList<Team>) request.getAttribute("teams");
     SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
%> 
<h3>Bewerk Lid</h3>
<form action="/sport"  method="get">
    <input type="text" 
       name="roepnaam" 
       value="<%= lid.getRoepnaam() %>">(roepnaam)<br>
    <input type="text" 
       name="tussenvoegsels" 
       value="<%= lid.getTussenvoegsels() %>">(tussenvoegsels)<br>
    <input type="text" 
       name="achternaam" 
       value="<%= lid.getAchternaam() %>">(achternaam)<br>
    <input type="text" 
       name="email" 
       value="<%= lid.getEmail() %>">(email)<br>
    <input type="text" 
       name="geboortedatum" 
       value="<%= sdf.format(lid.getGeboortedatum()) %>">(geboortedatum)<br>
       <input type="number" 
       name="spelernummer" 
       value="<%= lid.getSpelernummer() %>">(spelernummer)<br>
    <input type="hidden" 
       name="spelerscode" 
       value="<%= lid.getSpelerscode() %>">
    <input type="submit" 
       name="wijzig_lid_knop" 
       value="wijzig">
    <input type="submit" 
       name="verwijder_lid_knop" 
       value="verwijder">
</form>
<br>
<h3>Lid van Team('s):</h3>
<%  for (Team team: teams) { %>
        <%= team.getTeamnaam() %>
        <a href="/sport?haal_team=&teamcode=<%= team.getTeamcode() %>">naar team</a><br>
<%  }
%>
<% } %>
<br>
<a href="/sport">Terug naar overzicht</a>

