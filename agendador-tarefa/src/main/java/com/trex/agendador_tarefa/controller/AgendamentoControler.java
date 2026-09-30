package com.trex.agendador_tarefa.controller;

import com.trex.agendador_tarefa.infrastructure.entity.AgendamentoEntity;
import com.trex.agendador_tarefa.services.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoControler {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoEntity> salvalAgendamento(@RequestBody AgendamentoEntity agendamentoEntity){
        return ResponseEntity.accepted().body(agendamentoService.salvarAgendamento(agendamentoEntity));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarAgendamento(@RequestParam String cliente,
                                                   @RequestParam LocalDateTime dataHoraAgendamento){
        agendamentoService.deletarAgendamento(dataHoraAgendamento,cliente);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<AgendamentoEntity>> buscarAgendamentosDia(@RequestParam LocalDate data) {
        return ResponseEntity.ok().body(agendamentoService.buscarAgendamentoDias(data));
    }

    @PutMapping
    public ResponseEntity<AgendamentoEntity> alterarAgendamento(@RequestBody AgendamentoEntity agendamentoEntity,
                                                                @RequestParam String cliente,
                                                                @RequestParam LocalDateTime dataHoraAgendamento){
        return  ResponseEntity.accepted().body(agendamentoService.alterarAgemdamento(agendamentoEntity,cliente,dataHoraAgendamento));

    }
}
