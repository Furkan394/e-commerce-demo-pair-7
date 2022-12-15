package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.users.GetAllUserResponse;
import com.etiya.ecommercedemopair7.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Integer> {
    @Query("Select new com.etiya.ecommercedemopair7.business.response.users.GetAllUserResponse(u.id, u.email) from User u")
    Page<GetAllUserResponse> findAllUsersWithPage(Pageable pageable);


}
