package com.agil.consultas.apiconsultaagil.exceptions;

public class ConsultaAgendadaException extends RegraDeNegocioException{

    public ConsultaAgendadaException() {
        super("Já existe uma consulta agendada para a mesma data e horário.");
    }
}
