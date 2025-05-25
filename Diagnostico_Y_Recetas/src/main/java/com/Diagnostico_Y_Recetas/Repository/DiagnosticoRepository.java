package com.Diagnostico_Y_Recetas.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.Diagnostico_Y_Recetas.Model.Diagnostico;

@Repository
public class DiagnosticoRepository {

    private List<Diagnostico> diagnosticos = new ArrayList<>();


    // Crear algunos diagnósticos iniciales
    public DiagnosticoRepository() {
        // Agregar diagnósticos de ejemplo
        Diagnostico diagnostico1 = new Diagnostico();
        diagnostico1.setRun("12345678-9");
        diagnostico1.setId(1);
        diagnostico1.setDescripcion("Gripe");
        diagnostico1.setEstado("Pendiente");
        diagnostico1.setTipo("Consulta");

        Diagnostico diagnostico2 = new Diagnostico();
        diagnostico2.setRun("98765432-1");
        diagnostico2.setId(2);
        diagnostico2.setDescripcion("Dolor de cabeza");
        diagnostico2.setEstado("Completo");
        diagnostico2.setTipo("Urgencia");

        diagnosticos.add(diagnostico1);
        diagnosticos.add(diagnostico2);
    }


    // Metodo para retornar todos los diagnósticos
    public List<Diagnostico> obtenerDiagnosticos() {
        return diagnosticos;
    }

    // Metodo para Buscar un diagnóstico por ID
    public Diagnostico getDiagnosticoPorId(int id) {
        for (Diagnostico diagnostico : diagnosticos) {
            if (diagnostico.getId() == id) {
                return diagnostico;
            }
        }
        return null; // Retorna null si no se encuentra el diagnóstico
    }

    // Metodo para guardar un diagnóstico
    public Diagnostico guardarDiagnostico(Diagnostico diagnostico) {
        diagnosticos.add(diagnostico);
        return diagnostico;
    }

    // Metodo para actualizar un diagnóstico
    // Metodo para actualizar un diagnóstico
    public Diagnostico actualizarDiagnostico(Diagnostico nuevoDiagnostico) {
        int idPosicion = -1;
        int id = nuevoDiagnostico.getId();

        // Buscar la posición del diagnóstico con el ID proporcionado
        for (int i = 0; i < diagnosticos.size(); i++) {
            if (diagnosticos.get(i).getId() == id) {
                idPosicion = i;
                break;
            }
        }

        // Si no se encuentra el diagnóstico, retornar null
        if (idPosicion == -1) {
            return null;
        }
        

        // Crear un nuevo diagnóstico actualizado
        Diagnostico diagnosticoActualizado = new Diagnostico();

        diagnosticoActualizado.setRun(nuevoDiagnostico.getRun());
        diagnosticoActualizado.setId(id);
        diagnosticoActualizado.setDescripcion(nuevoDiagnostico.getDescripcion());
        diagnosticoActualizado.setFecha(nuevoDiagnostico.getFecha());
        diagnosticoActualizado.setSintomas(nuevoDiagnostico.getSintomas());
        diagnosticoActualizado.setMedicamentos(nuevoDiagnostico.getMedicamentos());
        diagnosticoActualizado.setRecomendaciones(nuevoDiagnostico.getRecomendaciones());
        diagnosticoActualizado.setObservaciones(nuevoDiagnostico.getObservaciones());
        diagnosticoActualizado.setEstado(nuevoDiagnostico.getEstado());
        diagnosticoActualizado.setTipo(nuevoDiagnostico.getTipo());
        // Agregar más campos según sea necesario

        // Reemplazar el diagnóstico en la lista
        diagnosticos.set(idPosicion, diagnosticoActualizado);

        return diagnosticoActualizado; // Retorna el diagnóstico actualizado
    }


    // Metodo para eliminar un diagnóstico por ID
    public void eliminarDiagnostico(int id) {
        Diagnostico diagnosticoAEliminar = getDiagnosticoPorId(id);
        if (diagnosticoAEliminar != null) {
            diagnosticos.remove(diagnosticoAEliminar);
        }
    }




}
