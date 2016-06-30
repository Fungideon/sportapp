package sportIO;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.*;


import java.util.ArrayList;
import java.util.Date;

import sport.Lid;
import sport.Team;

public class SportIO {
	private DatastoreService datastore;
	
	public SportIO(){
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	public void voegLidToe(Lid lid) {
		Entity ent = new Entity("Lid", lid.getSpelerscode());
		ent.setProperty("roepnaam", lid.getRoepnaam());
		ent.setProperty("tussenvoegsels", lid.getTussenvoegsels());
		ent.setProperty("achternaam", lid.getAchternaam());
		ent.setProperty("email", lid.getEmail());
		ent.setProperty("spelerscode", lid.getSpelerscode());
		ent.setProperty("spelernummer", lid.getSpelernummer());
		ent.setProperty("geboortedatum", lid.getGeboortedatum());
		datastore.put(ent);
	}
	
	public ArrayList<Lid> getAlleLeden() {
		ArrayList<Lid> leden = new ArrayList<Lid>();
		Query q = new Query("Lid");
		PreparedQuery resultaten = datastore.prepare(q);
		for (Entity ent: resultaten.asIterable()) {
			Lid lid = new Lid();
			lid.setRoepnaam((String) ent.getProperty("roepnaam"));
			lid.setTussenvoegsels((String) ent.getProperty("tussenvoegsels"));
			lid.setAchternaam((String) ent.getProperty("achternaam"));
			lid.setEmail((String) ent.getProperty("email"));
			lid.setSpelerscode((String) ent.getProperty("spelerscode"));
			lid.setSpelernummer((int) (long) ent.getProperty("spelernummer"));
			lid.setGeboortedatum((Date) ent.getProperty("geboortedatum"));
			leden.add(lid);
		}
		return leden;
	}
	
	public Lid getLid(String spelerscode) {
		Lid lid = null;
		Key k = KeyFactory.createKey("Lid", spelerscode);
		try {
			Entity ent = datastore.get(k);
			lid = new Lid();
			lid.setRoepnaam( (String) ent.getProperty("roepnaam"));
			lid.setTussenvoegsels((String) ent.getProperty("tussenvoegsels"));
			lid.setAchternaam((String) ent.getProperty("achternaam"));
			lid.setEmail((String) ent.getProperty("email"));
			lid.setSpelerscode((String) ent.getProperty("spelerscode"));
			lid.setSpelernummer((int) (long) ent.getProperty("spelernummer"));
			lid.setGeboortedatum((Date) ent.getProperty("geboortedatum"));
		} catch (EntityNotFoundException e){
			e.printStackTrace();
		}
		return lid;
	}
	
	public void verwijderLid(String spelerscode) {
	    Key k = KeyFactory.createKey("Lid", spelerscode );
	    datastore.delete(k);
	}

	//wijzigen == toevoegen
	public void wijzigLid(Lid lid) {
	    this.voegLidToe(lid);
	}
	
	public void voegTeamToe(Team team){
		Entity ent = new Entity("Team", team.getTeamcode());
		ent.setProperty("teamcode", team.getTeamcode());
		ent.setProperty("teamnaam", team.getTeamnaam());
		datastore.put(ent);
	}
	
	public ArrayList<Team> getAlleTeams() {
		ArrayList<Team> teams = new ArrayList<Team>();
		Query q = new Query("Team");
		PreparedQuery resultaten = datastore.prepare(q);
		for (Entity ent: resultaten.asIterable()) {
			Team team = new Team();
			team.setTeamcode((String) ent.getProperty("teamcode"));
			team.setTeamnaam((String) ent.getProperty("teamnaam"));
			teams.add(team);
		}
		return teams;
	}
	
	public Team getTeam(String teamcode) {
		Team team = null;
		Key k = KeyFactory.createKey("Team", teamcode);
		try {
			Entity ent = datastore.get(k);
			team = new Team();
			team.setTeamcode((String) ent.getProperty("teamcode"));
			team.setTeamnaam((String) ent.getProperty("teamnaam"));
		} catch (EntityNotFoundException e){
			e.printStackTrace();
		}
		return team;
	}
	
	public void verwijderTeam(String teamcode) {
	    Key k = KeyFactory.createKey("Team", teamcode );
	    datastore.delete(k);
	}
	
	public void wijzigTeam(Team team) {
	    this.voegTeamToe(team);
	}
	
	public void setTeamspeler(Team team, Lid lid){
		Entity ent = new Entity("Teamspeler", team.getTeamcode() + lid.getSpelerscode());
		ent.setProperty("teamcode", team.getTeamcode());
		ent.setProperty("spelerscode", lid.getSpelerscode());
		datastore.put(ent);
	}
	
	public void verwijderTeamspeler(Team team, Lid lid){
	   Key k = KeyFactory.createKey("Teamspeler", team.getTeamcode() + lid.getSpelerscode());
	   datastore.delete(k);
	}
	
	public ArrayList<Lid> getTeamspelers(Team team)  {
	    ArrayList<Lid> teamleden = new ArrayList<Lid>();
	    Filter teamcodeFilter =  new FilterPredicate(
	                       "teamcode", //naam van property in datastore 
	                       FilterOperator.EQUAL, //gelijk aan
	                       team.getTeamcode()); //attribuut van team
	                       
	    Query q = new Query("Teamspeler").setFilter(teamcodeFilter);
	    PreparedQuery pq = datastore.prepare(q);
			
	    for (Entity result: pq.asIterable()) {
	        Lid lid = null;
	        String spelerscode = (String) result.getProperty("spelerscode");
			lid = this.getLid(spelerscode);
			teamleden.add(lid);
	    }		
	    return teamleden;
	}
	
	public ArrayList<Team> getSpelerteams(Lid lid)  {
	    ArrayList<Team> teams = new ArrayList<Team>();
	    Filter spelercodeFilter =  new FilterPredicate(
	                       "spelerscode", //naam van property in datastore 
	                       FilterOperator.EQUAL, //gelijk aan
	                       lid.getSpelerscode()); //attribuut van team
	                       
	    Query q = new Query("Teamspeler").setFilter(spelercodeFilter);
	    PreparedQuery pq = datastore.prepare(q);
			
	    for (Entity result: pq.asIterable()) {
	        Team team = null;
	        String teamcode = (String) result.getProperty("teamcode");
			team = this.getTeam(teamcode);
			teams.add(team);
	    }		
	    return teams;
	}
	
	public void deleteSpelerFromAllTeams(String spelerscode){
		Filter spelercodeFilter =  new FilterPredicate(
                "spelerscode", //naam van property in datastore 
                FilterOperator.EQUAL, //gelijk aan
                spelerscode); //attribuut van team
		
		Query q = new Query("Teamspeler").setFilter(spelercodeFilter);
	    PreparedQuery pq = datastore.prepare(q);
	    
	    for (Entity result: pq.asIterable()) {
	    	String teamcode = (String) result.getProperty("teamcode");
	    	Key k = KeyFactory.createKey("Teamspeler", teamcode + spelerscode);
	    	datastore.delete(k);
	    }	
	}
	
	public void deleteAllSpelersFromTeam(String teamcode){
	    Filter teamcodeFilter =  new FilterPredicate(
                "teamcode", //naam van property in datastore 
                FilterOperator.EQUAL, //gelijk aan
                teamcode); //attribuut van team
		
		Query q = new Query("Teamspeler").setFilter(teamcodeFilter);
	    PreparedQuery pq = datastore.prepare(q);
	    
	    for (Entity result: pq.asIterable()) {
	    	String spelerscode = (String) result.getProperty("spelerscode");
	    	Key k = KeyFactory.createKey("Teamspeler", teamcode + spelerscode);
	    	datastore.delete(k);
	    }	
	}
}
