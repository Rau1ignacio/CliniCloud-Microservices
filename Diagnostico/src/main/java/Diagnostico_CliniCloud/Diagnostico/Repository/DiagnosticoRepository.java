package Diagnostico_CliniCloud.Diagnostico.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Diagnostico_CliniCloud.Diagnostico.Model.Diagnostico;

@Repository
public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Integer> {
    
    //Usando JPQL para consultas personalizadas
    
    // Encuentra diagnósticos por run
    @Query("SELECT d FROM Diagnostico d WHERE d.run = ?1")
    Diagnostico buscarPorRun(@Param("run") String run);
    // Encuentra diagnósticos por correo
    @Query("SELECT d FROM Diagnostico d WHERE d.correo = ?1")
    Diagnostico buscarPorCorreo(@Param("correo") String correo);
    // Encuentra diagnósticos por nombre
    @Query("SELECT d FROM Diagnostico d WHERE d.nombre = ?1")
    Diagnostico buscarPorNombre(@Param("nombre") String nombre);
    
}
