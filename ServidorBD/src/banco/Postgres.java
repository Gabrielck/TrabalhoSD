/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import bancoInterface.Frase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabrielkr
 */
public class Postgres {

    private static Connection con = null;

    private static void abrirConexao() throws Exception {
        if (con != null) {
            return;
        }
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sd", "postgres", "!(@!¨*@%%@%%");
        con.setAutoCommit(true);
    }

    public static PreparedStatement getSatement(String sql) throws Exception {
        abrirConexao();
        return con.prepareStatement(sql);
    }

    public static int executeQueryUpdate(PreparedStatement stmt) throws Exception {
        abrirConexao();
        int n = stmt.executeUpdate();
        stmt.close();
        return n;
    }

    public static Frase[] executeQueryFrase(PreparedStatement stmt) throws Exception {
        abrirConexao();
        List<Frase> lista = new ArrayList();

        ResultSet rs = stmt.executeQuery();
        int rows = 0;
        while (rs.next()) {
            rows++;
            int codigo = rs.getInt("codigo");
            String frase = rs.getString("frase");
            int tipo = rs.getInt("tipo");
            lista.add(new Frase(codigo,frase,tipo));
        }
        stmt.close();

        Frase[] fr = new Frase[rows];
        int pos = 0;
        for (Frase atual : lista) {
            fr[pos++] = atual;
        }
        return fr;
    }

}
