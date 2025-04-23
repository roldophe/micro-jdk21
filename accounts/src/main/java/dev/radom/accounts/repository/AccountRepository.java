package dev.radom.accounts.repository;

import dev.radom.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByCustomerId(Integer customerId);

    Optional<Account> findByAccountNumber(String accountNumber);
}
