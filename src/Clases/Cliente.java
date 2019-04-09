
package Clases;


public class Cliente {
    int id;
    String rutcliente, nombrecliente, apellidocliente;
    
    public Cliente(){
        this.id = 0;
        this.rutcliente = "";
        this.nombrecliente = "";
        this.apellidocliente = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRutcliente() {
        return rutcliente;
    }

    public void setRutcliente(String rutcliente) {
        this.rutcliente = rutcliente;
    }

    public String getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    public String getApellidocliente() {
        return apellidocliente;
    }

    public void setApellidocliente(String apellidocliente) {
        this.apellidocliente = apellidocliente;
    }
    
    
}
