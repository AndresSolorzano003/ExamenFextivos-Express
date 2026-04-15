public class Calendario {
    private LocalDate fecha;
    private Integer idTipo; 
    private String descripcion;

    public Calendario(LocalDate fecha, Integer idTipo, String descripcion) {
        this.fecha = fecha;
        this.idTipo = idTipo;
        this.descripcion = descripcion;
    }
    // Getters...
}