package dev.liambloom.softwareEngineering.chapter9.lawFirm;

public class Client2
{

    public static void main(String[] args)
    {
        Lawyer leia = new Lawyer();
        System.out.print("Lawyer: ");
        System.out.print(leia.getHours() + ", ");
        System.out.printf("$%.2f, ", leia.getSalary());
        System.out.print(leia.getVacationDays() + ", ");
        System.out.println(leia.getVacationForm());
        leia.sue();
        System.out.println(" -------------------------------- \n\n");

        Secretary sally = new Secretary();
        System.out.print("Secretary: ");
        System.out.print(sally.getHours() + ", ");
        System.out.printf("$%.2f, ", sally.getSalary());
        System.out.print(sally.getVacationDays() + ", ");
        System.out.println(sally.getVacationForm());
        sally.takeDictation("Sally is taking dictation!");
        System.out.println(" -------------------------------- \n\n");

        LegalSecretary lucy = new LegalSecretary();
        System.out.print("Legal Secretary: ");
        System.out.print(lucy.getHours() + ", ");
        System.out.printf("$%.2f, ", lucy.getSalary());
        System.out.print(lucy.getVacationDays() + ", ");
        System.out.println(lucy.getVacationForm());
        lucy.takeDictation("Lucy is taking dictation!");
        lucy.fileLegalBriefs();
        System.out.println(" -------------------------------- \n\n");

        // BJP Section 9.1
        HarvardLawyer harry = new HarvardLawyer();
        System.out.print("Harvard Lawyer: ");
        System.out.print(harry.getHours() + ", ");
        System.out.printf("$%.2f, ", harry.getSalary());
        System.out.print(harry.getVacationDays() + ", ");
        System.out.println(harry.getVacationForm());
        harry.sue();
        System.out.println(" -------------------------------- \n\n");

        Marketer mike = new Marketer();
        System.out.print("Marketer: ");
        System.out.print(mike.getHours() + ", ");
        System.out.printf("$%.2f, ", mike.getSalary());
        System.out.print(mike.getVacationDays() + ", ");
        System.out.println(mike.getVacationForm());
        mike.advertise();
        System.out.println(" -------------------------------- \n\n");

        Janitor jay = new Janitor();
        System.out.print("Janitor: ");
        System.out.print(jay.getHours() + ", ");
        System.out.printf("$%.2f, ", jay.getSalary());
        System.out.print(jay.getVacationDays() + ", ");
        System.out.println(jay.getVacationForm());
        jay.clean();
        System.out.println(" -------------------------------- \n\n");



        // BJP Section 9.2
        //"True" resulting test data
        System.out.println("\n\n >>>>>>>>>>>>>>>> TRUE equals() via getSalary() <<<<<<<<<<<<<< ");
        Lawyer larry = new Lawyer();
        Secretary suzy = new Secretary();
        System.out.println("leia.equals(larry) = " + leia.equals(larry));
        System.out.println("sally.equals(suzy) = " + sally.equals(suzy));
        System.out.println("mike.equals(jay) = " + mike.equals(jay));

        // "False" resulting test data
        System.out.println("\n\n >>>>>>>>>>>>>>>> FALSE equals() via getSalary() <<<<<<<<<<<<<< ");
        System.out.println("larry.equals(harry)= " + larry.equals(harry));
        Secretary sara = new Secretary();
        System.out.println("sally.equals(sara) = " + sally.equals(sara));
        Janitor jim = new Janitor();
        System.out.println("jim.equals(jay) = " + jim.equals(jay));

    } // main
} // Employee_CLIENT_Chap9_SECOND_EXAMPLE_WithEquals
