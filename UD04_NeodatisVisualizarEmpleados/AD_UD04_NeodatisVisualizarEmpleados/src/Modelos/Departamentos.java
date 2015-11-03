package Modelos;

public class Departamentos {

    private int deptNo;
    private String dnombre;
    private String loc;

    public Departamentos() {
    }

    public Departamentos(int deptno, String dnombre, String loc) {
        this.deptNo = deptno;
        this.dnombre = dnombre;
        this.loc = loc;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public String getDnombre() {
        return dnombre;
    }

    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String toString() {
        return dnombre;
    }
}
