package com.example.universidad.entidades;

public class Estudiante {

    private String nombre;
    private String nivel;
    private String asignatura;
    private double notaParcialUno;
    private double notaParcialDos;
    private double promedio;


    public Estudiante(){}

    public Estudiante(String nombre, String nivel, String asignatura, double notaParcialUno, double notaParcialDos, double promedio) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.asignatura = asignatura;
        this.notaParcialUno = notaParcialUno;
        this.notaParcialDos = notaParcialDos;
        this.promedio = promedio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public double getNotaParcialUno() {
        return notaParcialUno;
    }

    public void setNotaParcialUno(double notaParcialUno) {
        this.notaParcialUno = notaParcialUno;
    }

    public double getNotaParcialDos() {
        return notaParcialDos;
    }

    public void setNotaParcialDos(double notaParcialDos) {
        this.notaParcialDos = notaParcialDos;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }


    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", nivel='" + nivel + '\'' +
                ", asignatura='" + asignatura + '\'' +
                ", notaParcialUno=" + notaParcialUno +
                ", notaParcialDos=" + notaParcialDos +
                ", promedio=" + promedio +
                '}';
    }
}
