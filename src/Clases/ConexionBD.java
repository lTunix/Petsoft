package Clases;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ConexionBD 
{
    private String parametros;
    private Connection conexion;
    private Statement sentencia;
    private ResultSet rs;
    public static String tipo, nombre, apellido;
    
    public ConexionBD(){
        try{
            parametros = "jdbc:mysql://localhost:3306/petsoft";
            Class.forName("com.mysql.jdbc.Driver"); 
            conexion = DriverManager.getConnection(parametros, "root", "12345"); 
            System.out.println("Conexion exitosa");     
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error en la conexion a MySQL: " + e, "Error de conexion", 0);
            System.exit(0);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en la conexion a MySQL: " + e, "Error de conexion", 0);
            System.exit(0);
        }
    }
    
    public boolean ValidarUsuario(String r, String c) {
        
        
          
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("select * from personal where rutpersonal='"+r+"' and contrasena='"+c+"'");
            if(rs.next()){
                Personal p = new Personal();
                nombre = rs.getString("nombrepersonal");
                apellido = rs.getString("apellidopersonal");
                tipo = rs.getString("tipopersonal");                
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }        
    }
    
    public void GuardarCliente(Cliente c){
        String sql = "insert into cliente (idcliente, rutcliente, nombrecliente, apellidocliente, fechacreacioncliente) values ('"+c.getId()+"', '"+c.getRutcliente()+"', '"+c.getNombrecliente()+"', '"+c.getApellidocliente()+"', curdate())";
        try{
            sentencia = conexion.createStatement();
            sentencia.execute(sql);
            JOptionPane.showMessageDialog(null, "Cliente guardado exitosamente", "Guardado", 1);
            sentencia.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error en el ingreso del cliente" + e, "error", 0);
            System.out.println(e);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en el ingreso del cliente" + e, "error", 0);
            System.exit(0);
        }
    }
    
    public DefaultTableModel MostrarTablaCliente() throws SQLException{
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery("select * from cliente;");
        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable(modelo);
        modelo.addColumn("ID Cliente");
        modelo.addColumn("Rut Cliente");
        modelo.addColumn("Nombre Cliente");
        modelo.addColumn("Apellido Cliente");
        modelo.addColumn("Fecha Registro");
        while (rs.next()){
            Object [] fila = new Object[5];
            for (int i=0;i<5;i++)
            fila[i] = rs.getObject(i+1);
            modelo.addRow(fila); 
        }
        return modelo;
    }
    
    public void EliminarCliente(Cliente c) throws SQLException{
            String sql = "delete from cliente where rutcliente = '"+c.getRutcliente()+"'";
            try{
            sentencia = conexion.createStatement();
            sentencia.execute(sql);
            JOptionPane.showMessageDialog(null, "Cliente borrado exitosamente", "Borrado", 1);
            sentencia.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error en la eliminacion del Cliente" + e, "error", 0);
            System.out.println(e);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en la eliminacion del Cliente" + e, "error", 0);
            System.exit(0);
        }
    }
    
    public DefaultTableModel BuscarCliente(Cliente c) throws SQLException{
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery("select * from cliente where rutcliente = '"+c.getRutcliente()+"';");
        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable(modelo);
        modelo.addColumn("ID Cliente");
        modelo.addColumn("Rut Cliente");
        modelo.addColumn("Nombre Cliente");
        modelo.addColumn("Apellido Cliente");
        modelo.addColumn("Fecha Registro");
        while (rs.next()){
            Object [] fila = new Object[5];
            for (int i=0;i<5;i++)
            fila[i] = rs.getObject(i+1);
            modelo.addRow(fila); 
        }
            return modelo;
    }
    
    public void ActualizarCliente(Cliente c){
        String sql = "update cliente set rutcliente = '"+c.getRutcliente()+"', nombrecliente = '"+c.getNombrecliente()+"', apellidocliente = '"+c.getApellidocliente()+"' where idcliente = "+c.getId()+";";
        try{
            sentencia = conexion.createStatement();
            sentencia.execute(sql);
            JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente", "Guardado", 1);
            sentencia.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error en la actualizacion del Cliente" + e, "error", 0);
            System.out.println(e);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en la actualizacion del Cliente" + e, "error", 0);
            System.exit(0);
        }
    }
}