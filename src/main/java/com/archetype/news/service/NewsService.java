package com.archetype.news.service;

import com.archetype.news.domain.model.News;
import java.util.List;
import java.util.Optional;
import com.archetype.news.service.exception.NewsValidationException;
import org.springframework.stereotype.Service;

/**
 * Interfaz que define los servicios para la gestión de noticias.
 * Todos los métodos validan las entradas en el dominio y lanzan NewsValidationException
 * si las invariantes no se cumplen.
 */
@Service
public interface NewsService {

    /**
     * Crea una nueva noticia y la persiste.
     * La validación de unicidad del GUID y otras invariantes se realiza en el dominio.
     *
     * @param news La noticia a crear.
     * @return La noticia creada y persistida.
     * @throws NewsValidationException Si los datos de la noticia son inválidos (por ejemplo, GUID duplicado).
     */
    News create(News news);

    /**
     * Obtiene una noticia por su ID.
     *
     * @param id El identificador de la noticia.
     * @return Un Optional con la noticia si existe, o vacío si no.
     * @throws NewsValidationException Si el ID es nulo o vacío.
     */
    Optional<News> getById(String id);

    /**
     * Lista todas las noticias disponibles.
     *
     * @return Una lista inmutable de noticias.
     */
    List<News> list();

    /**
     * Lista los resúmenes de todas las noticias.
     *
     * @return Una lista inmutable de resúmenes de noticias.
     */
    List<String> listNewsSummaries();

    /**
     * Busca noticias que contengan la palabra clave en el título o contenido.
     *
     * @param keyword La palabra clave a buscar (no sensible a mayúsculas).
     * @return Una lista inmutable de noticias que coinciden con la palabra clave.
     * @throws NewsValidationException Si la palabra clave es nula o vacía.
     */
    List<News> searchByKeyword(String keyword);

    /**
     * Actualiza una noticia existente con los datos proporcionados.
     * Solo se actualizan los campos no nulos, y las validaciones se realizan en el dominio.
     *
     * @param id El identificador de la noticia a actualizar.
     * @param newsUpdates Los datos actualizados de la noticia.
     * @return Un Optional con la noticia actualizada si existe, o vacío si no.
     * @throws NewsValidationException Si el ID es nulo, vacío, o si los datos actualizados son inválidos (por ejemplo, GUID duplicado).
     */
    Optional<News> update(String id, News newsUpdates);

    /**
     * Elimina una noticia por su ID.
     *
     * @param id El identificador de la noticia a eliminar.
     * @throws NewsValidationException Si el ID es nulo o vacío.
     */
    void delete(String id);
}