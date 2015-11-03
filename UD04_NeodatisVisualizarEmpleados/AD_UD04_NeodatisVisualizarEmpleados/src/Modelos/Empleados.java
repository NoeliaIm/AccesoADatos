package Modelos;

//import java.sql.Date;

public class Empleados {

    private int empNo;
    private String apellido;
    private String oficio;
    private Empleados dir;
    private java.sql.Date fechaAlt;
    private float salario;
    private float comision;
    private Departamentos dept;

    public Empleados() {
    }

    public Empleados(int empNo, String apellido,
            String oficio, Empleados dir, java.sql.Date fechaAlt, float salario,
            float comision, Departamentos dept) {
        this.empNo = empNo;
        this.dept = dept;
        this.apellido = apellido;
        this.oficio = oficio;
        this.dir = dir;
        this.fechaAlt = fechaAlt;
        this.salario = salario;
        this.comision = comision;
    }

    public Empleados(int empno) {
        this.empNo = empno;
        this.dept = null;
        this.apellido = "";
        this.oficio = "";
        this.dir = this;
        this.fechaAlt = null;
        this.salario = 0;
        this.comision = 0;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public Empleados getDir() {
        return dir;
    }

    public void setDir(Empleados dir) {
        this.dir = dir;
    }

    public java.sql.Date getFechaAlt() {
        return fechaAlt;
    }

    public void setFechaAlt(java.sql.Date fechaAlt) {
        this.fechaAlt = fechaAlt;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }

    public Departamentos getDept() {
        return dept;
    }

    public void setDept(Departamentos dept) {
        this.dept = dept;
    }

    public String toString() {
        return apellido;
    }
}
