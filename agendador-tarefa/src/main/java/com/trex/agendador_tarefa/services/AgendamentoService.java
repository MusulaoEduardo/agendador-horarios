package com.trex.agendador_tarefa.services;

import com.trex.agendador_tarefa.infrastructure.entity.AgendamentoEntity;
import com.trex.agendador_tarefa.infrastructure.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoEntity salvarAgendamento(AgendamentoEntity agendamento){
        LocalDateTime horaAgendamento = agendamento.getDataHoraAgendamento();
        LocalDateTime horaFim = agendamento.getDataHoraAgendamento().plusHours(1);

        AgendamentoEntity agendados = agendamentoRepository.findByServicoAndDataHoraAgendamentoBetween(agendamento.getServico(),horaAgendamento,horaFim);
        if(Objects.nonNull(agendados)){
            throw new RuntimeException("Horario ja esta preenchido");
        }
        return agendamentoRepository.save(agendamento);
    }

    public void deletarAgendamento(LocalDateTime dataHoraAgendamento,String cliente){
        agendamentoRepository.deleteByDataHoraAgendamentoAndCliente(dataHoraAgendamento,cliente);

    }

    public List<AgendamentoEntity> buscarAgendamentoDias(LocalDate data){
        LocalDateTime primeiraHoraDia = data.atStartOfDay();
        LocalDateTime horaFinalDia = data.atTime(23,59,59);

        return  agendamentoRepository.findByDataHoraAgendamentoBetween(primeiraHoraDia,horaFinalDia);
    }

    public AgendamentoEntity alterarAgemdamento(AgendamentoEntity agendamentoEntity, String cliente, LocalDateTime dataHoraAgendamento){
        AgendamentoEntity agenda = agendamentoRepository.findByDataHoraAgendamentoAndCliente(dataHoraAgendamento,cliente);
        if(Objects.nonNull(agenda)){
            throw new RuntimeException("Horario não esta preenchido");
        }
        agendamentoEntity.setId(agenda.getId());
        return agendamentoRepository.save(agendamentoEntity);
    }

}
