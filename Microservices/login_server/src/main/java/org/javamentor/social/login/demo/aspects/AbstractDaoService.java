package org.javamentor.social.login.demo.aspects;

import org.springframework.stereotype.Service;

@Service
public interface AbstractDaoService<T> {
    T findById(Long id);
}
