package com.example.mobile;

import java.util.Date;

public class RapportVisite {
    private String rapNum;
    private String rapMatricule;
    private String praNum;
    private String rapBilan;
    private String rapMotif;
    private String rapCoefConf;
    private String rapStatut;
    private String rapDateVisite;
    private String rapDateRapport;


    public RapportVisite(){
        super();
    }

    public RapportVisite(String rapNum, String rapMatricule, String praNum, String rapBilan, String rapMotif, String rapCoefConf, String rapStatut, String rapDateVisite, String rapDateRapport) {
        this.rapNum = rapNum;
        this.rapMatricule = rapMatricule;
        this.praNum = praNum;
        this.rapBilan = rapBilan;
        this.rapMotif = rapMotif;
        this.rapCoefConf = rapCoefConf;
        this.rapStatut = rapStatut;
        this.rapDateVisite = rapDateVisite;
        this.rapDateRapport = rapDateRapport;
    }


    public String getRapNum() {
        return rapNum;
    }

    public void setRapNum(String rapNum) {
        this.rapNum = rapNum;
    }

    public String getRapMatricule() {
        return rapMatricule;
    }

    public String getRapBilan() {
        return rapBilan;
    }

    public void setRapBilan(String rapBilan) {
        this.rapBilan = rapBilan;
    }

    public String getPraNum() {
        return praNum;
    }

    public void setPraNum(String praNum) {
        this.praNum = praNum;
    }

    public String getRapMotif() {
        return rapMotif;
    }

    public void setRapMotif(String rapMotif) {
        this.rapMotif = rapMotif;
    }

    public String getRapCoefConf() {
        return rapCoefConf;
    }

    public void setRapCoefConf(String rapCoefConf) {
        this.rapCoefConf = rapCoefConf;
    }

    public String getRapStatut() {
        return rapStatut;
    }

    public void setRapStatut(String rapStatut) {
        this.rapStatut = rapStatut;
    }

    public void setRapMatricule(String visMatricule) {
        this.rapMatricule = visMatricule;
    }

    public String getRapDateVisite() {
        return rapDateVisite;
    }

    public void setRapDateVisite(String rapDateVisite) {
        this.rapDateVisite = rapDateVisite;
    }

    public String getRapDateRapport() {
        return rapDateRapport;
    }

    public void setRapDateRaport(String rapDateRapport) {
        this.rapDateRapport = rapDateRapport;
    }

    @Override
    public String toString() {
        return "RapportVisite{" +
                "rapNum=" + rapNum +
                ", visMatricule='" + rapMatricule + '\'' +
                ", praNum=" + praNum +
                ", rapBilan='" + rapBilan + '\'' +
                ", rapMotif='" + rapMotif + '\'' +
                ", rapCoefConf=" + rapCoefConf +
                ", rapStatut=" + rapStatut +
                ", rapDateVisite=" + rapDateVisite +
                ", rapDateRapport=" + rapDateRapport +
                '}';
    }



}
