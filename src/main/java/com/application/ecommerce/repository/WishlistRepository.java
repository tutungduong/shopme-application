package com.application.ecommerce.repository;

import com.application.ecommerce.model.User;
import com.application.ecommerce.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

    List<Wishlist> findAllByUserOrderByCreatedDateDesc(User user);
}
