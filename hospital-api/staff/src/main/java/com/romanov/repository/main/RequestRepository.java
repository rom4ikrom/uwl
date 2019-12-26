package com.romanov.repository.main;

import com.romanov.model.request.Request;
import com.romanov.model.request.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query("SELECT r FROM request r WHERE r.request_status = ?1")
    List<Request> findRequestsByStatus(RequestStatus status);

}
