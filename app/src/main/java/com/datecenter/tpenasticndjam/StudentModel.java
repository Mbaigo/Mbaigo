package com.datecenter.tpenasticndjam;

import java.util.Date;

public class StudentModel {
    private int id;
    private String nom;
    private String prenom;
    private String dateNaiss;
    private boolean isNoraml;

    public StudentModel(int id, String nom, String prenom, String dateNaiss, boolean isNoraml) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.isNoraml = isNoraml;
    }

    public StudentModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(String dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public boolean isNoraml() {
        return isNoraml;
    }

    public void setNoraml(boolean noraml) {
        isNoraml = noraml;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaiss=" + dateNaiss +
                ", isNoraml=" + isNoraml +
                '}';
    }
}
