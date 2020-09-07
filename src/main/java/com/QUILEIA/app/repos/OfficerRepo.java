package com.QUILEIA.app.repos;

import org.springframework.data.repository.CrudRepository;

import com.QUILEIA.app.model.Officer;

public interface OfficerRepo extends CrudRepository<Officer, String> {
}
