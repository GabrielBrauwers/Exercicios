/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Util;

import Classes.Configuracao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.Scanner;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.swing.JOptionPane;

public class Ldap {

    public static ArrayList listAlunos() throws Exception {
        int i, t;
        String strOUAlunos = null;
        Configuracao CF = new Configuracao();
        String server = null;
        String port = null;
        String username = null;
        String password = null;
        String ldapstr = null;
        String temp = null;
        String baseou = null;

        CF = acessoArquivo.le();
        server = (String) CF.getLdapserver();
        port = (String) CF.getLdapporta();
        username = (String) CF.getLdapuser();
        password = (String) CF.getLdappassword();
        ArrayList dados = new ArrayList();
        int cont = 0;
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        ldapstr = "ldap://" + server + ":" + port;
        props.put(Context.PROVIDER_URL, ldapstr);
        props.put(Context.SECURITY_PRINCIPAL, username);
        props.put(Context.SECURITY_CREDENTIALS, password);
        InitialDirContext context = new InitialDirContext(props);
        SearchControls ctrls = new SearchControls();
        ctrls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        Scanner scanner = new Scanner(CF.getListaOUAlunos());

        while (scanner.hasNextLine()) {
            strOUAlunos = scanner.nextLine();
            try {
                NamingEnumeration<javax.naming.directory.SearchResult> answers = context.search(strOUAlunos, "(objectClass=*)", ctrls);
                while (answers.hasMore()) {
                    javax.naming.directory.SearchResult result = answers.nextElement();
                    if (!(result.getName().isEmpty())) {
                        dados.add(result.getName());
                        cont++;
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro no processamento da OU " + strOUAlunos);
            }
        }

      //  JOptionPane.showMessageDialog(null, "total " + cont);

        for (i = 0; i < dados.size(); i++) {
            temp = dados.get(i).toString();
            t = (int) temp.length();
            if (t != 0) {
                temp = temp.substring(3, t);
                dados.set(i, temp);
            }
        }

        Collections.sort(dados);
        return dados;
    }

    public static ArrayList listProfessores() throws Exception {
        int i, t;
        String strOUProfes = null;
        Configuracao CF = new Configuracao();
        String server = null;
        String port = null;
        String username = null;
        String password = null;
        String ldapstr = null;
        String temp = null;

        CF = acessoArquivo.le();
        server = (String) CF.getLdapserver();
        port = (String) CF.getLdapporta();
        username = (String) CF.getLdapuser();
        password = (String) CF.getLdappassword();
        ArrayList dados = new ArrayList();
        int cont = -1;
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        ldapstr = "ldap://" + server + ":" + port;
        props.put(Context.PROVIDER_URL, ldapstr);
        props.put(Context.SECURITY_PRINCIPAL, username);
        props.put(Context.SECURITY_CREDENTIALS, password);
        InitialDirContext context = new InitialDirContext(props);
        SearchControls ctrls = new SearchControls();
        ctrls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        Scanner scanner = new Scanner(CF.getListaOUProfes());

        while (scanner.hasNextLine()) {
            strOUProfes = scanner.nextLine();
            NamingEnumeration<javax.naming.directory.SearchResult> answers = context.search(strOUProfes, "(objectClass=*)", ctrls);
            //NamingEnumeration<javax.naming.directory.SearchResult> answers = context.search("ou=professores,dc=mesquitateste,dc=com,dc=br", "(objectClass=*)", ctrls);
            while (answers.hasMore()) {
                javax.naming.directory.SearchResult result = answers.nextElement();
                dados.add(result.getName());
                cont++;
            }
        }
        //    JOptionPane.showMessageDialog(null, "total " + cont);
            for (i = 0; i < dados.size(); i++) {
                temp = dados.get(i).toString();
                t = (int) temp.length();
                if (t != 0) {
                    temp = temp.substring(3, t);
                    dados.set(i, temp);
                }
            }

            Collections.sort(dados);
            return dados;
        }

    }
