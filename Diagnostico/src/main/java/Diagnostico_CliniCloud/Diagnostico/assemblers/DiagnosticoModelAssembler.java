package Diagnostico_CliniCloud.Diagnostico.assemblers;

import Diagnostico_CliniCloud.Diagnostico.Model.Diagnostico;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import Diagnostico_CliniCloud.Diagnostico.Controller.DiagnosticoControllerV2;

@Component
public class DiagnosticoModelAssembler implements RepresentationModelAssembler<Diagnostico, EntityModel<Diagnostico>> {
    @Override
    public EntityModel<Diagnostico> toModel(Diagnostico diagnostico) {
        return EntityModel.of(diagnostico,
            linkTo(methodOn(DiagnosticoControllerV2.class).buscarPorId(diagnostico.getId())).withSelfRel(),
            linkTo(methodOn(DiagnosticoControllerV2.class).Listar()).withRel("diagnosticos")
        );
    }
}
