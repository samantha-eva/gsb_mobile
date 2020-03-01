package com.example.mobile;



public class Visiteur {
    private String visMatricule;
    private String visNom;
    private String visPrenom;
    private String visAdresse;
    private String visCp;
    private String visVille;
    private String visLogin;
    private String visMdp;

    public Visiteur() {
        super();
    }

    public Visiteur(String visMatricule, String visNom, String visPrenom, String visAdresse,
                    String visCp, String visVille) {
        this.visMatricule = visMatricule;
        this.visNom = visNom;
        this.visPrenom = visPrenom;
        this.visAdresse = visAdresse;
        this.visCp = visCp;
        this.visVille = visVille;

        this.visMdp = "azerty";
    }

    public Visiteur(String visMatricule, String visNom, String visPrenom) {
        this.visMatricule = visMatricule;
        this.visNom = visNom;
        this.visPrenom = visPrenom;
        this.visLogin = (visPrenom.charAt(0) + "" + visNom).toLowerCase();
        this.visMdp = "azerty";
    }

    public String getVisMatricule() {
        return visMatricule;
    }

    public void setVisMatricule(String visMatricule) {
        this.visMatricule = visMatricule;
    }

    public String getVisNom() {
        return visNom;
    }

    public void setVisNom(String visNom) {
        this.visNom = visNom;
    }

    public String getVisPrenom() {
        return visPrenom;
    }

    public void setVisPrenom(String visPrenom) {
        this.visPrenom = visPrenom;
    }

    public String getVisAdresse() {
        return visAdresse;
    }

    public void setVisAdresse(String visAdresse) {
        this.visAdresse = visAdresse;
    }

    public String getVisCp() {
        return visCp;
    }

    public void setVisCp(String visCp) {
        this.visCp = visCp;
    }

    public String getVisVille() {
        return visVille;
    }

    public void setVisVille(String visVille) {
        this.visVille = visVille;
    }

    public String getVisLogin() {
        return visLogin;
    }

    public void setVisLogin(String visLogin) {
        this.visLogin = visLogin;
    }

    public void setVisLogin() {
        this.visLogin = (visPrenom.charAt(0) + "" + visNom).toLowerCase();
    }

    public String getVisMdp() {
        return visMdp;
    }

    public void setVisMdp(String visMdp) {
        this.visMdp = visMdp;
    }

    public void setVisMdp() {

        this.visMdp = "azerty";
    }

    @Override
    public String toString() {
        return "Visiteur{" +
                "visNom='" + visNom + '\'' +
                ", visPrenom='" + visPrenom + '\'' +
                '}';
    }
}
