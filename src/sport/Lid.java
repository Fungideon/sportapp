package sport;

import java.util.Date;

public class Lid {
	private String spelerscode, roepnaam,
		tussenvoegsels, achternaam, email;
	private int spelernummer;
	private Date geboortedatum;
	
	//lege constructor
	public Lid() {
		this.spelerscode = "";
		this.roepnaam = "";
		this.tussenvoegsels = "";
		this.achternaam = "";
		this.email = "";
		this.spelernummer = 0;
		this.geboortedatum = (Date) null;
		
	}
	
	 /**
     * constructor maakt lid-object
     * @param roepnaam
     * @param tussenvoegsels
     * @param achternaam
     * @param email
     * @param geboortedatum
     * @param spelernummer
     */
	public Lid (String roepnaam,
				String tussenvoegsels,
				String achternaam,
				String email,
				int spelernummer,
				Date geboortedatum) {
		this.roepnaam = roepnaam;
		this.tussenvoegsels = tussenvoegsels;
		this.achternaam = achternaam;
		this.email = email;
		this.spelerscode = email + geboortedatum + roepnaam;
		this.spelernummer = spelernummer;
		this.geboortedatum = geboortedatum;
	}
	
	/* getters en setters */
	
	public String getSpelerscode() {
        return spelerscode;
    }
    
    public void setSpelerscode(String spelerscode) {
        this.spelerscode = spelerscode;
    }
    
    public String getRoepnaam() {
        return roepnaam;
    }

    public void setRoepnaam(String roepnaam) {
        this.roepnaam = roepnaam;
    }

    public String getTussenvoegsels() {
        return tussenvoegsels;
    }

    public void setTussenvoegsels(String tussenvoegsels) {
        this.tussenvoegsels = tussenvoegsels;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }
    
    public int getSpelernummer() {
        return spelernummer;
    }

    public void setSpelernummer(int spelernummer) {
        this.spelernummer = spelernummer;
    }
    
    /**
     *@return samengevoegde naam
     *
     */
    public String getNaam() {
        String naam;
        if (tussenvoegsels.equals("")) {
            naam = roepnaam + " " + achternaam;
        } else {
            naam = roepnaam + " " + tussenvoegsels + " " + achternaam;
        }
        return naam;
    }
}