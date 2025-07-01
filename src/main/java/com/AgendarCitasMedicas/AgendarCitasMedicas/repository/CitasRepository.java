package com.AgendarCitasMedicas.AgendarCitasMedicas.repository;

import com.AgendarCitasMedicas.AgendarCitasMedicas.model.CitasMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//CRUD (Create, Read, Update, Delete):
//o save(S entity): Guarda una entidad.
//o findById(ID id): Encuentra una entidad por su ID.
//o existsById(ID id): Verifica si una entidad con un ID dado existe.
//o findAll(): Encuentra todas las entidades.
//o findAllById(Iterable<ID> ids): Encuentra todas las entidades por sus IDs.
//o count(): Cuenta todas las entidades.
//o deleteById(ID id): Borra una entidad por su ID.
//o delete(S entity): Borra una entidad.
//o deleteAll()borra todos los datos.


@Repository
public interface CitasRepository extends JpaRepository<CitasMedica, Integer> {

    Optional<CitasMedica> findByRut(String rut);
}