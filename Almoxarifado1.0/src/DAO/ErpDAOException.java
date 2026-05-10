/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author 276642
 */
public class ErpDAOException extends Exception{
 public ErpDAOException(){

    }

    public ErpDAOException(String arg){
        super(arg);
        System.out.println("Erro"+ arg);
    }

    public ErpDAOException(Throwable arg){
        super(arg);
        System.out.println("Erro" + arg);
    }

    public ErpDAOException(String arg, Throwable arg1){
        super(arg, arg1);
        System.out.println("Erro" + arg);
    }
}
