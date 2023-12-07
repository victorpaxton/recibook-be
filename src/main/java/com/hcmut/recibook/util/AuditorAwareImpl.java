package com.hcmut.recibook.util;

import com.hcmut.recibook.model.entity.User.User;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<User> {


    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.empty();
    }
}
