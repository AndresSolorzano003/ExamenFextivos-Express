@Service
public class ValidarFechaUseCase {
    private final FestivoService festivoService; // Inyectado desde Infrastructure

    public Calendario ejecutar(int a, int m, int d) {
        LocalDate fecha = LocalDate.of(a, m, d);
        // 1. Preguntamos a tu API de ExpressJS [cite: 18]
        String respuesta = festivoService.verificarFestivo(a, m, d);

        if (respuesta.equals("Es Festivo")) {
            return new Calendario(fecha, 3, "Día festivo");
        } 
        
        // 2. Si no es festivo, miramos si es fin de semana
        DayOfWeek dia = fecha.getDayOfWeek();
        if (dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY) {
            return new Calendario(fecha, 2, "Fin de Semana");
        }

        // 3. Por defecto es laboral
        return new Calendario(fecha, 1, "Día laboral");
    }
}