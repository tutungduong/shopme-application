package com.application.ecommerce.repository;

import com.application.ecommerce.model.User;
import com.application.ecommerce.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Integer> {

    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
}
