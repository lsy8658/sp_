package com.example.demo.repository;

import com.example.demo.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository("/")
public interface MemberRepository extends CrudRepository<Member, Long> {

    @GetMapping
    public String hello () {
        return "hello spring boot"
    }
}
