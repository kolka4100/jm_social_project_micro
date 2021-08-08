package org.javamentor.social.login.demo.serviceImpl;

import org.javamentor.social.login.demo.config.JwtTokenProvider;
import org.javamentor.social.login.demo.dao.AccountDao;
import org.javamentor.social.login.demo.dao.RoleDao;
import org.javamentor.social.login.demo.exceptions.BlockUserException;
import org.javamentor.social.login.demo.exceptions.NoSuchUserException;
import org.javamentor.social.login.demo.model.Account;
import org.javamentor.social.login.demo.model.dto.AccountDTO;
import org.javamentor.social.login.demo.model.dto.AuthorizeDto;
import org.javamentor.social.login.demo.model.request.AuthRequest;
import org.javamentor.social.login.demo.service.AccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AccountServiceImpl implements AccountService {

    private final PasswordEncoder passwordEncoder;
    private final AccountDao accountDao;
    private final RoleDao roledao;
    private final JwtTokenProvider jwtTokenProvider;

    public AccountServiceImpl(PasswordEncoder passwordEncoder, AccountDao accountDao, RoleDao roledao, JwtTokenProvider jwtTokenProvider) {
        this.passwordEncoder = passwordEncoder;
        this.accountDao = accountDao;
        this.roledao = roledao;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Account findByEmailAndPassword(String email, String password) {
        Account account = accountDao.findByEmail(email);
        if (account != null) {
            if (passwordEncoder.matches(password, account.getPassword())) {
                return account;
            }
        }
        return null;
    }

    public void register (AuthRequest  authRequest) {
        try {
            Account account = accountDao.findByEmail(authRequest.getEmail());

            account.setEmail(authRequest.getEmail());
            account.setPassword(passwordEncoder.encode(authRequest.getPassword()));
            accountDao.saveAndFlush(account);
        } catch (NullPointerException e){
            Account account = new Account();
            account.setEmail(authRequest.getEmail());
            account.setRole(roledao.getByRoleName("ROLE_USER"));
            account.setPassword(passwordEncoder.encode(authRequest.getPassword()));
            account.setEnable(true);
            accountDao.saveAndFlush(account);
        }
    }

    @Override
    public AuthorizeDto getAuthorizeDto(AuthRequest request) {
        Account account = findByEmailAndPassword(request.getEmail(), request.getPassword());
        if (account.getEnable()) {
            String token = jwtTokenProvider.generateToken(account.getEmail(), account.getRole(), account.getId());
            return new AuthorizeDto(token, account);
        } else {
            throw new BlockUserException("user blocked");
        }
    }

    @Override
    public void save(Account account) {
        accountDao.saveAndFlush(account);
    }

    @Override
    public Account findById(final Long userId) {
        final Optional<Account> accountOptional = accountDao.findById(userId);
        return accountOptional.orElse(null);
    }

    @Override
    public String getUserEmailByUserId(final Long userId) {
        Optional<Account> accountOptional = accountDao.findById(userId);
        return accountOptional.map(Account::getEmail).orElse(null);
    }

    public String getStatusById(Long userId) {
        AccountDTO accountDTO = accountDao.findAccountDtoById(userId);
        return accountDTO.getStatus().name();
    }
}
