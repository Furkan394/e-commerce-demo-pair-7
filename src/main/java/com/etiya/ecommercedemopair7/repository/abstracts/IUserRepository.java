package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.users.GetAllUserResponse;
import com.etiya.ecommercedemopair7.entities.concretes.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository extends JpaRepository<User,Integer> {
    @Query("Select new com.etiya.ecommercedemopair7.business.response.users.GetAllUserResponse(u.id, u.email) from User u")
    Page<GetAllUserResponse> findAllUsersWithPage(Pageable pageable);


}
