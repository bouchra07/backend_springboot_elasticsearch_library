package com.example.springmysqlelastic.utils;

import com.example.springmysqlelastic.mapper.BookMapper;
import com.example.springmysqlelastic.model.Book;
import com.example.springmysqlelastic.repo.IBookDAO;
import com.example.springmysqlelastic.repo.elastic.IBookESRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ElasticSynchronizer {

    private IBookDAO bookDAO;
    private IBookESRepo bookESRepo;
    private BookMapper bookMapper;

    private static final Logger LOG = LoggerFactory.getLogger(ElasticSynchronizer.class);

    @Autowired
    public ElasticSynchronizer(IBookDAO bookDAO, IBookESRepo bookESRepo, BookMapper bookMapper) {
        this.bookDAO = bookDAO;
        this.bookESRepo = bookESRepo;
        this.bookMapper = bookMapper;
    }

    @Scheduled(cron = "0 */3 * * * *")
    @Transactional
    public void sync() {
        LOG.info("Start Syncing - {}", LocalDateTime.now());
        this.syncBooks();
        LOG.info(" End Syncing - {}", LocalDateTime.now());
    }

    private void syncBooks() {

        Specification<Book> bookSpecification = (root, criteriaQuery, criteriaBuilder) ->
                getModificationDatePredicate(criteriaBuilder, root);
        List<Book> bookList;
        if (bookESRepo.count() == 0) {
            bookList = bookDAO.findAll();
        } else {
            bookList = bookDAO.findAll(bookSpecification);
        }
        for(Book book: bookList) {
            LOG.info("Syncing Book - {}", book.getId());
            bookESRepo.save(this.bookMapper.toBookModel(book));
        }
    }

    private static Predicate getModificationDatePredicate(CriteriaBuilder cb, Root<?> root) {
        Expression<Timestamp> currentTime;
        currentTime = cb.currentTimestamp();
        Expression<Timestamp> currentTimeMinus = cb.literal(new Timestamp(System.currentTimeMillis() -
                (Constants.INTERVAL_IN_MILLISECONDE)));
        return cb.between(root.<Date>get(Constants.MODIFICATION_DATE),
                currentTimeMinus,
                currentTime
        );
    }
}
