/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hi
 */
public class Employee {
    int id,Salary,deptid;
    String name;

    public Employee(int Salary, int deptid, String name) {
        this.Salary = Salary;
        this.deptid = deptid;
        this.name = name;
    }

    public Employee(int Salary, String name) {
        this.Salary = Salary;
        this.name = name;
    }

    

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int Salary) {
        this.Salary = Salary;
    }

    public int getDeptid() {
        return deptid;
    }

    public void setDeptid(int deptid) {
        this.deptid = deptid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
