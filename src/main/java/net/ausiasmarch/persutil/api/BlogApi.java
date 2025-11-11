package net.ausiasmarch.persutil.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.persutil.entity.BlogEntity;
import net.ausiasmarch.persutil.service.AleatorioServices;
import net.ausiasmarch.persutil.service.BlogService;

@RestController
@RequestMapping("/blog")
public class BlogApi {


    //inyectar servicio AleatorioServices
    @Autowired
    AleatorioServices aleatorioServices;

    @Autowired
    BlogService oBlogService;

    @Autowired
    BlogEntity oBlogEntity;

  @GetMapping("/saludar")
    public ResponseEntity<String> saludar() {
        return new ResponseEntity<>("\"Hola desde el blog\"", HttpStatus.OK); 
        //con estas barritas conviertes el texto a JSON
    }

    @GetMapping("/saludar/buenosdias")
    public ResponseEntity<String> saludarPorLaMañana() {
        return new ResponseEntity<>("\"Hola, buenos días desde el blog\"", HttpStatus.OK); 
    }

    @GetMapping("/aleatorio") //endpoint
    public ResponseEntity<Integer> aleatorio() {
        int numeroAleatorio = (int)(Math.random()*100) +1;
        return ResponseEntity.ok(numeroAleatorio);
    }

    @GetMapping("/aleatorio/{min}/{max}") //endpoint
    public ResponseEntity<Integer> aleatorioEnRango(
        @PathVariable int min,
        @PathVariable int max) {
            int numeroAleatorio = (int) (Math.random() * (max - min + 1)) + min;
            return ResponseEntity.ok(numeroAleatorio);
    }

    @GetMapping("/aleatorio/service/{min}/{max}") //endpoint
    public ResponseEntity<Integer> aleatorioUsandoServiceEnRango(
        @PathVariable int min,
        @PathVariable int max) {
            int numeroAleatorio = aleatorioServices.GenerarNumeroAleatorioEnteroEnRango(min, max);
            return ResponseEntity.ok(numeroAleatorio);
    }

    @GetMapping("/palabras") //endpoint
    public ResponseEntity<String[]> obtenerPalabraAleatoria() {
        String[] frase = aleatorioServices.generarFraseAleatoria();
        oBlogEntity.setTitulo(frase[0] + " " + frase[1] + " " + frase[2] + " " + frase[3] + " " + frase[4]);
        frase = aleatorioServices.generarFraseAleatoria();
        oBlogEntity.setContenido(frase[0] + " " + frase[1] + " " + frase[2] + " " + frase[3] + " " + frase[4]);
        frase = aleatorioServices.generarFraseAleatoria();
        oBlogEntity.setEtiquetas(frase[0] + " " + frase[1] + " " + frase[2] + " " + frase[3] + " " + frase[4]);
        return new ResponseEntity<String[]>(frase, HttpStatus.OK);
    }

}
