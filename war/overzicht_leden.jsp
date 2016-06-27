<%@ page import="sport.*" %>
<%@ page import="java.util.ArrayList" %>

<%
if (request.getAttribute("leden") == null) {
    response.sendRedirect("/sport");
} else { 
     ArrayList<Lid> leden = (ArrayList<Lid>) request.getAttribute("leden");
     ArrayList<Team> teams = (ArrayList<Team>) request.getAttribute("teams");
%>

<h1>Ledenoverzicht</h1>
<%  for (Lid lid: leden) { %>
        <%= lid.getNaam() %>
        <a href="/sport?haal_lid=&spelerscode=<%= lid.getSpelerscode() %>">naar lid</a><br>
<%  }
%>
<h2>Teamoverzicht</h2>
<%  for (Team team: teams) { %>
        <%= team.getTeamnaam() %>
        <a href="/sport?haal_team=&teamcode=<%= team.getTeamcode() %>">naar team</a><br>
<%  }
%>
<%
}   %>
<br>
<a href="index.jsp">lid/team toevoegen</a>