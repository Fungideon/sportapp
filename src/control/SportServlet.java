package control;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sport.Lid;
import sport.Team;
import sportIO.SportIO;

@SuppressWarnings("serial")
public class SportServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
		SportIO io = new SportIO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		if (req.getParameter("verzend_nieuw_lid_knop") != null){
			try{
				String roepnaam = req.getParameter("roepnaam");
				String tussenvoegsels = req.getParameter("tussenvoegsels");
				String achternaam = req.getParameter("achternaam");
				String email = req.getParameter("email");
				int spelernummer = Integer.parseInt(req.getParameter("spelernummer"));
				date = sdf.parse(req.getParameter("geboortedatum"));
				Lid lid = new Lid(roepnaam, tussenvoegsels, achternaam, email, spelernummer, date);
				io.voegLidToe(lid);
				resp.sendRedirect("/sport");
			}catch(NumberFormatException | ParseException e){
				resp.sendRedirect("/index.jsp&err=1");
			}
		}else if (req.getParameter("haal_lid") != null) {
	        String spelerscode = req.getParameter("spelerscode");
	        Lid lid = io.getLid(spelerscode);
	        ArrayList<Team> teams = io.getSpelerteams(lid);
	        req.setAttribute("teams", teams);
	        req.setAttribute("lid", lid);
	    	RequestDispatcher disp = req.getRequestDispatcher("/lid.jsp");
	        disp.forward(req, resp);
		}else if (req.getParameter("wijzig_lid_knop") != null) {
			try{
				String roepnaam = req.getParameter("roepnaam");
				String tussenvoegsels = req.getParameter("tussenvoegsels");
				String achternaam = req.getParameter("achternaam");
				String email = req.getParameter("email");
				int spelernummer = Integer.parseInt(req.getParameter("spelernummer"));
				date = sdf.parse(req.getParameter("geboortedatum"));
				Lid lid = new Lid(roepnaam, tussenvoegsels, achternaam, email, spelernummer, date);
				lid.setSpelerscode(req.getParameter("spelerscode"));
				io.wijzigLid(lid);
			}catch(NumberFormatException | ParseException e){
		    	RequestDispatcher disp = req.getRequestDispatcher("/lid.jsp");
		        disp.forward(req, resp);
			}
			resp.sendRedirect("/sport");
		}else if (req.getParameter("verwijder_lid_knop") != null){
			String spelerscode = req.getParameter("spelerscode");
			io.verwijderLid(spelerscode);
			resp.sendRedirect("/sport");
		}
		else if(req.getParameter("verzend_nieuw_team_knop") != null){
			try{
				String teamcode = req.getParameter("teamcode");
				String teamnaam = req.getParameter("teamnaam");
				Team team = new Team(teamcode, teamnaam);
				io.voegTeamToe(team);
				resp.sendRedirect("/sport");
			}catch(NumberFormatException e){
				resp.sendRedirect("/index.jsp&err=1");
			}
		}else if (req.getParameter("haal_team") != null) {
	        String teamcode = req.getParameter("teamcode");
	        Team team = io.getTeam(teamcode);
	        ArrayList<Lid> teamspelers = io.getTeamspelers(team);
	        ArrayList<Lid> leden = io.getAlleLeden();
	        req.setAttribute("leden", leden);
	        req.setAttribute("teamspelers", teamspelers);
	        req.setAttribute("team", team);
	    	RequestDispatcher disp = req.getRequestDispatcher("/team.jsp");
	        disp.forward(req, resp);
		}else if (req.getParameter("wijzig_team_knop") != null) {
			try{
				String teamcode = req.getParameter("teamcode");
				String teamnaam = req.getParameter("teamnaam");
				Team team = new Team(teamcode, teamnaam);
				io.wijzigTeam(team);
			}catch(NumberFormatException e){
		    	RequestDispatcher disp = req.getRequestDispatcher("/team.jsp");
		        disp.forward(req, resp);
			}
			resp.sendRedirect("/sport");
		}else if (req.getParameter("verwijder_team_knop") != null){
			String teamcode = req.getParameter("teamcode");
			io.verwijderTeam(teamcode);
			resp.sendRedirect("/sport");
		}else if (req.getParameter("voeg_lid_toe_aan_team_knop") != null){
			String spelerscode = req.getParameter("spelerscode");
			String teamcode = req.getParameter("teamcode");
			Lid lid = io.getLid(spelerscode);
			Team team = io.getTeam(teamcode);
			io.setTeamspeler(team, lid);
			resp.sendRedirect("/sport?haal_team=&teamcode=" + teamcode);
		}else if (req.getParameter("verwijder_lid_van_team_knop") != null){
			String spelerscode = req.getParameter("spelerscode");
			String teamcode = req.getParameter("teamcode");
			Lid lid = io.getLid(spelerscode);
			Team team = io.getTeam(teamcode);
			io.verwijderTeamspeler(team, lid);
			resp.sendRedirect("/sport?haal_team=&teamcode=" + teamcode);
		}
		else{
			ArrayList<Lid> leden = io.getAlleLeden();
			ArrayList<Team> teams = io.getAlleTeams();
			req.setAttribute("teams", teams);
			req.setAttribute("leden", leden);
			RequestDispatcher disp = req.getRequestDispatcher("/overzicht_leden.jsp");
			disp.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
