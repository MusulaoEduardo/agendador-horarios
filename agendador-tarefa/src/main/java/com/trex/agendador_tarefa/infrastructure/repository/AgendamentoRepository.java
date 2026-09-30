package com.trex.agendador_tarefa.infrastructure.repository;

import com.trex.agendador_tarefa.infrastructure.entity.AgendamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {

    AgendamentoEntity findByServicoAndDataHoraAgendamentoBetween(String servico, LocalDateTime dataHoraInicio,LocalDateTime dataHoraFinal);

    @Transactional
    void deleteByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento, String cliente);

    List<AgendamentoEntity> findByDataHoraAgendamentoBetween(LocalDateTime dtaaHoraInicial, LocalDateTime dataHoraFinal);

    AgendamentoEntity findByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento, String cliente);
}
