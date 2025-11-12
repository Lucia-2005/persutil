package net.ausiasmarch.persutil.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ausiasmarch.persutil.entity.BlogEntity;
import net.ausiasmarch.persutil.repository.BlogRepository;

@Service
public class BlogService {

    @Autowired
    BlogRepository oBlogRepository;

    @Autowired
    AleatorioServices oAleatorioService;

    ArrayList<String> alFrases = new ArrayList<>();

    public BlogService() {

        alFrases.add("La vida es bella.");
        alFrases.add("El conocimiento es poder.");
        alFrases.add("La perseverancia es la clave del éxito.");
        alFrases.add("El tiempo es oro.");
        alFrases.add("La creatividad es la inteligencia divirtiéndose.");
        alFrases.add("Más vale tarde que nunca.");
        alFrases.add("El cambio es la única constante en la vida.");
        alFrases.add("La esperanza es lo último que se pierde.");
        alFrases.add("La unión hace la fuerza.");
        alFrases.add("El respeto es la base de toda relación.");
        alFrases.add("La comunicación es clave en cualquier relación.");
        alFrases.add("Más vale pájaro en mano que ciento volando.");
        alFrases.add("A mal tiempo, buena cara.");
        alFrases.add("El que no arriesga no gana.");
        alFrases.add("La suerte favorece a los audaces.");
        alFrases.add("El tiempo lo dirá.");
    }

    public Long rellenaBlog() {
        BlogEntity oBlogEntity = new BlogEntity();        
        oBlogEntity.setTitulo( alFrases.get(oAleatorioService.GenerarNumeroAleatorioEnteroEnRango(0, alFrases.size() - 1)));
        String contenidoGenerado = "";
        int numFrases=oAleatorioService.GenerarNumeroAleatorioEnteroEnRango(1, 30);
        for (int i=1; i<=numFrases; i++) {
            contenidoGenerado += alFrases.get(oAleatorioService.GenerarNumeroAleatorioEnteroEnRango(0, alFrases.size() - 1)) + " ";
            if (oAleatorioService.GenerarNumeroAleatorioEnteroEnRango(0, 10) == 1) {
                contenidoGenerado += "\n";
            }
        }
        oBlogEntity.setContenido(contenidoGenerado.trim());
                contenidoGenerado += "\n";
        // extraer 5 palabras aleatorias del contenido  para las etiquetas
        String[] palabras = contenidoGenerado.split(" ");
        // eliminar signos de puntuacion de las palabras
        for (int i = 0; i < palabras.length; i++) {
            palabras[i] = palabras[i].replace(".", "").replace(",", "").replace(";", "").replace(":", "").replace("!", "").replace("?", "");
        }   
        // convertir todas las palabras a minúsculas
        for (int i = 0; i < palabras.length; i++) {
            palabras[i] = palabras[i].toLowerCase();
        }
        // seleccionar palabras de más de 4 letras
        ArrayList<String> alPalabrasFiltradas = new ArrayList<>();
        for (String palabra : palabras) {
            if (palabra.length() > 4 && !alPalabrasFiltradas.contains(palabra)) {
                alPalabrasFiltradas.add(palabra);
            }
        }
        palabras = alPalabrasFiltradas.toArray(new String[0]);
        String etiquetas = "";
        for (int i = 0; i < 5; i++) {
            String palabra = palabras[oAleatorioService.GenerarNumeroAleatorioEnteroEnRango(0, palabras.length - 1)];
            if (!etiquetas.contains(palabra)) {
                etiquetas += palabra + ", ";
            }
        }
        if (etiquetas.endsWith(", ")) {
            etiquetas = etiquetas.substring(0, etiquetas.length() - 2);
        }
        oBlogEntity.setEtiquetas(etiquetas);
        oBlogEntity.setFechaCreacion(LocalDateTime.now());
        oBlogEntity.setFechaModificacion(null);
        oBlogRepository.save(oBlogEntity);
        return oBlogRepository.count();
    }

     public BlogEntity get(Long id) {
        return oBlogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    public Long create(BlogEntity blogEntity) {
        blogEntity.setFechaCreacion(LocalDateTime.now());
        blogEntity.setFechaModificacion(null);
        oBlogRepository.save(blogEntity);
        return blogEntity.getId();
    }


    public Long update(BlogEntity blogEntity) {
        BlogEntity existingBlog = oBlogRepository.findById(blogEntity.getId())
                .orElseThrow(() -> new RuntimeException("Blog not found"));
        existingBlog.setTitulo(blogEntity.getTitulo());
        existingBlog.setContenido(blogEntity.getContenido());
        existingBlog.setEtiquetas(blogEntity.getEtiquetas());
        existingBlog.setFechaModificacion(LocalDateTime.now());
        oBlogRepository.save(existingBlog);
        return existingBlog.getId();
    }

}
