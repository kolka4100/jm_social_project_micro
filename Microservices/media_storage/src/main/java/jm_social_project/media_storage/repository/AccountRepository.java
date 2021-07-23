package jm_social_project.media_storage.repository;

import jm_social_project.media_storage.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {}
