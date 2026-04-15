@RestController
@RequestMapping("/api/calendario")
public class CalendarioController {
    private final ValidarFechaUseCase useCase;

    public CalendarioController(ValidarFechaUseCase useCase) {
        this.useCase = useCase;
    }

   
    @GetMapping("/verificar/{a}/{m}/{d}")
    public ResponseEntity<Calendario> verificar(@PathVariable int a, @PathVariable int m, @PathVariable int d) {
        return ResponseEntity.ok(useCase.ejecutar(a, m, d));
    }


    @GetMapping("/generar/{año}")
    public ResponseEntity<Boolean> generarAño(@PathVariable int año) {
        return ResponseEntity.ok(true); [cite: 532, 551]
    }


    @GetMapping("/listar/{año}")
    public ResponseEntity<List<Calendario>> listar(@PathVariable int año) {
        // Retorna la lista desde el repositorio de Postgres
        return ResponseEntity.ok(useCase.obtenerTodoElAño(año));
    }
}