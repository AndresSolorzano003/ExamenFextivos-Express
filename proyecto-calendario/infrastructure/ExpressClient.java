@Component
public class ExpressClient implements FestivoService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String verificarFestivo(int a, int m, int d) {
        // Tu API de Express corriendo en el 8080 [cite: 18, 51]
        String url = "http://localhost:8080/api/festivos/verificar/" + a + "/" + m + "/" + d;
        return restTemplate.getForObject(url, String.class);
    }
}