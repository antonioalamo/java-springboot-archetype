package com.archetype.layer.service;

import com.archetype.layer.domain.model.News;
import com.archetype.layer.mapper.persistence.NewsPersistenceMapper;
import com.archetype.layer.persistence.NewsRepository;
import com.archetype.layer.persistence.entity.NewsEntity;
import com.archetype.layer.event.HighImportanceNewsCreatedEvent;
import com.archetype.layer.service.exception.NewsCreationException;
import com.archetype.layer.service.exception.NewsValidationException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final NewsPersistenceMapper persistenceMapper;

    public NewsServiceImpl(NewsRepository newsRepository, ApplicationEventPublisher eventPublisher,
                           NewsPersistenceMapper persistenceMapper) {
        this.newsRepository = newsRepository;
        this.eventPublisher = eventPublisher;
        this.persistenceMapper = persistenceMapper;
    }

    @Override
    public News create(News news) {
        // Check GUID uniqueness
        if (newsRepository.existsByGuid(news.getGuid())) {
            throw new NewsCreationException("El guid " + news.getGuid() + " ya está en uso");
        }

        NewsEntity entityToSave = persistenceMapper.fromDomain(news);
        NewsEntity savedEntity = newsRepository.save(entityToSave);
        News savedNews = persistenceMapper.toDomain(savedEntity);

        // Publish event if the news is of high importance
        if (savedNews.isHighImportance()) {
            eventPublisher.publishEvent(new HighImportanceNewsCreatedEvent(savedNews.getId()));
        }

        return savedNews;
    }

    @Override
    public Optional<News> getById(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new NewsValidationException("El ID no puede ser nulo o vacío");
        }
        return newsRepository.findById(id)
                .map(persistenceMapper::toDomain);
    }

    @Override
    public List<News> list() {
        return newsRepository.findAll().stream()
                .map(persistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<String> listNewsSummaries() {
        return newsRepository.findAll().stream()
                .map(persistenceMapper::toDomain)
                .map(News::getSummary)
                .toList();
    }

    @Override
    public List<News> searchByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new NewsValidationException("La palabra clave no puede estar vacía");
        }
        String lowerCaseKeyword = keyword.toLowerCase();
        return newsRepository.findAll().stream()
                .map(persistenceMapper::toDomain)
                .filter(news -> news.getTitle().toLowerCase().contains(lowerCaseKeyword) ||
                        news.getContent().toLowerCase().contains(lowerCaseKeyword))
                .toList();
    }

    @Override
    public Optional<News> update(String id, News newsUpdates) {
        if (id == null || id.trim().isEmpty()) {
            throw new NewsValidationException("El ID no puede ser nulo o vacío");
        }
        return newsRepository.findById(id).map(existingEntity -> {
            News existingNews = persistenceMapper.toDomain(existingEntity);

            if (newsUpdates.getTitle() != null) {
                existingNews.changeTitle(newsUpdates.getTitle());
            }
            if (newsUpdates.getContent() != null) {
                existingNews.setContent(newsUpdates.getContent());
            }
            if (newsUpdates.getGuid() != null) {
                existingNews.setGuid(newsUpdates.getGuid());
            }
            if (newsUpdates.getLink() != null) {
                existingNews.setLink(newsUpdates.getLink());
            }
            if (newsUpdates.getGrade() != null) {
                existingNews.setGrade(newsUpdates.getGrade());
            }

            NewsEntity entityToSave = persistenceMapper.fromDomain(existingNews);
            NewsEntity savedEntity = newsRepository.save(entityToSave);
            return persistenceMapper.toDomain(savedEntity);
        });
    }

    @Override
    public void delete(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new NewsValidationException("El ID no puede ser nulo o vacío");
        }

        if (!newsRepository.existsById(id)) {
            throw new NewsValidationException("No existe una noticia con el ID: " + id);
        }

        newsRepository.deleteById(id);
    }
}