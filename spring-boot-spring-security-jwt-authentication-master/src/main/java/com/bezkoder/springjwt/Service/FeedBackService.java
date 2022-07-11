package com.bezkoder.springjwt.Service;


import com.bezkoder.springjwt.models.FeedBack;
import com.bezkoder.springjwt.models.Post;
import com.bezkoder.springjwt.repository.FeedBackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedBackService {
    @Autowired
    private FeedBackRepository feedBackRepository;

    public List<FeedBack> findAll() {
        return feedBackRepository.findAll();
    }

    public Optional<FeedBack> findById(Long id) {
        return feedBackRepository.findById(id);
    }

    public FeedBack save(FeedBack feedBack) {
        return feedBackRepository.save(feedBack);
    }

    public void deleteById(Long id) {
        feedBackRepository.deleteById(id);
    }
}
